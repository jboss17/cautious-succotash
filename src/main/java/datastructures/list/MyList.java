package main.java.datastructures.list;

import java.util.Objects;

/**
 * A simple list interface defining basic operations.
 *
 * @param <E> the type of elements in this list
 */
public interface MyList<E> {

    /**
     * Appends the specified element to the end of the list.
     * Time Complexity: O(N)
     *
     * @param element the element to be appended to this list
     * @return true
     */
    boolean add(E element);

    /**
     * Inserts the specified element at the specified index in the list.
     * Shifts the element currently at that index (if any) and any subsequent elements to the right (adds one to their indices).
     * Time Complexity: O(N)
     *
     * @param position the index at which the specified element is to be inserted
     * @param element the element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     */
    void add(int position, E element);

    /**
     * Returns the element at the specified position in this list.
     * Time Complexity: O(1)
     *
     * @param position the index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    E get(int position);

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * Time Complexity: O(1)
     *
     * @param position the index of the element to replace
     * @param element the element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    E set(int position, E element);

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * Time Complexity: O(N)
     *
     * @param position the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    E remove(int position);

    /**
     * Returns the number of elements in this list.
     * Time Complexity: O(1)
     *
     * @return the number of elements in this list
     */
    int size();

    /**
     * @return a {@code String} representation of the elements in MyArrayList
     */
    String toString();

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
    boolean equals(Object obj);

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
    int hashCode();
}
