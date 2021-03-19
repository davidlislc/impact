package com.davidli.demo;

import org.apache.spark.sql.*;
import org.springframework.stereotype.Service;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.sum;

@Service
public class SparkService {

  public void impactCalculation() {
    SparkSession spark =
        SparkSession.builder().appName("Simple Application").master("local").getOrCreate();
    // Read all the catalog csv files, sort by NDC, then take the lower price. Returns a sanctioned
    // catalog.
    Dataset<Row> sanctionedCatalogDataSet =
        spark
            .read()
            .format("csv")
            .option("header", "true")
            .load("data/catalog*.csv")
            .toDF("NDC", "catalogNumber", "price")
            .sort("NDC")
            .groupBy("NDC")
            .agg(functions.min("price"));
    // Read the purchases
    Dataset<Row> purchasesDataset =
        spark
            .read()
            .format("csv")
            .option("header", "true")
            .load("data/purchases.csv")
            .toDF("NDC", "Quantity", "Cost", "Extended Cost");
    /*
    Join the sanctioned catalog with the purchases by using NDC as key.
    Calculate the new cost for each purchase
    Calculate the impact for each purchase by formula: new price times quantity, then minus the old cost
    Group by DNC
    Then aggregate the sum for each group as "sum(impact)"
    Sort by impact
     */
    Dataset<Row> catalogPriceAndPurchaseJoinDataset =
        sanctionedCatalogDataSet
            .join(purchasesDataset)
            .where(sanctionedCatalogDataSet.col("NDC").equalTo(purchasesDataset.col("NDC")))
            .toDF("NDC", "newPrice", "purchaseNDC", "Quantity", "Cost", "Extended Cost")
            //.drop("purchaseNDC")
            .withColumn(
                "impact",
                col("newPrice")
                    .$times(col("Quantity"))
                    // .cast("Decimal(20,2)")
                    .minus(col("Extended Cost"))
                    .cast("Decimal(20,2)"))
            .groupBy("NDC")
            .agg(functions.sum("impact"))
            .sort(col("sum(impact)").desc())
            .toDF("NDC", "impact");

    catalogPriceAndPurchaseJoinDataset.show(10000);
    // write impact to csv file
    catalogPriceAndPurchaseJoinDataset
        .coalesce(1)
        .write()
        .format("com.databricks.spark.csv")
        .mode(SaveMode.Overwrite)
        .option("header", "true")
        .save("data/impact");
    // calculate grandTotal
    Dataset<Row> grandTotalDataset =
        catalogPriceAndPurchaseJoinDataset.select(sum("impact")).toDF("grandTotal");
    // write the grand total
    grandTotalDataset
        .coalesce(1)
        .write()
        .format("com.databricks.spark.csv")
        .mode(SaveMode.Overwrite)
        .option("header", "true")
        .save("data/impacttotal");

    grandTotalDataset.show();
    spark.stop();
  }
}
