package main.java.datastructures.list;

import java.util.Arrays;
import java.util.Objects;

/**
 * A simplified, generic, resizable array-based list implementation.
 *
 * <p>Elements are stored in an internal array that doubles in capacity when full.
 * This implementation is designed for educational purposes and does
 * not implement the full Java Collections Framework.
 *
 * @param <E> the type of elements held in this list
 */
public class MyArrayList<E> implements MyList<E> {

    private int size;
    private E[] list;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        this.size = 0;
        this.list = (E[]) new Object[10]; // list.length is my capacity
    }

    @Override
    public boolean add(E element) {
        this.ensureCapacity();
        list[size] = element;
        size += 1;
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
        }

        this.ensureCapacity();

        for(int i = size - 1; i > index - 1; i--) {
            list[i + 1] = list[i];
        }

        list[index] = element;
        size += 1;
    }

    /**
     * Removes all the elements from this list.
     * The list will be empty after this call returns.
     * Time Complexity: O(N)
     */
    public void clear() {
        for(int i = 0; i < size; i++) {
            list[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
        }
        return list[index];
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
        }

        E removed = list[index];

        for(int i = index + 1; i < size; i++) {
            list[i - 1] = list[i];
        }

        list[size - 1] = null;
        size -= 1;
        return removed;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
        }

        E old = this.get(index);
        list[index] = element;
        return old;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(list, size));
    }

    @Override
    public boolean equals(Object obj) {
        // 1. Same object reference
        if (this == obj) return true;

        // 2. Null or not the same class
        if (obj == null || this.getClass() != obj.getClass()) return false;

        // <?> : wildcard --> avoids unchecked warnings and allows safe element comparison using Objects.equals()
        MyArrayList<?> list = (MyArrayList<?>) obj;

        // 3. Compare size
        if (this.size() != list.size()) return false;

        // 4. Compare elements
        for(int i = 0; i < this.size(); i++) {
            if(!Objects.equals(this.get(i), list.get(i))) {
                return false;
            }
        }

        return true;
    }


    @Override
    public int hashCode() {
       int hash = 1;
       for (int i = 0; i < this.size(); i++) {
           E element = this.get(i);
           hash = 31 * hash + Objects.hashCode(element);
       }
       return hash;
    }

    /**
     * Doubles the capacity of the list when full,
     * If the current size is 0, capacity is increased to 1.
     * Time Complexity: O(N)
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (size == list.length) {
            int newCapacity = Math.max(1, 2 * size);
            E[] newList = (E[]) new Object[newCapacity];

            System.arraycopy(list, 0, newList, 0, size);

            list = newList;
        }
    }
}
