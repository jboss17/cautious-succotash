package main.java.datastructures.queue;

/**
 * A doubly-ended queue that supports insertion and removal from both ends.
 * Can be used to implement a stack (LIFO).
 *
 * @param <E> the type of elements in this deque
 */
public interface MyDeque<E> extends MyQueue<E> {

    /**
     * Inserts the specified element at the front of this deque if it is possible to do so immediately without violating capacity restrictions.
     * Time Complexity: O(1)
     *
     * @param element the element to add
     */
    void push(E element);

    /**
     * Retrieves and removes the first element of this deque.
     * Time Complexity: O(1)
     *
     * @return the head of this deque
     */
    E pop();

    /**
     * Removes and returns the last element from this list.
     * Time Complexity: O(1)
     *
     * @return the last element in this list or {@code null} if this list is empty.
     */
    E removeLast();

    /**
     * Returns the last element in this list.
     * Time Complexity: O(1)
     *
     * @return the last element in this list.
     */
    E getLast();

}
