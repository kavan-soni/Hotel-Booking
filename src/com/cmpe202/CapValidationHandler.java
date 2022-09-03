package com.cmpe202;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class CapValidationHandler implements Handler {

    private final int capOfEssentials;
    private final int capOfLuxury;
    private final int capOfMisc;


    private Handler next;

    public CapValidationHandler(int capOfEssentials, int capOfLuxury, int capOfMisc) {
        this.capOfEssentials = capOfEssentials;
        this.capOfLuxury = capOfLuxury;
        this.capOfMisc = capOfMisc;
    }

    @Override
    public void handleRequest(Order order, Inventory inventory) throws IOException {


        Map<Order.OrderItem, Order.Status> orderItemStatusMap = order.getOrderItemStatusMap();
        int countOfEssentials = 0;
        int countOfLuxury = 0;
        int countOfMisc = 0;


        for (Map.Entry<Order.OrderItem, Order.Status> entry : orderItemStatusMap.entrySet()) {

            Order.OrderItem orderItem = entry.getKey();

            Component inventoryChild = inventory.getChild(orderItem.getItemName());

            CategoryType categoryType = inventoryChild.getCategoryType();

            if (categoryType.equals(CategoryType.ESSENTIALS)) {
                countOfEssentials = countOfEssentials + orderItem.getQuantity();
            } else if (categoryType.equals(CategoryType.LUXURY)){
                countOfLuxury = countOfLuxury + orderItem.getQuantity();
            } else if (categoryType.equals(CategoryType.MISC)) {
                countOfMisc = countOfMisc + orderItem.getQuantity();
            }

        }


        if (countOfEssentials > capOfEssentials) {
            generateOutputTxt(new StringBuilder("Max Cap Exceeded For Essentials: Cap: " + capOfEssentials + ", Count of Essentials in the Order: " + countOfEssentials));
        } else if (countOfLuxury > capOfLuxury) {
            generateOutputTxt(new StringBuilder("Max Cap Exceeded For Luxury: Cap: " + capOfLuxury + ", Count of Luxury in the Order: " + countOfLuxury));
        } else if (countOfMisc > capOfMisc) {
            generateOutputTxt(new StringBuilder("Max Cap Exceeded For Misc: Cap: " + capOfMisc + ", Count of Misc in the Order: " + countOfMisc));
        } else if (next != null) {
            next.handleRequest(order, inventory);
        }

    }

    private void generateOutputTxt(StringBuilder reason) throws IOException {

        System.out.println("FAILURE! \nunsuccessful_order_out.txt is generated.");
        FileWriter fileWriter = new FileWriter("unsuccessful_order_output.txt", false);
        PrintWriter printWriter = new PrintWriter(fileWriter, true);

        printWriter.append(reason);
        printWriter.close();

    }

    @Override
    public void setNext(Handler nextHandler) {
        this.next = nextHandler;
    }
}
