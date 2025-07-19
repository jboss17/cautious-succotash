package main.java.datastructures.list;

import java.util.Objects;

public class MyLinkedList<E> {
    private static class ListNode<E> {
        private final E val;
        private ListNode<E> next;
        private ListNode<E> prev;

        public ListNode(E value) {
            this.val = value;
            this.next = null;
            this.prev = null;
        }

        public E getVal() {
            return this.val;
        }

        public ListNode<E> getNext() {
            return this.next;
        }

        public ListNode<E> getPrev() {
            return this.prev;
        }

        public void setNext(ListNode<E> node) {
            this.next = node;
        }

        public void setPrev(ListNode<E> node) {
            this.prev = node;
        }

    }

    private ListNode<E> head;
    private ListNode<E> tail;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Inserts the specified element at the beginning of the list.
     *
     * @param element the element to add
     */
    public void addFirst(E element) {
        ListNode<E> newHead = new ListNode<>(element);

        if (head == null) {
            head = newHead;
            tail = head;
        } else {
            head.setPrev(newHead);
            newHead.setNext(head);
            head = newHead;
        }
        size += 1;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element the element to add
     */
    public void addLast(E element) {
        ListNode<E> newTail = new ListNode<>(element);

        if (head == null) {
            head = newTail;
            tail = head;
        } else {
            tail.setNext(newTail);
            newTail.setPrev(tail);
            tail = newTail;
        }
        size += 1;
    }

    /**
     * Inserts the specified element at the specified position in the list.
     * Time Complexity: O(n)
     *
     * @param position the index at which to insert the element (0-based)
     * @param element the element to insert
     * @throws IndexOutOfBoundsException if position is less than 0 or greater than the size of this list
     */
    public void insert(int position, E element) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Index " + position + " is out of bounds.");
        }

        if (position == 0) {
            this.addFirst(element);
        } else if (position == size) {
            this.addLast(element);
        } else {
            ListNode<E> temp = head;

            for (int i = 0; i < position - 1; i++) {
                temp = temp.getNext();
            }

            ListNode<E> toInsert = new ListNode<>(element);
            ListNode<E> toReplace = temp.getNext();

            toInsert.setPrev(temp);
            temp.setNext(toInsert);

            toInsert.setNext(toReplace);
            toReplace.setPrev(toInsert);

            size += 1;
        }
    }

    /**
     * Returns the first element in this list.
     *
     * @return the first element in this list.
     */
    public E getFirst() {
        return head.getVal();
    }

    /**
     * Returns the last element in this list.
     *
     * @return the last element in this list.
     */
    public E getLast() {
        return tail.getVal();
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param position the index at which to return the element
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the position is less than 0 or greater than or equal to size of this list
     */
    public E get(int position) {
        if (position < 0 || position > size - 1) {
            throw new IndexOutOfBoundsException("Index " + position + " is out of bounds.");
        }

        ListNode<E> toFind = head;
        int idx = 0;

        while(idx < position) {
            toFind = toFind.getNext();
            idx += 1;
        }

        return toFind.getVal();
    }

    /**
     * Removes and returns the first element from this list.
     *
     * @return the first element in this list or {@code null} if this list is empty.
     */
    public E removeFirst() {
        if (head == null) return null;

        E firstElement = head.getVal();

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head.getNext().setPrev(null);
            head = head.getNext();
        }

        size -= 1;

        return firstElement;
    }

    /**
     * Removes and returns the last element from this list.
     *
     * @return the last element in this list or {@code null} if this list is empty.
     */
    public E removeLast() {
        if (tail == null) return null;

        E lastElement = tail.getVal();

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail.getPrev().setNext(null);
            tail = tail.getPrev();
        }

        size -= 1;

        return lastElement;
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param position the index at which to remove the element
     * @return the removed element
     */
    public E remove(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Index " + position + " is out of bounds.");
        }

        E removed;

        if (position == 0) {
            removed = this.removeFirst();
        } else if (position == size - 1) {
            removed = this.removeLast();
        } else {
            ListNode<E> temp = head;

            for(int i = 0; i < position - 1; i++) {
                temp = temp.getNext();
            }

            ListNode<E> toRemove = temp.getNext();
            removed = toRemove.getVal();

            toRemove.getNext().setPrev(temp);
            temp.setNext(toRemove.getNext());

            toRemove.setNext(null);
            toRemove.setPrev(null);

            size -= 1;
        }

        return removed;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list.
     */
    public int size() {
        return size;
    }

    /**
     * Returns a string representation of the list elements in order.
     * The format is: [element1, element2, element3, ...]
     * If the list is empty, it returns [].
     *
     * @return a string representation of this list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        ListNode<E> curr = head;
        while (curr != null) {
            sb.append(curr.getVal());

            if(curr.getNext() != null) {
                sb.append(",");
            }
            curr = curr.getNext();
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * The method returns {@code true} if and only if:
     * <ul>
     *     <li>The other object is the same reference as this one, or</li>
     *     <li>The other object is also a MyLinkedList, has the same size, and all the corresponding elements are equal in order.</li>
     * </ul>
     *
     * This method uses {@link Objects#equals(Object, Object)} to compare objects in a null-safe manner.
     *
     * @param obj the object to compare this MyLinkedList against
     * @return {@code true} if the specified obj is equal to this MyLinkedList; {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || this.getClass() != obj.getClass()) return false;

        MyLinkedList<?> other = (MyLinkedList<?>) obj;

        if(this.size() != other.size()) return false;

        ListNode<E> currThis = head;
        ListNode<?> currOther = (ListNode<?>) other.getFirst();

        while (currThis != null && currOther != null) {
            if(!Objects.equals(currThis.getVal(), currOther.getVal())) {
                return false;
            }
            currThis = currThis.getNext();
            currOther = currOther.getNext();
        }

        return currThis == null && currOther == null;
    }

    /**
     * <p>
     *     Computes the hash code for this MyLinkedList by combining the hash codes of all elements in order.
     *     <ul>
     *         <li>
     *             This method uses {@link Objects#hashCode(Object)} to generate hash codes for any given element in this MyLinkedList in a null-safe manner.
     *         </li>
     *     </ul>
     * </p>
     * <p>
     *     This method ensures that two objects of MyLinkedList with the same elements in the same order have the same hash code,
     *     maintaining consistency with the {@code equals()} method.
     * </p>
     *
     * @return the computed hash code value for this list
     */
    @Override
    public int hashCode() {
        int hash = 1;

        ListNode<E> curr = head;
        while (curr != null) {
            E element = curr.getVal();
            hash = 31 * hash + Objects.hashCode(element);
            curr = curr.getNext();
        }

        return hash;
    }
}
