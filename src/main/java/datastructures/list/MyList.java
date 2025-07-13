package main.java.datastructures.list;

/**
 * A simplified custom List interface defining core list operations.
 *
 * @param <E> -- the type of elements in the list
 */
public interface MyList<E> {
    int size();
    boolean isEmpty();
    E get(int index);
    void add(E item);
    void add(int index, E item);
    E set(int index, E item);
    boolean remove(int index);
}