
import java.util.*;

public class HomeWork3 {
    public static void main(String[] args) {

        //Задание 1
        //--------------------------------------------------
        System.out.println("Задание 1:");
        System.out.println("--------------------------------------------------");
        MyCountMap<? super Integer> map = new MyCountMap<>();

        //добавляет элемент в этот контейнер
        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        //Возвращает количество добавлений данного элемента
        int count = map.getCount(5); // 2
        System.out.println("Count 5: " + count);
        count = map.getCount(6); // 1
        System.out.println("Count 6: " + count);
        count = map.getCount(10); // 3
        System.out.println("Count 10: " + count);

        //Удаляет элемент и контейнера и возвращает количество его добавлений(до удаления)
        count = map.remove(5);
        System.out.println("Remove 5: " + count);
        System.out.println("MyCountMap: ");
        map.printCountMap();

        //количество разных элементов
        count = map.size();
        System.out.println("Count all unique: " + count);

        //Добавить все элементы из source в текущий контейнер,
        //при совпадении ключей, суммировать значения
        MyCountMap<Integer> mapForAdd = new MyCountMap<>();
        mapForAdd.add(7);
        mapForAdd.add(1);
        mapForAdd.add(5);
        mapForAdd.add(1);

        map.addAll(mapForAdd);
        System.out.println("MyCountMap: ");
        map.printCountMap();

        //Вернуть java.util.Map. ключ - добавленный элемент,
        //значение - количество его добавлений
        Map<? super Integer, Integer> mapCopy;
        mapCopy = map.toMap();
        System.out.println("mapCopy: ");
        System.out.println(mapCopy);

        //всю информацию записать в destination
        Map<? super Integer, Integer> mapCopy2 = new HashMap<>();
        map.toMap(mapCopy2);
        System.out.println("mapCopy2: ");
        map.printCountMap();
        System.out.println(mapCopy);

        //Задание 2
        //--------------------------------------------------
        System.out.println("--------------------------------------------------");
        System.out.println("Задание 2:");
        System.out.println("--------------------------------------------------");

        List<Integer> source = new ArrayList<>(Arrays.asList(8, 1, 3, 5, 6, 4));
        List<Integer> list = CollectionUtils.newArrayList();

        CollectionUtils.addAll(source, list);
        System.out.println("CollectionUtils.addAll:");
        System.out.println(list);

        System.out.print("CollectionUtils.indexOf 5: ");
        System.out.println(CollectionUtils.indexOf(list, 5));
        System.out.print("CollectionUtils.indexOf 2: ");
        System.out.println(CollectionUtils.indexOf(list, 2));

        System.out.println("CollectionUtils.limit:");
        System.out.println(CollectionUtils.limit(list, 4));

        System.out.println("CollectionUtils.add:");
        CollectionUtils.add(list, 7);
        CollectionUtils.add(list, 1);
        System.out.println(list);

        System.out.println("CollectionUtils.removeAll (3,5):");
        CollectionUtils.removeAll(list, Arrays.asList(3, 5));
        System.out.println(list);

        System.out.println("CollectionUtils.containsAll (6,8):");
        System.out.println(CollectionUtils.containsAll(list, Arrays.asList(6, 8)));
        System.out.println("CollectionUtils.containsAll (3,8):");
        System.out.println(CollectionUtils.containsAll(list, Arrays.asList(3, 8)));

        System.out.println("CollectionUtils.containsAny (3,8):");
        System.out.println(CollectionUtils.containsAny(list, Arrays.asList(3, 8)));
        System.out.println("CollectionUtils.containsAny (3,5):");
        System.out.println(CollectionUtils.containsAny(list, Arrays.asList(3, 5)));

        System.out.println("CollectionUtils.range (4,7):");
        System.out.println(CollectionUtils.range(list, 4, 7));

        System.out.println("CollectionUtils.range (4,7):");
        Comparator<Integer> comparator = Comparator.naturalOrder();
        System.out.println(CollectionUtils.range(list, 4, 7, comparator));
    }
}
