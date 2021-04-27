
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeWork {
    public static void main(String[] args) {

        //Задание 1
        System.out.println("Задание 1:");
        System.out.println("check year = " + checkYear(1900));
        System.out.println("check year = " + checkYear(2008));
        System.out.println("check year = " + checkYear(2000));

        //Задание 2
        int maxWeight = 17;
        List<Integer> boxes = new ArrayList<>();
        boxes.add(6);
        boxes.add(7);
        boxes.add(5);
        boxes.add(1);
        boxes.add(9);

        System.out.println("Задание 2:");
        loadTruck(maxWeight, boxes);

        //Задание 3
        int[] numbers = new int[]{4, 6, 1, 9, 1, 2, 2};
        System.out.println("Задание 3:");
        nearLarger(numbers);

        //Задание 4
        numbers = new int[]{4, 1, 3, 0, 4, 1, 1};
        System.out.println("Задание 4:");
        countNumbers(numbers, 5);

        //Задание 5
        String text = ",as,y,,tyh,u,irohg,,";
        System.out.println("Задание 5:");
        text = shortWords(text, 2);
        System.out.println(text);

        //Задание 6
        text = ",rfrm ,tfr.fr. ";
        String patternStr = "fr?";
        System.out.println("Задание 6:");
        searchPattern(text, patternStr);

        //Задание 7
        boolean married = false;
        Person ivan = new Person(true, "Ivan Petrov");
        Person anna = new Person(false, "Anna Sidorova");
        Person petr = new Person(true, "Petr Alexeev");
        Person elena = new Person(false, "Elena Nikolaeva");
        System.out.println(ivan.getName() + ": status - " + married + " spouse - " + ivan.getSpouseName());
        married = ivan.marry(anna);
        System.out.println(ivan.getName() + ": status - " + married + " spouse - " + ivan.getSpouseName());
        married = ivan.marry(petr);
        System.out.println(ivan.getName() + ": status - " + married + " spouse - " + ivan.getSpouseName());
        married = elena.marry(petr);
        System.out.println(elena.getName() + ": status - " + married + " spouse - " + elena.getSpouseName());
        married = elena.marry(ivan);
        System.out.println(elena.getName() + ": status - " + married + " spouse - " + elena.getSpouseName());
        married = elena.marry(ivan);
        System.out.println(elena.getName() + ": status - " + married + " spouse - " + elena.getSpouseName());
        System.out.println(ivan.getName() + ": spouse - " + ivan.getSpouseName());
        System.out.println(anna.getName() + ": spouse - " + anna.getSpouseName());
        System.out.println(petr.getName() + ": spouse - " + petr.getSpouseName());
    }

    //2004. Високосный год
    public static int checkYear(int year) {

        if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
            return 1;
        } else {
            return 0;
        }

    }

    //2008. Загрузка грузовика
    public static void loadTruck(int maxWeight, List<Integer> boxes) {
        int count = 0;
        int weight = 0;

        for (int box : boxes) {
            if (weight + box <= maxWeight) {
                weight = weight + box;
                count++;
            }
        }

        System.out.println("count = " + count);
        System.out.println("weight = " + weight);
    }

    //2026. Ближайший справа больший
    public static void nearLarger(int[] numbers) {
        int[] resultArray = new int[numbers.length];
        int num;
        for (int i = 0; i < numbers.length; i++) {
            num = 0;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] > numbers[i]) {
                    num = numbers[j];
                    break;
                }
            }
            resultArray[i] = num;
            System.out.print(resultArray[i] + " ");
        }
        System.out.println();
    }

    //2028. Числа 0-4
    public static void countNumbers(int[] numbers, int length) {
        int[] countNumbers = new int[length];
        for (int num : numbers) {
            if (num < countNumbers.length) {
                countNumbers[num]++;
            }
        }

        for (int i = 0; i < countNumbers.length; i++) {
            if (countNumbers[i] > 0) {
                System.out.println(i + " " + countNumbers[i]);
            }
        }
    }

    //2037. Строки. Слишком короткие слова
    public static String shortWords(String text, int length) {
        StringBuilder result = new StringBuilder();

        String[] words = text.split(",");
        for (String word : words) {
            if (word.length() >= length) {
                result.append(" ").append(word);
            }
        }
        return result.toString().trim().replace(' ', ',');
    }

    //2042. Строки. Поиск образца в тексте
    public static void searchPattern(String text, String patternStr) {
        int i;
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(text);

        System.out.print("позиции вхождения шаблона: ");
        while (matcher.find()) {
            i = matcher.start() + 1;
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
