package test.java.datastructures.list;

import main.java.datastructures.list.MyArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListTest {

    @Test
    void testAddAndGet() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("apple");
        list.add("banana");

        assertEquals("apple", list.get(0));
        assertEquals("banana", list.get(1));
    }

    @Test
    void testInsert() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("a");
        list.add("c");
        list.add(1, "b");

        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
    }

    @Test
    void testRemove() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.remove(1); // remove 2

        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
    }

    @Test
    void testSet() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("old");
        list.set(0, "new");

        assertEquals("new", list.get(0));
    }


    @Test
    void testClear() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("x");
        list.add("y");
        list.clear();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testIndexOutOfBounds() {
        MyArrayList<String> list = new MyArrayList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(5, "oops"));
    }

}
