import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;


public class homework_6 {
    public static void main(String[] args) {

        mainMenuChoice();
    }

    private static HashMap<String, ArrayList<Integer>> phoneBook = new HashMap<>();

    public static void mainMenuChoice() {
        System.out.println("- Главное меню телефонной книги -");
        System.out.println();
        System.out.println("- 1. Просмотр списка контактов -");
        System.out.println("- 2. Найти телефон по ФИО -");
        System.out.println("- 3. Добавить новый контакт/телефонный номер -");
        System.out.println("- 0. Выход из телефонной книги -");
        System.out.println();
        System.out.println("Выберите пункт меню");

        try (Scanner scanner = new Scanner(System.in)) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    /* Вывод списка контактов */
                    System.out.println("Контакты телефонной книги:");                  
                    System.out.println(sortTheMap());  // Сортировка по количеству телефонов не работает
                    System.out.println();
                    mainMenuChoice();

                case 2:
                    /* Поиск контакта */
                    Scanner findScanner = new Scanner(System.in);
                    System.out.println();
                    System.out.println("- Введите ФИО -");
                    String findname = findScanner.nextLine();
                    System.out.println("Найденные телефоны контакта:");
                    System.out.println(find(findname));
                    System.out.println();
                    mainMenuChoice();

                case 3:
                    /* Создаём Новый контакт */
                    Scanner newContScanner = new Scanner(System.in);
                    System.out.println();
                    System.out.println("- Введите ФИО -");
                    String newLastname = newContScanner.nextLine();
                    System.out.println("- Введите номер телефона -");
                    Integer newPhone = newContScanner.nextInt();

                    add(newLastname, newPhone);
                    System.out.println("Внесены изменения в телефонную книгу");
                    System.out.println();
                    mainMenuChoice();

                case 0:
                    /* Выход из программы */
                    System.out.println();
                    System.out.println("Выходим...");
                    break;

                default:
                    mainMenuChoice();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }

    public static ArrayList<Integer> find(String name) {
        if (phoneBook.containsKey(name))
            return phoneBook.get(name);
        else
            return new ArrayList<>();

    }

    public static HashMap<String, ArrayList<Integer>> getPhoneBook() {

        return phoneBook;
    }

    public static void add(String name, Integer phoneNum) {

        if (phoneBook.containsKey(name)) {
            ArrayList<Integer> list = phoneBook.get(name);
            list.add(phoneNum);
        } else {
            ArrayList<Integer> listStr = new ArrayList<>();
            listStr.add(phoneNum);
            phoneBook.put(name, listStr);
        }
    }

    public static Map<Object, Object> sortTheMap() {

        Map<Object, Object> sorted = phoneBook.entrySet().stream()
                .sorted(comparingInt(e -> e.getValue().size()))
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new));
                        return sorted;
    }
}
