package main.java.datastructures.list;

import main.java.datastructures.queue.MyDeque;
import java.util.Objects;

public class MyLinkedList<E> implements MyList<E>, MyDeque<E> {
    private static class ListNode<E> {
        private E val;
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

        public void setVal(E value) {
            this.val = value;
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

    // List
    @Override
    public boolean add(E element) {
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
        return true;
    }

    @Override
    public void add(int position, E element) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Index " + position + " is out of bounds.");
        }

        if (position == 0) {
            this.push(element);
        } else if (position == size) {
            this.add(element);
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

    @Override
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

    @Override
    public E set(int position, E element) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Index " + position + " is out of bounds.");
        }

        ListNode<E> temp = head;
        for(int i = 0; i < position; i++) {
            temp = temp.getNext();
        }

        E oldValue = temp.getVal();
        temp.setVal(element);
        return oldValue;
    }

    @Override
    public E remove(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Index " + position + " is out of bounds.");
        }

        E removed;

        if (position == 0) {
            removed = this.dequeue();
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

    @Override
    public int size() {
        return size;
    }

    // Queue
    public E peek() {
        return head.getVal();
    }

    @Override
    public E dequeue() {
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

    @Override
    public void enqueue(E element) {
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

    // Deque
    @Override
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

    @Override
    public E getLast() {
        return tail.getVal();
    }

    // Deque (Stack functionality)
    @Override
    public void push(E element) {
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

    @Override
    public E pop() {
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

    // Object
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || this.getClass() != obj.getClass()) return false;

        MyLinkedList<?> other = (MyLinkedList<?>) obj;

        if(this.size() != other.size()) return false;

        ListNode<E> currThis = head;
        ListNode<?> currOther = (ListNode<?>) other.peek();

        while (currThis != null && currOther != null) {
            if(!Objects.equals(currThis.getVal(), currOther.getVal())) {
                return false;
            }
            currThis = currThis.getNext();
            currOther = currOther.getNext();
        }

        return currThis == null && currOther == null;
    }

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
