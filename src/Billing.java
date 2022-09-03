import com.cmpe202.*;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class Billing {


    public static void main(String[] args) throws Exception {


        Inventory inventory = new Inventory();
        CardDatabase cardDatabase = CardDatabase.getCardDatabase();
        Order order = new Order();

//        if (args.length != 3) {
//            throw new IllegalArgumentException("path to inventory.csv, input.csv and config.properties should be provided.");
//        }


//        String inputFilename = args[1];
        System.out.println("\n\n");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a path to the input file (containing order): ");

        String pathToInputFile = sc.nextLine();
        System.out.println("\nInput File Path: " + pathToInputFile);
        parseInputFile(order, pathToInputFile);


        System.out.println("\n----------------OUTPUT-----------------");
        String inventoryFilename = "inventory.csv";
        System.out.println("\ncom.cmpe202.Inventory Filename: " + inventoryFilename);


        //Read the file line by line to prepare inventory

        parseInventory(inventory, "inventory.csv");
        parseCreditCard(cardDatabase, inventoryFilename);


        inventory.printInventory();
        cardDatabase.printCardDatabase();


        Properties properties = loadProperties("config.properties");

        final int capOfEssentials = Integer.parseInt(properties.getProperty("Essentials"));
        System.out.println("Cap configured: com.cmpe202.Essentials: " + capOfEssentials);

        final int capOfLuxury = Integer.parseInt(properties.getProperty("Luxury"));
        System.out.println("Cap configured: com.cmpe202.Luxury: " + capOfLuxury);

        final int capOfMisc = Integer.parseInt(properties.getProperty("Misc"));
        System.out.println("Cap configured: com.cmpe202.Misc: " + capOfMisc);


        order.printOrder();


        Handler handler = initChainOfResponsibility(cardDatabase, capOfEssentials, capOfLuxury, capOfMisc);

        handler.handleRequest(order, inventory);
    }


    private static Properties loadProperties(String path) throws IOException {

        InputStream inputStream = new FileInputStream(path);
        Properties prop = new Properties();
        prop.load(inputStream);
        return prop;
    }

    private static Handler initChainOfResponsibility(CardDatabase cardDatabase, int capOfEssentials, int capOfLuxury, int capOfMisc) {
        Handler capValidationHandler = new CapValidationHandler(capOfEssentials, capOfLuxury, capOfMisc);
        Handler quantityValidationHandler = new QuantityValidationHandler();
        Handler cardReaderHandler = new CardReaderHandler(cardDatabase);
        Handler orderSuccessfulHandler = new OrderSuccessfulHandler();
        Handler orderFailureHandler = new OrderFailureHandler();

        capValidationHandler.setNext(quantityValidationHandler);
        quantityValidationHandler.setNext(cardReaderHandler);
        cardReaderHandler.setNext(orderSuccessfulHandler);
        orderSuccessfulHandler.setNext(orderFailureHandler);

        return capValidationHandler;
    }

    private static void parseInputFile(Order order, String inputFilename) throws FileNotFoundException {
        try {
            Scanner scanner = new Scanner(new File(inputFilename));


            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                String[] split = currentLine.split(",");

                String itemName = split[0];
                String quantity = split[1];

                Order.OrderItem orderItem = new Order.OrderItem(itemName, quantity);

                order.putOrderItem(orderItem);

                if (split.length == 3) {
                    String cardNumber = split[2];
                    order.addCardNumber(cardNumber);
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }

    }

    private static void parseCreditCard(CardDatabase cardDatabase, String filename) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(filename));

        try {

            //inventory lines are skilled from the csv
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();

                if (currentLine.equals("Cards")) {
                    break;
                }
            }

            // ColumnName is read which is CardNumber
            String columnName = scanner.nextLine();
            System.out.println("columnName should be CardNumber. columnName : " + columnName);

            //Actual cards are read.
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();

                cardDatabase.addIfAbsent(currentLine); //Card is added in the database;
            }



            scanner.close();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static void parseInventory(Inventory inventory, String filename) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(filename));

        try {

            scanner.nextLine(); // Table name 'Items' is skipped
            scanner.nextLine(); // Column Names are skipped.

            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();


                String[] str = currentLine.split(",");

                if (currentLine.isBlank()) {
                    System.out.println("Empty line is encountered. We stop scanning the inventory");
                    break;
                }

                System.out.println(currentLine);
//                System.out.println(str[1]);

                String itemName = str[0];
                String category = str[1];
                String quantity = str[2];
                String costPerUnit = str[3];


                Component component = Factory.create(itemName, category, quantity, costPerUnit);
                inventory.add(component);


            }
            scanner.close();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }


    }
}

