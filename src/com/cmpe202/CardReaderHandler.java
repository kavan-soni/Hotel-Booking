package com.cmpe202;

import java.io.IOException;
import java.util.List;

public class CardReaderHandler implements Handler{

    private Handler next;
    private final CardDatabase cardDatabase;

    public CardReaderHandler(CardDatabase cardDatabase) {
        this.cardDatabase = cardDatabase;
    }

    @Override
    public void handleRequest(Order order, Inventory inventory) throws IOException {

        List<String> cardNumbers = order.getCardNumbers();

        for (String cardNumber : cardNumbers) {
            cardDatabase.addIfAbsent(cardNumber); // Card is added in the card database if absent
        }

        System.out.println("Card is store if not present.\n");
        cardDatabase.printCardDatabase();

        if(next != null) {
            next.handleRequest(order, inventory);
        }
    }

    @Override
    public void setNext(Handler nextHandler) {
            next = nextHandler;
    }
}
