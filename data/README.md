# Emerlyn Interview Project

We have just received new price catalogs from our vendors. We would like to
report the estimated impact this new pricing will have on our future purchases.

## Files

There are 3 files included:

* catalog-1.csv & catalog-2.csv: these are the catalogs containing current
  pricing of items available for purchase.

* purchases.csv: this is a listing of all purchases made last month.

## Task

The task is to report the estimated impact of the current prices (from the
catalog files), if the same purchases (from the purchases file) are made again
next month.

For example, the item with NDC 00657778127 was purchased 3 times: 

purchases.csv:
00657778127,1,32.30,32.30
00657778127,2,25.84,51.68
00657778127,2,32.30,64.60

The total quantity purchased was 5 and the total cost was 148.58. The estimated
impact will be the total cost of buying 5 again, using the new price (from the
catalog files).

Note: the item may be available to purchase from both catalogs, and they may
have different prices. In this event, you should use the lowest price.

For example, this item has a lower price in catalog-1.csv:

catalog-1.csv
00657778127,025376,29.39

catalog-2.csv
00657778127,835152,32.30

If we bought 5 of this item again, at the new price 29.39, the total cost would
be 146.95. This is 1.63 lower than the previous total cost, so the impact would
be -1.63.

Report the impact for each NDC, as well as the impact grand total. Sort the
items by impact so the items that will cost more come first.
