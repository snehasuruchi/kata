Code Kata Basic POS function Java

//Date : Feb 3 2019
//Developer : Suruchi

Programming language Used - Java 8

I have tried to develop this project in Core Java used Junit to follow the TD principle

Since there is no scanner, the program will accept the items and quantities through terminal
Since there is no database, I have hard coded the UOM and list of Default items in the program

Program will ask User to enter item name. 
Currently the Valid items are
pepsi,soup,oranges,onions,avocado,pineapple,cauliflower,celery.
Price and UOM is maintained only for these items.

Based on the UOM - EA or LB program will ask to enter Quantity or Weight of the item and print the price

If user wants to cancel any entered item, he can press 'X' and enter the name and quantity of items to cancel.

When the user is done scanning and ready for checkout he will press the button 'R' and complete the process.

//Date : Feb 6 2019

The program also support the Weekly Mark down prices of items.
Mark down is maintained for items like Avocado,Pineapple,Pepsi,Soup
I have assumed that Store Manager will maintain the Mark Down prices every week however I havent developed a method to set the markdown items.

The program also supports special pricing procedures BNGMPXF e.g Buy 1 Get 1 50% off 
Pricing Rule Code - BNGMPXF is for Buy N For X (e.g Buy 2 and get 50% off for additional 1 item)
BNGMPXF Rule format is "BNGMPXF-<Number Of Items>:<Number Of Special Items>:<DiscountPercent>:<ItemLimit>
ItemLimit U is equivalent to Unlimited
Currently items Eggs and Celery is configured to run through this rule 

The program also supports special pricing procedures BNFX (e.g Buy 3 for 5) 
Pricing Rule Code - BNFX is for Buy N For X (e.g Buy 3 for 5)
BNFX Rule format is "BNFX-<Number Of Items>:<Price>:<ItemLimit>
ItemLimit U is equivalent to Unlimited
Currently item Cookies is configured to run through this rule

Only a certain limit is allowed for these rules.



**************************************************************************************************************************************************************************************************************************
Sample Terminal Input and Output to process 4 Units of Pepsi and 4 Pounds of Onions and Cancellation of 1 Unit of Pepsi
***********************************************************************************************************************
Press R when ready for checkout

Press X for item cancellation

Enter the item:
 
pepsi

Enter the quantity:
 
4

Total is : 6.0

Enter the item:
 
onions

Enter the weight:
 
2

Total is : 9.0

Enter the item: 

X

Enter the item you want to cancel: 

pepsi

Enter the quantity of items to cancel: 

1

Total is : 7.5

Enter the item: 
r

Thanks For Shopping with Us

*************************************************************************************************************

*************************************************************************************************************
Sample Terminal Input and Output to process 6 Cookies and 2 Unit of Eggs
*************************************************************************************************************
Press R when ready for checkout

Press X for item cancellation

Enter the item: 

Cookies

Enter the quantity: 

6

Your Special price is : 10.0

Total is : 10.0

You save: 2.0 on Cookies

Enter the item: 

Eggs

Enter the quantity: 

2

Your Special price is : 1.5

Total is : 11.5

You save: 1.5 on Eggs

Enter the item: 

r

Thanks For Shopping with Us
