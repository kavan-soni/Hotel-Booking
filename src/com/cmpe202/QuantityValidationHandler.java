package com.cmpe202;

import java.io.IOException;
import java.util.Map;

public class QuantityValidationHandler implements Handler {

    private Handler next;

    @Override
    public void handleRequest(Order order, Inventory inventory) throws IOException {

        Map<Order.OrderItem, Order.Status> orderItemStatusMap = order.getOrderItemStatusMap();

        for (Map.Entry<Order.OrderItem, Order.Status> entry : orderItemStatusMap.entrySet()) {

            Order.OrderItem orderItem = entry.getKey();

            Component inventoryChild = inventory.getChild(orderItem.getItemName());

            if (orderItem.getQuantity() <= inventoryChild.quantity()) {
                entry.setValue(Order.Status.SUCCESS);

                double price = orderItem.getQuantity() * inventoryChild.costPerUnit();
                orderItem.setPrice(price);
            } else {
                entry.setValue(Order.Status.FAILURE);
            }

        }

        order.printOrder();

        if (next != null) {
            next.handleRequest(order, inventory);
        }
    }

    @Override
    public void setNext(Handler nextHandler) {
        this.next = nextHandler;

    }
}
