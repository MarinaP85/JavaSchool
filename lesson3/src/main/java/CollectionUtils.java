
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionUtils {
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }

    public static <T> List<? extends T> limit(List<? extends T> source, int size) {
        return source.stream().limit(size).collect(Collectors.toList());
    }

    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? extends T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static <T> boolean containsAll(List<? extends T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    public static <T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
        boolean check = false;
        for (T temp : c2) {
            if (c1.contains(temp)) {
                check = true;
                break;
            }
        }
        return check;
    }

    public static <T extends Comparable<? super T>> List<? extends T> range(List<? extends T> list, T min, T max) {
        return list.stream().sorted().filter(el -> (el.compareTo(min) >= 0) && (el.compareTo(max) <= 0)).collect(Collectors.toList());
    }

    public static <T extends Comparable<? super T>> List<? extends T> range(List<? extends T> list, T min, T max, Comparator<? super T> comparator) {
        return list.stream().sorted(comparator).filter(el -> (el.compareTo(min) >= 0) && (el.compareTo(max) <= 0)).collect(Collectors.toList());
    }

}
