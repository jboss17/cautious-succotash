package main.java.datastructures.list;

public interface MyList<E> {
    public boolean add(E element);
    public void add(int position, E element);

    public E get(int position);
    public E set(int position, E element);

    public E remove(int position);

    public int size();
}
