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
        return new FreezerContent.FreezerItem(String.valueOf(position), "ID " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class FreezerItem {
        public final String id;
        public final String content;
        public final String details;
        public String name;
        public String address;
        public String phone;
        public String meal1;
        public String meal2;
        public String meal3;

        public FreezerItem(String id, String content, String details) {
            this.id = id;
            int i = Integer.parseInt(id);
            this.content = content;
            this.details = details;
            this.name = DataGenerator.generateName(i);
            this.address = DataGenerator.generateAddress(i);
            this.phone = "021 " + i + " 22 33" + i;
            this.meal1 = DataGenerator.generateMeals(i);
            i++;
            this.meal2 = DataGenerator.generateMeals(i);
            i++;
            this.meal3 = DataGenerator.generateMeals(i);

        }

        @Override
        public String toString() {
            return content;
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
                default:
                    return "Address:";
            }
        }

        public static String generateMeals(int index) {
            switch (index) {
                case 0:
                    return "Las x1";
                case 1:
                    return "M&C x2";
                case 2:
                    return "Bol x2";
                case 3:
                    return "Las x3";
                case 4:
                    return "M&C x4";
                case 5:
                    return "Bol x1";
                default:
                    return "Name:";
            }
        }
    }
}
