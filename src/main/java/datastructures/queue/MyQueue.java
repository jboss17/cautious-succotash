package main.java.datastructures.queue;

/**
 * A data structure that supports FIFO.
 *
 * @param <E> the type of elements in this queue
 */
public interface MyQueue<E> {

    /**
     * Inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions.
     * Time Complexity: O(1)
     *
     * @param element the element to enqueue
     */
    void enqueue(E element);

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
     * Time Complexity: O(1)
     *
     * @return the head of this queue or null if this queue is empty
     */
    E peek();

    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty.
     * Time Complexity: O(1)
     *
     * @return the head of this queue or null if this queue is empty
     */
    E dequeue();
}
