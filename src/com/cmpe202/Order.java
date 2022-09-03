package com.cmpe202;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    private final Map<OrderItem, Status> orderItemStatusMap;
    private final List<String> cardNumbers;

    public Order() {
        orderItemStatusMap = new HashMap<>();
        cardNumbers = new ArrayList<>();
    }

    public void putOrderItem(OrderItem orderItem) {
        this.orderItemStatusMap.put(orderItem, Status.INIT);
    }

    public void changeOrderItemStatus(OrderItem orderItem, Status status) {

        if (orderItemStatusMap.containsKey(orderItem)) {
            orderItemStatusMap.put(orderItem, status);
        } else {
            throw new IllegalStateException("OrderItem is not present.");
        }
    }

    public Map<OrderItem, Status> getOrderItemStatusMap() {
        return orderItemStatusMap;
    }

    public List<String> getCardNumbers() {
        return cardNumbers;
    }

    public void addCardNumber(String cardNumber) {
        this.cardNumbers.add(cardNumber);
    }

    public static class OrderItem {
        private final String itemName;
        private final int quantity;
        private double price;



        public OrderItem(String itemName, String quantity) {
            this.itemName = itemName;
            this.quantity = Integer.parseInt(quantity);
            this.price = 0.0; // will be set later
        }

        public String getItemName() {
            return itemName;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "{ itemName: " + itemName + ", quantity: " + quantity + "}";
        }
    }

    public enum Status {
        INIT, SUCCESS, FAILURE
    }

    public void printOrder() {

        System.out.println("\n\n--------------------PRINTING OUR ORDER------------------------");
        for (Map.Entry<OrderItem, Status> entry : orderItemStatusMap.entrySet()) {

            System.out.println("Key -> " + entry.getKey() + ", value -> " + entry.getValue());
        }

        System.out.println("\n\n----------CARD NUMBERS IN THE ORDER-----------");
        System.out.println("Cards : { " + cardNumbers + " }");

    }

}
