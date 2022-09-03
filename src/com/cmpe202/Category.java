package com.cmpe202;

public abstract class Category extends Leaf {

    protected final String itemName;
    protected final int quantity;
    protected final double costPerUnit;


    protected Category(String itemName, int quantity, double costPerUnit) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.costPerUnit = costPerUnit;
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
    public String itemName() {
        return itemName;
    }

    @Override
    public String toString() {
        return "{ itemName: " + itemName + ", quantity: " + quantity + ", costPerUnit: " + costPerUnit + " }";
    }
}
