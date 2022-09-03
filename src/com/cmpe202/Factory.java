package com.cmpe202;

public class Factory {

    public static Component create(String itemName, String category, String quantity, String costPerUnit) {

        itemName = itemName.trim();
        CategoryType categoryType = CategoryType.valueOf(category.trim().toUpperCase());

        int quantityInt = Integer.parseInt(quantity.trim());
        double costPerUnitDouble = Double.parseDouble(costPerUnit.trim());

        switch (categoryType) {

            case ESSENTIALS:
                return new Essentials(itemName, quantityInt, costPerUnitDouble);
            case MISC:
                return new Misc(itemName, quantityInt, costPerUnitDouble);
            case LUXURY:
                return new Luxury(itemName, quantityInt, costPerUnitDouble);
            default:
                throw new IllegalStateException("Unexpected value: " + categoryType);
        }

    }

}
