package com.cmpe202;

public class Essentials extends Category {


    public Essentials(String itemName, int quantity, double costPerUnit) {
        super(itemName, quantity, costPerUnit);
    }

    @Override
    public CategoryType getCategoryType() {
        return CategoryType.ESSENTIALS;
    }


    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Component getChild(String name) {
        throw new UnsupportedOperationException();
    }
}
