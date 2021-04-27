
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class HomeWork2 {
    public static void main(String[] args) {

        //Задание 0
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Лада", "седан"));
        cars.add(new Car("Лада", "хэтчбек"));
        cars.add(new Car("Мерседес", "седан"));
        cars.add(new Car("БМВ", "кроссовер"));
        cars.add(new Car("Форд", "хэтчбек"));
        cars.add(new Car("Пежо", "кроссовер"));
        cars.add(new Car("Тойота", "седан"));

        Map<String, List<Car>> carsByType = cars.stream().collect(groupingBy(Car::getType, HashMap::new, toList()));
        System.out.println("Задание 0:");
        System.out.println(carsByType);

        //Задание 1
        String fileName = "G:\\IdeaProjects\\ForTests\\HomeWork2_Text.txt";
        System.out.println("Задание 1:");
        countWordsInFile(fileName);

        //Задание 2
        System.out.println("Задание 2:");
        sortByLength(fileName);

        //Задание 3
        System.out.println("Задание 3:");
        countEachWord(fileName);

        //Задание 4
        System.out.println("Задание 4:");
        reversStrings(fileName);

        //Задание 5
        System.out.println("Задание 5:");
        reversStringsMy(fileName);

        //Задание 6
        System.out.println("Задание 6:");
        lineByNumber(fileName, 2);
        lineByNumber(fileName, 0);
        lineByNumber(fileName, 6);
    }

    //Задание 1: Подсчитайте количество различных слов в файле
    public static void countWordsInFile(String fileName) {
        String text;

        try {
            text = new String(Files.readAllBytes(Paths.get(fileName)), "Cp1251");
            Set<String> setWords = new HashSet<>();
            String[] str = text.replace("\n\r", " ").split("\\s+");
            Collections.addAll(setWords, str);
            System.out.println("количество различных слов: " + setWords.size());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    //Задание 2: Выведите на экран список различных слов файла, отсортированный по возрастанию их длины
    public static void sortByLength(String fileName) {
        String text;

        try {
            text = new String(Files.readAllBytes(Paths.get(fileName)), "Cp1251");

            Set<String> setWords = new HashSet<>();
            Collections.addAll(setWords, text.replace("\n\r", " ").split("\\s+"));

            List<String> str = new ArrayList<>(setWords);
            Comparator<String> comparatorLength = Comparator.comparingInt(String::length);
            str.sort(comparatorLength);

            for (String s : str)
                System.out.println(s);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    //Задание 3: Подсчитайте и выведите на экран сколько раз каждое слово встречается в файле
    public static void countEachWord(String fileName) {
        String text;

        try {
            text = new String(Files.readAllBytes(Paths.get(fileName)), "Cp1251");
            String[] str = text.replace("\n\r", " ").split("\\s+");

            Map<String, Integer> mapWords = new HashMap<>();

            for (String tempWord : str) {
                if (mapWords.containsKey(tempWord)) {
                    mapWords.replace(tempWord, mapWords.get(tempWord) + 1);
                } else {
                    mapWords.put(tempWord, 1);
                }
            }
            System.out.println(mapWords);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    //Задание 4: Выведите на экран все строки файла в обратном порядке
    public static void reversStrings(String fileName) {

        try {
            List<String> str;
            str = Files.readAllLines(Paths.get(fileName), Charset.forName("Cp1251"));
            Collections.reverse(str);
            for (String s : str)
                System.out.println(s);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    //Задание 5: Реализуйте свой Iterator для обхода списка в обратном порядке
    public static void reversStringsMy(String fileName) {

        try {
            List<String> str;
            str = Files.readAllLines(Paths.get(fileName), Charset.forName("Cp1251"));

            ListIterator<String> myIterator = str.listIterator(str.size());

            while (myIterator.hasPrevious()) {
                String txt = myIterator.previous();
                System.out.println(txt);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    //Задание 6: Выведите на экран строки, номера которых задаются пользователем в произвольном порядке
    public static void lineByNumber(String fileName, int number) {

        try {
            List<String> str;
            str = Files.readAllLines(Paths.get(fileName), Charset.forName("Cp1251"));

            if (number >= str.size()) {
                System.out.println("Строка с таким номером в файле отсутствует");
            } else {
                System.out.print("Строка " + number + ": ");
                System.out.println(str.get(number));
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
