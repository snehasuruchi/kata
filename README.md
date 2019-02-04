Code Kata Basic POS function Java

//Date : Feb 3 2019
//Developer : Suruchi

Programming language Used - Java 8

I have tried to develop this project in Core Java used Junit to follow the TD principle

Since there is no scanner, the program will accept the items and quantities through terminal
Since there is no database, I have hard coded the UOM and list of Default items in the program

Program will ask User to enter item name. 
Currently the Valid items are
pepsi,soup,oranges,onions,avocado.
Price and UOM is maintained only for these items.

Based on the UOM EA or LB program will ask to enter Quantity or Weight of the item and print the price

If user wants to cancel any entered item, he can press 'X' and enter the name and quantity of items to cancel.

When the is done scanning and ready for checkout he will press the button R and complete the process.


I have not included Markdown and Weekly Specials logic as of now.


**********************************************************************************************************************
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
4
Total is : 12.0
Enter the item: 
X
Enter the item you want to cancel: 
pepsi
Enter the quantity of items to cancel: 
1
Total is : 10.5
Enter the item: 
r
Thanks For Shopping with Us
