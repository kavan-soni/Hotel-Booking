package com.cmpe202;

import java.io.IOException;

public interface Handler {
    public void handleRequest(Order order, Inventory inventory) throws IOException;
    public void setNext(Handler nextHandler);
}
