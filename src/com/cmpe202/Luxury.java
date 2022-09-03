package com.cmpe202;

public class Luxury extends Category {

    public Luxury(String itemName, int quantity, double costPerUnit) {
        super(itemName, quantity, costPerUnit);
    }

    @Override
    public int quantity() {
        return quantity;
    }

    @Override
    public double costPerUnit() {
        return costPerUnit;
    }

    @Override
    public CategoryType getCategoryType() {
        return CategoryType.LUXURY;
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
