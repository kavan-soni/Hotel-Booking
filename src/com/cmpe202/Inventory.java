package com.cmpe202;

import java.util.HashMap;
import java.util.Map;

public class Inventory implements Component {


    public Inventory() {
    }

    private Map<String, Component> inventoryMap = new HashMap<>();

    @Override
    public void add(Component component) {
        inventoryMap.put(component.itemName().toLowerCase(), component);

    }

    @Override
    public void remove(Component component) {
        inventoryMap.remove(component);

    }

    @Override
    public Component getChild(String name) {
        name = name.toLowerCase();
        return inventoryMap.get(name);
    }

    @Override
    public CategoryType getCategoryType() {
        throw new UnsupportedOperationException();
    }


    @Override
    public String itemName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int quantity() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double costPerUnit() {
        throw new UnsupportedOperationException();
    }

    public void printInventory() {

        System.out.println("\n\n--------------------PRINTING OUR INVENTORY------------------------");
        for (Map.Entry<String, Component> entry : inventoryMap.entrySet()) {

            System.out.println("Key -> " + entry.getKey() + ", value -> " + entry.getValue());
        }

    }
}
