package com.cmpe202;

public class Misc extends Category{

    public Misc(String itemName, int quantity,double costPerUnit) {
        super(itemName, quantity, costPerUnit);
    }

    @Override
    public CategoryType getCategoryType() {
        return CategoryType.MISC;
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
