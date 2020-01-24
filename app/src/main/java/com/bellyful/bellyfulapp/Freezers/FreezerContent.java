package com.bellyful.bellyfulapp.Freezers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreezerContent {
    /**
     * An array of sample freezer items.
     */
    public static final List<FreezerContent.FreezerItem> ITEMS = new ArrayList<>();

    /**
     * A map of sample freezer items, by ID.
     */
    public static final Map<String, FreezerContent.FreezerItem> ITEM_MAP = new HashMap<>();

    private static final int COUNT = 4;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createFreezerItem(i));
        }
    }

    private static void addItem(FreezerContent.FreezerItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static FreezerContent.FreezerItem createFreezerItem(int position) {
        return new FreezerContent.FreezerItem(String.valueOf(position));
    }


    /**
     * A dummy item representing a piece of content.
     */
    public static class FreezerItem {
        public final String id;
        public String name;
        public String address;
        public String phone;
        public String meal1;
        public String mealQty1;
        public String meal2;
        public String mealQty2;
        public String meal3;
        public String mealQty3;

        public FreezerItem(String id) {
            this.id = id;
            int i = Integer.parseInt(id);
            this.name = DataGenerator.generateName(i);
            this.address = DataGenerator.generateAddress(i);
            this.phone = "021 " + i + " 22 33" + i;
            this.meal1 = DataGenerator.generateMeals(i);
            this.mealQty1 = DataGenerator.generateMealQty(i);
            i++;
            this.meal2 = DataGenerator.generateMeals(i);
            this.mealQty2 = DataGenerator.generateMealQty(i);
            i++;
            this.meal3 = DataGenerator.generateMeals(i);
            this.mealQty3 = DataGenerator.generateMealQty(i);

        }
    }


    static class DataGenerator{
        public static String generateName(int index){
            switch (index) {
                case 0:
                    return "Suzanne";
                case 1:
                    return "George";
                case 2:
                    return "Mary";
                case 3:
                    return "Ashley";
                case 4:
                    return "John";
                case 5:
                    return "Lee";
                case 6:
                    return "Steve";
                default:
                    return "Name:";
            }
        }

        public static String generateAddress(int index){
            switch (index) {
                case 0:
                    return "123 West St";
                case 1:
                    return "2A Beach Rd";
                case 2:
                    return "Unit 3a, 22 High Pd";
                case 3:
                    return "32 Oscar Pl";
                case 4:
                    return "21 Koru Dr";
                case 5:
                    return "13B Bays Cr";
                case 6:
                    return "24 North St";
                default:
                    return "Address:";
            }
        }

        public static String generateMeals(int index) {
            switch (index) {
                case 0:
                    return "Las";
                case 1:
                    return "M&C";
                case 2:
                    return "Bol";
                case 3:
                    return "Veg Soup";
                case 4:
                    return "Chick Soup";
                case 5:
                    return "Veg Las";
                case 6:
                    return "Curry";
                default:
                    return "Name:";
            }
        }

        public static String generateMealQty(int index) {
            switch (index) {
                case 0:
                    return "1";
                case 1:
                    return "2";
                case 2:
                    return "3";
                case 3:
                    return "4";
                case 4:
                    return "5";
                case 5:
                    return "6";
                case 6:
                    return "7";
                default:
                    return "Qty:";
            }
        }
    }
}
