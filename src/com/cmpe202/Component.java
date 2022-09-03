package com.cmpe202;

public interface Component {
    //Operation
    public String itemName();
    public int quantity();
    public double costPerUnit();

    public void add(Component component);
    public void remove(Component component);
    public Component getChild(String name);

    CategoryType getCategoryType();
}
