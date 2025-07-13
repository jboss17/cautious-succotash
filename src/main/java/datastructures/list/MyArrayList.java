package main.java.datastructures.list;
import java.util.Arrays;

/**
 * A simplified, generic, resizable array-based list implementation.
 *
 * <p>This class mimics the core functionality of {@link java.util.ArrayList},
 * supporting dynamic resizing, index-based access, and common list operations
 * like add, insert, remove, and get.
 *
 * <p>Elements are stored in an internal array that doubles in capacity when full.
 * This implementation is designed for educational purposes and does
 * not implement the full Java Collections Framework.
 *
 * @param <E> the type of elements held in this list
 */
public class MyArrayList<E> {

    private int size;
    private E[] list;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        this.size = 0;
        this.list = (E[]) new Object[10]; // list.length is my capacity
    }

    /**
     * Appends the specified element to the end of the list
     *
     * @param element -- the element to be appended to this list
     * @return true (as specified by {@link java.util.Collection#add}
     */
    public boolean add(E element) {
        this.ensureCapacity();
        list[size] = element;
        size += 1;
        return true;
    }

    /**
     * Inserts the specified element at the specified index in the list.
     * Shifts the element currently at that index (if any) and any subsequent elements to the right (adds one to their indices).
     *
     * @param index -- index at which the specified element is to be inserted
     * @param element -- element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     */
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
     */
    public void clear() {
        for(int i = 0; i < size; i++) {
            list[i] = null;
        }
        size = 0;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index -- the index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
        }
        return list[index];
    }

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     *
     * @param index -- the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
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

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index -- index of the element to replace
     * @param element -- element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
        }

        E old = this.get(index);
        list[index] = element;
        return old;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Trims the capacity of this ArrayList instance to be the list's current size.
     * An application can use this operation to minimize the storage of an ArrayList instance.
     */
    @SuppressWarnings("unchecked")
    public void trimToSize() {
        if (size < list.length) {
            E[] newList = (E[]) new Object[size];
            System.arraycopy(list, 0, newList, 0, size);
            list = newList;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(list, size));
    }

    /**
     * Doubles the capacity of the list when full.
     * If the current size is 0, capacity is increased to 1.
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
