import com.sber.streams.Person;
import com.sber.streams.Streams;
import org.junit.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class StreamsTest {
    private static List<Person> listPerson;

    @BeforeTest
    public static void BeforeTests() {
        listPerson = new ArrayList<>();
        listPerson.add(new Person("Анна", 30));
        listPerson.add(new Person("Петр", 21));
        listPerson.add(new Person("Евгений", 8));
        listPerson.add(new Person("Елена", 44));
        listPerson.add(new Person("Иван", 60));
    }

    @Test
    public void filterTest() {
        Map<String, Integer> mapPerson;
        mapPerson = Streams.of(listPerson)
                .filter(p -> p.getAge() > 25)
                .toMap(Person::getName, Person::getAge);
        System.out.println("New:");
        mapPerson.forEach((k, v) -> System.out.println("name = " + k + ", age = " + v));
        System.out.println("Old:");
        listPerson.forEach(p -> System.out.println("name = " + p.getName() + ", age = " + p.getAge()));
    }

    @Test
    public void transformTest() {
        Map<String, Integer> mapPerson;
        mapPerson = Streams.of(listPerson)
                .transform(p -> new Person(p.getName(), p.getAge() + 10))
                .toMap(Person::getName, Person::getAge);
        System.out.println("New:");
        mapPerson.forEach((k, v) -> System.out.println("name = " + k + ", age = " + v));
        System.out.println("Old:");
        listPerson.forEach(p -> System.out.println("name = " + p.getName() + ", age = " + p.getAge()));
    }

    @Test
    public void allTest() {
        Map<String, Integer> mapPerson;
        mapPerson = Streams.of(listPerson)
                .transform(p -> new Person(p.getName().toUpperCase(Locale.ROOT), p.getAge()))
                .filter(p -> p.getAge() > 25)
                .toMap(Person::getName, Person::getAge);
        System.out.println("New:");
        mapPerson.forEach((k, v) -> System.out.println("name = " + k + ", age = " + v));
        System.out.println("Old:");
        listPerson.forEach(p -> System.out.println("name = " + p.getName() + ", age = " + p.getAge()));
    }

    @AfterClass
    public static void AfterTests() {
        listPerson = null;
    }
}
