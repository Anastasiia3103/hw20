package hw20;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadSafeList<T> {
    private final List<T> list;
    public ThreadSafeList() {
        list = Collections.synchronizedList(new ArrayList<>());
    }
    public void add(T item) {
        list.add(item);
    }
    public void remove(T item) {
        list.remove(item);
    }
    public T get(int index) {
        return list.get(index);
    }
}
