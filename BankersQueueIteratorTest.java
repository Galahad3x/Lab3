import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BankersQueueIteratorTest {

    @Test
    void iterator() {
        BankersQueue<Integer> queue = new BankersQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.add(i + 1);
        }
        Iterator<Integer> it = queue.iterator();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(it.next());
        }
        assertEquals(list, queue.unify());
    }

    @Test
    void middleIterator() {
        BankersQueue<Integer> queue = new BankersQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.add(i + 1);
        }
        Iterator<Integer> it = queue.iterator(5);
        for (int i = 6; i < 10; i++) {
            assertEquals(it.next(), i);
        }
    }

    @Test
    void hasNext() {
        BankersQueue<Integer> queue = new BankersQueue<>();
        Iterator<Integer> it1 = queue.iterator();
        assertFalse(it1.hasNext());
        for (int i = 0; i < 10; i++) {
            queue.add(i + 1);
        }
        Iterator<Integer> it2 = queue.iterator();
        assertTrue(it2.hasNext());
    }

    @Test
    void ConcurrentModificationException() {
        try {
            BankersQueue<Integer> queue = new BankersQueue<>();
            Iterator<Integer> it = queue.iterator();
            for (int i = 0; i < 10; i++) {
                queue.add(i + 1);
            }
            System.out.println(it.next());
            fail("Didn't throw exception");
        } catch (ConcurrentModificationException c) {
            assertTrue(true);
        }
    }

    @Test
    void NoSuchElementException() {
        try {
            BankersQueue<Integer> queue = new BankersQueue<>();
            for (int i = 0; i < 10; i++) {
                queue.add(i + 1);
            }
            Iterator<Integer> it2 = queue.iterator();
            for (int i = 0; i < 20; i++) {
                it2.next();
            }
            fail("Didn't throw exception");
        } catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }

    @Test
    void remove() {
        try{
            BankersQueue<Integer> queue = new BankersQueue<>();
            Iterator<Integer> it = queue.iterator();
            it.remove();
            fail("Didn't throw exception");
        }catch (UnsupportedOperationException u){
            assertTrue(true);
        }
    }

    @Test
    void elementOfEmpty() {
        try{
            BankersQueue<Integer> queue = new BankersQueue<>();
            queue.element();
            fail("Didn't throw exception");
        }catch (NoSuchElementException e){
            assertTrue(true);
        }
    }
}
