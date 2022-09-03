package com.cmpe202;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class OrderSuccessfulHandler implements Handler{

    private Handler next;

    @Override
    public void handleRequest(Order order, Inventory inventory) throws IOException {

        Map<Order.OrderItem, Order.Status> orderItemStatusMap = order.getOrderItemStatusMap();

        boolean isSuccessfulOrder = true;

        for (Map.Entry<Order.OrderItem, Order.Status> entry : orderItemStatusMap.entrySet()) {

            if (entry.getValue().equals(Order.Status.FAILURE)) {
                isSuccessfulOrder = false;
                break;
            }

        }

        if (isSuccessfulOrder) {

            generateOutputCSV(order);
        } else {
            next.handleRequest(order, inventory);
        }

    }

    private void generateOutputCSV(Order order) throws IOException {

        System.out.println("SUCCESS! \nsuccessful_order_output.csv is generated.");
        FileWriter fileWriter = new FileWriter("successful_order_output.csv", false);
        PrintWriter printWriter = new PrintWriter(fileWriter, true);



        Map<Order.OrderItem, Order.Status> orderItemStatusMap = order.getOrderItemStatusMap();

        StringBuilder columnNames = new StringBuilder();
        columnNames.append("Item, Quantity, Price, TotalPrice\n");

        printWriter.append(columnNames);

        double totalPrice = 0;
        for (Map.Entry<Order.OrderItem, Order.Status> entry : orderItemStatusMap.entrySet()) {

            totalPrice = totalPrice + entry.getKey().getPrice();
        }

        boolean isTotalPriceWritten = false;

        for (Map.Entry<Order.OrderItem, Order.Status> entry : orderItemStatusMap.entrySet()) {

            Order.OrderItem orderItem = entry.getKey();

            String itemName = orderItem.getItemName();
            int quantity = orderItem.getQuantity();
            double price = orderItem.getPrice();

            StringBuilder output = new StringBuilder();

            output.append(itemName);
            output.append(",");
            output.append(quantity);
            output.append(",");
            output.append(price);

            if (!isTotalPriceWritten) {
                output.append(",");
                output.append(totalPrice);
                isTotalPriceWritten = true;
            }
            output.append("\n");
            printWriter.append(output);
        }


        printWriter.close();
    }

    @Override
    public void setNext(Handler nextHandler) {
        next = nextHandler;
    }
}
