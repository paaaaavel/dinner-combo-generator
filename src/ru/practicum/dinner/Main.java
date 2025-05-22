package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static DinnerConstructor dinnerConstructor;
    private static Scanner scanner;

    public static void main(String[] args) {
        dinnerConstructor = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    System.out.println("Выход из программы.");
                    return;
                default:
                    System.out.println("Неизвестная команда, попробуйте снова.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();

        dinnerConstructor.addDish(dishType, dishName);

        System.out.println("Текущий список блюд:");
        printAllDishesFormatted();
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private static void printAllDishesFormatted() {
        HashMap<String, ArrayList<String>> allDishes = dinnerConstructor.getDishes();
        for (String type : allDishes.keySet()) {
            System.out.println(capitalize(type) + ": " + allDishes.get(type));
        }
    }

    private static void generateDishCombo() {
        ArrayList<String> types = new ArrayList<>();
        System.out.println("Начинаем конструировать обед...");

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку:");
        String nextItem = scanner.nextLine();

        while (!nextItem.isEmpty()) {
            if (!dinnerConstructor.isType(nextItem)) {
                System.out.println("Внимание: блюд типа \"" + nextItem + "\" нет в списке. Этот тип будет пропущен.");
            } else {
                types.add(nextItem);
            }
            nextItem = scanner.nextLine();
        }

        ArrayList<ArrayList<String>> combos = dinnerConstructor.generateCombos(numberOfCombos, types);
        for (int i = 0; i < combos.size(); i++) {
            System.out.println("Комбо " + (i + 1));
            System.out.println(combos.get(i));
        }
    }
}