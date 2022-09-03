package com.cmpe202;

import java.util.ArrayList;
import java.util.List;

public class CardDatabase {

    private final List<String> cards;
    private static CardDatabase cardDatabase;

    private CardDatabase() {
        cards = new ArrayList<>();
    }

    public static CardDatabase getCardDatabase() {

        if (cardDatabase == null) {
            cardDatabase = new CardDatabase();
        }
        return cardDatabase;
    }

    public void addIfAbsent(String cardNumber) {

        if(!cards.contains(cardNumber)) {
            cards.add(cardNumber);
        } else {
            System.out.println("Card is already present");
        }
    }

    public void printCardDatabase() {

        System.out.println("\n\n----------CARD DATABASE-----------");
        System.out.println("Cards : { " + cards + " }");
    }
}
