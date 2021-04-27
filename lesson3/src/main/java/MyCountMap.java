
import java.util.*;

public class MyCountMap<T> implements CountMap<T> {
    private final HashMap<T, Integer> countMap;

    public MyCountMap() {
        this.countMap = new HashMap<>();
    }

    @Override
    public void add(T o) {
        if (this.countMap.containsKey(o)) {
            this.countMap.replace(o, this.countMap.get(o) + 1);
        } else {
            this.countMap.put(o, 1);
        }
    }

    @Override
    public int getCount(T o) {
        return this.countMap.get(o);
    }

    @Override
    public int remove(T o) {
        int count = getCount(o);
        this.countMap.remove(o);
        return count;
    }

    @Override
    public int size() {
        return this.countMap.size();
    }

    @Override
    public void addAll(CountMap<? extends T> source) {
        addAllHelper(source.toMap());
    }

    private void addAllHelper(Map<? extends T, Integer> source) {
        for (T temp : source.keySet()) {
            if (this.countMap.containsKey(temp)) {
                this.countMap.replace(temp, this.countMap.get(temp) + source.get(temp));
            } else {
                this.countMap.put(temp, source.get(temp));
            }
        }
    }

    @Override
    public Map<T, Integer> toMap() {
        return this.countMap;
    }

    @Override
    public void toMap(Map<?, Integer> destination) {
        destination = this.countMap;
    }

    //печать элементов без значений (кочилества добавлений)
    public void printCountMap() {
        for (T element : this.countMap.keySet())
            System.out.print(element + " ");
        System.out.println();
    }
}
