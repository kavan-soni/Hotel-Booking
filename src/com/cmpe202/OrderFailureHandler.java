package com.cmpe202;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class OrderFailureHandler implements Handler {

    private Handler next;

    @Override
    public void handleRequest(Order order, Inventory inventory) throws IOException {

        Map<Order.OrderItem, Order.Status> orderItemStatusMap = order.getOrderItemStatusMap();

        StringBuilder failureItems = new StringBuilder();

        for (Map.Entry<Order.OrderItem, Order.Status> entry : orderItemStatusMap.entrySet()) {

            if (entry.getValue().equals(Order.Status.FAILURE)) {
                failureItems.append(entry.getKey().getItemName());
                failureItems.append(", ");
            }

        }

        generateOutputCSV(order, failureItems);


    }

    private void generateOutputCSV(Order order, StringBuilder failureItems) throws IOException {

        System.out.println("FAILURE! \nunsuccessful_order_out.csv is generated.");
        FileWriter fileWriter = new FileWriter("unsuccessful_order_output.txt", false);
        PrintWriter printWriter = new PrintWriter(fileWriter, true);

        printWriter.append("Please Correct Quantities : ").append(String.valueOf(failureItems));
        printWriter.close();

    }

    @Override
    public void setNext(Handler nextHandler) {
        next = nextHandler;
    }
}
