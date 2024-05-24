package org.example;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public abstract class Prison {
    private static final Logger LOGGER = Logger.getLogger(Prison.class.getName());
    private String name;
    private int capacity;
    private int age;
    private String crime;
    private static int prisonerCount;
    protected static Scanner scanner;
    private static String[] prisoners;
    public String crimeSeverity;

    static {
        try {
            FileHandler fileHandler = new FileHandler(Prison.class.getSimpleName() + ".log", true);
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.ALL);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Ошибка при инициализации регистратора", e);
        }
    }

    public Prison(String name, int capacity, int age, String crime, String crimeSeverity) {
        this.name = name;
        this.capacity = capacity;
        this.age = age;
        this.crime = crime;
        this.crimeSeverity = crimeSeverity;
        this.prisonerCount = 0;
        this.prisoners = new String[capacity];

        try {
            scanner = new Scanner(System.in);
        } catch (Exception e) {
            LOGGER.severe("Ошибка при инициализации");
        }

        LOGGER.info("Обьект тюрьма создан");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity - (int) (prisonerCount * 0.9);
    }

    void viewCriminalRecord() {
        LOGGER.info("viewCriminalRecord метод вызван");
        System.out.println("Кого заключенного хотите посмотреть?");
        displayPrisoners();
        System.out.print("Введите имя заключенного: ");

        String input = scanner.next();

        if (input.matches("[а-яА-Яa-zA-Z]+")) {
            for (int i = 0; i < prisonerCount; i++) {
                if (prisoners[i].startsWith(input)) {
                    String[] record = prisoners[i].split(" \\(");
                    String name = record[0];
                    System.out.println("Имя: " + name);
                    System.out.println("Степень тяжести преступления: " + crimeSeverity);
                    return;
                }
            }
            System.out.println("Такого заключенного нет.");
        } else {
            LOGGER.severe("Ошибка: имя заключенного должно состоять только из букв");
            System.out.println("Имя заключенного должно состоять только из букв");
        }
    }
    public void workInPrison() {
        LOGGER.info("workInPrison метод вызван");
        System.out.println("Вы работаете в тюрьме.");
    }

    public void assaultPrisoner() {
        LOGGER.info("assaultPrisoner метод вызван");
        System.out.println("Кого Вы хотите избить?");
        displayPrisoners();
        System.out.print("Введите номер заключенного (от 1 до " + prisonerCount + "): ");

        try {
            int target = scanner.nextInt();

            if (target >= 1 && target <= prisonerCount && prisoners[target - 1] != null) {
                System.out.println("Вы избили до полусмерти " + prisoners[target - 1] + ". Он отправлен в карцер.");
            } else {
                System.out.println("Такого заключенного нет.");
            }
        } catch (InputMismatchException e) {
            LOGGER.log(Level.SEVERE, "Ошибка считывания входных данных", e);
            System.out.println("Ошибка: неверный ввод.");
            scanner.nextLine();
        }
    }

    public void visitLibrary() {
        LOGGER.info("visitLibrary метод вызван");
        System.out.println("Вы отправились в библиотеку для занятий.");
    }

    public void admitPrisoner() {
        if (prisonerCount < capacity) {
            System.out.print("Введите имя нового заключенного: ");
            while (true) {
                String name = scanner.next();
                if (name.matches("^[a-zA-Zа-яА-Я\\s]{1,50}$")) {
                    scanner.nextLine();
                    System.out.print("За что сел в тюрьму " + name + ": ");
                    String crime = scanner.nextLine();
                    if (crime.matches("^[a-zA-Zа-яА-Я\\s]{1,200}$")) {
                        prisoners[prisonerCount++] = name + " (" + crime + ")";
                        System.out.println(name + " был заключен в тюрьму под номером " + prisonerCount);
                        break;
                    } else {
                        System.out.println("Ошибка: описание преступления должно состоять только из букв и содержать не более 200 символов.");
                        LOGGER.severe("Ошибка добавления заключенного");
                    }
                } else {
                    System.out.println("Ошибка: имя должно состоять только из букв и содержать не более 50 символов.");
                    LOGGER.severe("Ошибка добавления заключенного");
                }
            }
        } else {
            System.out.println("Тюрьма заполнена. Невозможно заключить нового заключенного.");
        }
    }

    public static void displayPrisoners() {
        LOGGER.info("displayPrisoners метод вызван");
        System.out.println("Список заключенных:");

        if (prisonerCount == 0) {
            System.out.println("Список пуст.");
            return;
        }

        for (int i = 0; i < prisonerCount; i++) {
            System.out.println((i + 1) + ". " + prisoners[i]);
            System.out.println("--------------------------------");
        }
    }


    public static void inviteTattooArtist() {
        LOGGER.info("inviteTattooArtist метод вызван");
        System.out.print("Введите имя тату мастера: ");
        while (true) {
            String artistName = scanner.next();
            if (artistName.matches("^[a-zA-Zа-яА-Я\\s]+$")) {
                scanner.nextLine();
                System.out.print("За что сидит в тюрьме тату мастер " + artistName + ": ");
                String artistCrime = scanner.nextLine();
                if (artistCrime.matches("^[a-zA-Zа-яА-Я\\s]+$")) {
                    try {
                        System.out.println("Тату мастер " + artistName + " сидящий за: " + artistCrime + " принят на работу в тюрьму.");
                    } catch (Exception e) {
                        LOGGER.log(Level.SEVERE, "Ошибка в приглашении татуировщика", e);
                    }
                    break;
                } else {
                    System.out.println("Ошибка: описание преступления должно состоять только из букв и пробелов.");
                    LOGGER.severe("Ошибка в приглашении татуировщика");
                }
            } else {
                System.out.println("Ошибка: имя тату мастера должно состоять только из букв и пробелов.");
                LOGGER.severe("Ошибка в приглашении татуировщика");
            }
        }
    }

    public void conductParoleHearing() {
        LOGGER.info("conductParoleHearing метод вызван");
        System.out.println("Проводится слушание по условно-досрочному освобождению.");
    }

    public static void drugSearch() {
        LOGGER.info("drugSearch метод вызван");
        System.out.println("Проводится обыск в поисках наркотиков.");
    }

    public static void organizePrisonRiot() {
        LOGGER.info("organizePrisonRiot метод вызван");
        System.out.println("Организуется восстание в тюрьме.");
    }

    public abstract void printMenu();
}