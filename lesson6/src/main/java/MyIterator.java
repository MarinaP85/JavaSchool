import java.util.Iterator;

public class MyIterator<T> implements Iterator<T> {
    private int index;
    private final T[] array;

    public MyIterator(T[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return index < this.array.length;
    }

    @Override
    public T next() {
        try {
            return this.array[index++];
        } catch (Exception e) {
            return null;
        }

    }
}
