package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class DinnerConstructor {
    Random random = new Random();
    HashMap<String, ArrayList<String>> dishes = new HashMap<>();

    public void printAllDishes() {
        for (String type : dishes.keySet()) {
            System.out.println(type + ": " + dishes.get(type));
        }
    }

    public String getRandomDishByType(String type) {
        type = type.toLowerCase();
        if (dishes.containsKey(type)) {
            ArrayList<String> dishList = dishes.get(type);
            if (!dishList.isEmpty()) {
                return dishList.get(random.nextInt(dishList.size()));
            }
        }
        return null;
    }

    public HashMap<String, ArrayList<String>> getDishes() {
        return dishes;
    }

    public void addDish(String type, String nameOfDish) {
        type = type.toLowerCase();
        dishes.putIfAbsent(type, new ArrayList<>());
        dishes.get(type).add(nameOfDish);
    }

    public boolean isType(String type) {
        return dishes.containsKey(type.toLowerCase());
    }

    public ArrayList<ArrayList<String>> generateCombos(int numberOfCombos, ArrayList<String> types) {
        ArrayList<ArrayList<String>> combos = new ArrayList<>();

        for (int i = 0; i < numberOfCombos; i++) {
            ArrayList<String> combo = new ArrayList<>();
            for (String type : types) {
                String dish = getRandomDishByType(type);
                if (dish != null) {
                    combo.add(dish);
                } else {
                    combo.add("[нет блюда]");
                }
            }
            combos.add(combo);
        }

        return combos;
    }
}