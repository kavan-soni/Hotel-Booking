# Hotel Booking System


## Steps to run the program

### Step 1: Compile the Billing class using `javac`

In order to compile the project, run the following command in the project directory.

Go to the src directory. Billing class is located here.
```
cd src
```

Compile Billing.java class by using `javac`.

```
javac Billing.java
```


### Step 2: Run the Billing class using `java` command

Run the Billing class
```
java Billing
```

It will prompt for the location of the input file.

```
kavansoni@Kavans-MacBook-Air src % java Billing



Enter a path to the input file (containing order): input1.csv

Input File Path: input1.csv

```
You can choose to enter the absolute path instead. That should work. 

Inventory is scanned from `inventory.csv` (You will need to change this file if you are making changes in inventory)

### Step 3: Check the output files generated

For a SUCCESSFUL transaction, `successful_order_output.csv` is generated in the same directory.
For a UNSUCCESSFUL transaction, `unsuccessful_order_output.txt` is generated in the same directory.

## How to configure cap on each category

We can configure cap on each cateogory by modifying `config.properties` file. 

Here is the cap configured by default. 

```
Essentials=3
Luxury=4
Misc=3
```

## Sample Runs (Test cases)

### A Successful transaction

We have sample input files. Here is how you can run a successful transaction by using `input1.csv`

input1.csv

```
Item,Quantity,CardNumber
Shampoo,2,4.12E+12
chocolates,1
Wallet,1
Pen,1
```

Run the command `java Billing` and when prompted, enter the path of `input1.csv`

```
kavansoni@Kavans-MacBook-Air src % java Billing



Enter a path to the input file (containing order): input1.csv
```

Generated output file will be `successful_order_output.csv`

Here is the content of the output file.

```

Item, Quantity, Price, TotalPrice
Pen,1,3.0,126.0
Shampoo,2,20.0
chocolates,1,3.0
Wallet,1,100.0

```

### An Unsuccessful transaction (By Quantity)

We have sample input files. Here is how you can run an un-successful transaction by using `input2.csv`

input2.csv

```
Item,Quantity,CardNumber
Shampoo,201,4.12E+12
chocolates,1
Wallet,1
Pen,1

```

Here is config.properties (We changed the limit to the category Essentials)

```
Essentials=300
Luxury=4
Misc=3
```

Run the command `java Billing` and when prompted, enter the path of `input2.csv`

```
kavansoni@Kavans-MacBook-Air src % java Billing



Enter a path to the input file (containing order): input2.csv

```

Generated output file will be `unsuccessful_order_output.txt`

Here is the content of the output file.

```

Please Correct Quantities : Shampoo, 

```


### An Unsuccessful transaction (By Cap)

We have sample input files. Here is how you can run an un-successful transaction by using `input3.csv`

input3.csv

```
Item,Quantity,CardNumber
Shampoo,201,4.12E+12
chocolates,1
Wallet,1
Pen,1

```

Here is config.properties (We will revert the limit to the category Essentials)

```
Essentials=3
Luxury=4
Misc=3
```

Run the command `java Billing` and when prompted, enter the path of `input3.csv`

```
kavansoni@Kavans-MacBook-Air src % java Billing



Enter a path to the input file (containing order): input3.csv
```

Generated output file will be `unsuccessful_order_output.txt`

Here is the content of the output file.

```
Max Cap Exceeded For Essentials: Cap: 3, Count of Essentials in the Order: 201

```

### Class Diagram

Here is the class diagram: 

![202](https://user-images.githubusercontent.com/99461999/165007937-913fb0af-6d09-4d3a-923a-d164ffe9720c.jpeg)



### Design Patterns: 

# Singleton Pattern
![image](https://user-images.githubusercontent.com/99461999/166174337-e68f3aac-37be-46cf-b2a8-47fab49cd582.png)

### Participants: 

CardDatabase

# Chain of Responsibility Pattern
![image](https://user-images.githubusercontent.com/99461999/166174293-58efc6eb-4aef-4aa7-a4f0-43d49858b8ce.png)

### Participants: 
Interface: Handler
ConcreteHandlers: CardReaderHandler, CapValidationHandler, OrderSuccessfulHandler, QuantityValidationHandler


# Factory Pattern
![image](https://user-images.githubusercontent.com/99461999/166174358-3db4942b-2ab9-4790-b628-adec1b998fc4.png)

### Participants: 

Factory

# Composite Pattern
![image](https://user-images.githubusercontent.com/99461999/166174220-b8b5109e-6da8-479b-8068-ef8b65876a4c.png)

### Participants:

Component Interface,
Composite: Inventory
Leaf: Essentials, Luxury, Misc

