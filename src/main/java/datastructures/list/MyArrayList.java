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

    /**
     * Appends the specified element to the end of the list.
     * Time Complexity: O(N)
     *
     * @param element -- the element to be appended to this list
     * @return true (as specified by {@link java.util.Collection#add}
     */
    @Override
    public boolean add(E element) {
        this.ensureCapacity();
        list[size] = element;
        size += 1;
        return true;
    }

    /**
     * Inserts the specified element at the specified index in the list.
     * Shifts the element currently at that index (if any) and any subsequent elements to the right (adds one to their indices).
     * Time Complexity: O(N)
     *
     * @param index -- index at which the specified element is to be inserted
     * @param element -- element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     */
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

    /**
     * Returns the element at the specified position in this list.
     * Time Complexity: O(1)
     *
     * @param index -- the index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
        }
        return list[index];
    }

    /**
     * Returns true if this list contains no elements.
     * Time Complexity: O(1)
     *
     * @return true if this list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * Time Complexity: O(N)
     *
     * @param index -- the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
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

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * Time Complexity: O(1)
     *
     * @param index -- index of the element to replace
     * @param element -- element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    @Override
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
     * Time Complexity: O(1)
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Trims the capacity of this ArrayList instance to be the list's current size.
     * An application can use this operation to minimize the storage of an ArrayList instance.
     * Time Complexity: O(N)
     */
    @SuppressWarnings("unchecked")
    public void trimToSize() {
        if (size < list.length) {
            E[] newList = (E[]) new Object[size];
            System.arraycopy(list, 0, newList, 0, size);
            list = newList;
        }
    }

    /**
     * @return a {@code String} representation of the elements in MyArrayList
     */
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(list, size));
    }

    /**
     * The method returns {@code true} if and only if:
     * <ul>
     *     <li>The other object is the same reference as this one, or</li>
     *     <li>The other object is also a MyArrayList, has the same size, and all corresponding elements are equal in order.</li>
     * </ul>
     *
     * This method uses {@link Objects#equals(Object, Object)} to safely compare elements,
     * allowing {@code null} values to be handled correctly.
     *
     * @param obj the object to compare this MyArrayList against
     * @return {@code true} if the specified obj is equal to this MyArrayList; {@code false} otherwise
     */
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

    /**
     * Computes the hash code for this MyArrayList by combining the hash codes of all elements in order.
     * <ul>
     *     <li>Uses a prime multiplier (31) to produce a well-distributed hash code.</li>
     *     <li>Multiplying by 31 can be optimized by the JVM via bit shifting</li>
     * </ul>
     *
     * This method ensures that two objects of MyArrayList with the same elements in the same order have the same hash code,
     * maintaining consistency with the equals() method.
     * <ul>
     *     <li>if a.equals(b) then a.hashCode() ==  b.hashCode()</li>
     *     <li>This method uses {@link Objects#hashCode(Object)} to safely generate hash codes for each element,
     *     allowing {@code null} values to be handled correctly by treating their hash code as zero.
     *     </li>
     * </ul>
     *
     * @return the computed hash code value for this list
     */
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
