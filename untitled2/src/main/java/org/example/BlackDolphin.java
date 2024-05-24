package org.example;
import java.io.IOException;
import java.util.logging.Logger;
public class BlackDolphin extends Prison {
    private static Logger logger;

    static {
        try {
            Log.getInstance().initializeLogger(BlackDolphin.class, "BlackDolphin.log");
            logger = Log.getInstance().getLogger(BlackDolphin.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BlackDolphin() {
        super("Black Dolphin", 100, 10, "убийцы и маньяки", "тяжелое");
        logger.info("Тюрьма 'Чёрный Дельфин' создана");
    }

    public void changeName() {
        logger.info("Поменяйте название");
        System.out.print("Введите новое название тюрьмы: ");
        String newName = scanner.nextLine();
        if (newName.matches("[а-яА-Яa-zA-Z]+")) {
            setName(newName);
            logger.info("Название тюрьмы было изменено на " + getName());
            System.out.println("Название тюрьмы было изменено на " + getName());
        } else {
            logger.warning("Недопустимый ввод. Название тюрьмы должно состоять только из букв.");
            System.out.println("Недопустимый ввод. Название тюрьмы должно состоять только из букв.");
        }
    }

    @Override
    public void printMenu() {
        logger.info("Меню тюрьмы отображено");
        System.out.println("******************************************");
        System.out.println("*          " + getName() + " Prison          *");
        System.out.println("******************************************");
        System.out.println("1. Работать в тюрьме");
        System.out.println("2. Заключить нового заключенного");
        System.out.println("3. Ударить кого-то");
        System.out.println("4. Посетить библиотеку");
        System.out.println("5. Посмотреть список заключенных");
        System.out.println("6. Пригласить тату мастера");
        System.out.println("7. Посмотреть личное дело заключенного");
        System.out.println("8. Провести обыск");
        System.out.println("9. Провести слушание по досрочному освобождению");
        System.out.println("10. Секретная информация");
        System.out.println("11. Ребрендинг тюрьмы");
        System.out.println("12. Посмотреть текущую вместимость тюрьмы");
        System.out.println("******************************************");
        System.out.println("13. Выйти из программы");
    }

    public static void main(String[] args) {
        BlackDolphin blackDolphin = new BlackDolphin();
        boolean exit = false;

        while (!exit) {
            blackDolphin.printMenu();
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("******************************************");
                    blackDolphin.workInPrison();
                    break;
                case 2:
                    System.out.println("******************************************");
                    blackDolphin.admitPrisoner();
                    break;
                case 3:
                    System.out.println("******************************************");
                    blackDolphin.assaultPrisoner();
                    break;
                case 4:
                    System.out.println("******************************************");
                    blackDolphin.visitLibrary();
                    break;
                case 5:
                    System.out.println("******************************************");
                    blackDolphin.displayPrisoners();
                    break;
                case 6:
                    System.out.println("******************************************");
                    blackDolphin.inviteTattooArtist();
                    break;
                case 7:
                    System.out.println("******************************************");
                    blackDolphin.viewCriminalRecord();
                    break;
                case 8:
                    System.out.println("******************************************");
                    blackDolphin.drugSearch();
                    break;
                case 9:
                    System.out.println("******************************************");
                    blackDolphin.conductParoleHearing();
                    break;
                case 10:
                    System.out.println("******************************************");
                    blackDolphin.organizePrisonRiot();
                    break;
                case 11:
                    System.out.println("******************************************");
                    blackDolphin.changeName();
                    break;
                case 12:
                    System.out.println("******************************************");
                    System.out.println("Текущая вместимость тюрьмы (в процентах): " + blackDolphin.getCapacity());
                    break;
                case 13:
                    exit = true;
                    break;
                default:
                    System.out.println("Введите число в диапазоне от 1 до 13");
                    logger.warning("Ошибка: Некорректный ввод. Попробуйте еще раз.");
                    break;
            }
        }
    }
}