package test.java.datastructures.list;

import main.java.datastructures.list.MyArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListTest {

    private MyArrayList<String> list1;
    private MyArrayList<String> list2;
    private MyArrayList<String> list3;
    private MyArrayList<String> list4;

    @BeforeEach
    public void setUp() {
        list1 = new MyArrayList<>();
        list1.add("apple");
        list1.add("banana");

        list2 = new MyArrayList<>();
        list2.add("apple");
        list2.add("banana");

        list3 = new MyArrayList<>();
        list3.add("apple");
        list3.add("orange");

        list4 = new MyArrayList<>();
        list4.add("apple");
        list4.add("banana");
    }

    @Test
    void testAddAndGet() {
        assertEquals("apple", list1.get(0));
        assertEquals("banana", list1.get(1));
    }

    @Test
    void testInsert() {
        list3.add("a");
        list3.add("c");
        list3.add(1, "b");

        assertEquals("a", list3.get(3));
        assertEquals("b", list3.get(1));
        assertEquals("apple", list3.get(0));
        assertEquals("c", list3.get(4));
    }

    @Test
    void testRemove() {
        list3.remove(0);

        assertEquals(1, list3.size());
        assertEquals("orange", list3.get(0));
    }

    @Test
    void testSet() {
        String old = list3.set(0, "new apple");

        assertEquals("apple", old);
        assertEquals("new apple", list3.get(0));
    }

    @Test
    void testClear() {
        list3.clear();
        assertEquals(0, list3.size());
    }

    @Test
    void testIndexOutOfBounds() {
        list3.clear();

        assertThrows(IndexOutOfBoundsException.class, () -> list3.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list3.remove(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list3.set(5, "oops"));
    }

    @Test
    void testEquals() {
        // Reflexive
        assertEquals(list1, list1);

        // Symmetric
        assertEquals(list1, list2);
        assertEquals(list2, list1);

        // Transitive
        assertEquals(list1, list2);
        assertEquals(list2, list4);
        assertEquals(list1, list4);

        // Not equal: different contents
        assertNotEquals(list1, list3);

        // Not equal: different size
        list2.add("cherry");
        assertNotEquals(list1, list2);

        // Not equal: null and different type
        assertNotEquals(null, list1);
        assertNotEquals("some string", list1);
    }

    @Test
    void testHashCode() {
        // Equal objects must have equal hash codes (the converse is not necessarily true)
        assertEquals(list1.hashCode(), list2.hashCode());
    }
}
