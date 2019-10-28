import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BankersQueueTest {

    @Test
    void addElement() {
        List<Integer> front = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> back = new ArrayList<>(List.of(4, 5, 6));
        BankersQueue<Integer> queue = new BankersQueue<>(front, back);
        queue.add(7);
        List<Integer> frontExpected = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> backExpected = new ArrayList<>(List.of(4, 5, 6, 7));
        BankersQueue<Integer> expectedList = new BankersQueue<>(frontExpected, backExpected);
        assertTrue(queue.equals(expectedList));
    }

    @Test
    void addElementToAnEmptyList() {
        BankersQueue<Integer> queue = new BankersQueue<>();
        queue.add(1);
        List<Integer> frontExpected = new ArrayList<>();
        List<Integer> backExpected = new ArrayList<>(List.of(1));
        BankersQueue<Integer> expectedQueue = new BankersQueue<>(frontExpected, backExpected);
        assertTrue(queue.equals(expectedQueue));
        assertEquals(queue.element(),1);
        frontExpected = new ArrayList<>(List.of(1));
        backExpected = new ArrayList<>();
        expectedQueue = new BankersQueue<>(frontExpected, backExpected);
        assertTrue(queue.equals(expectedQueue));
    }

    @Test
    void removeElement() {
        List<Integer> front = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> back = new ArrayList<>(List.of(4, 5, 6));
        BankersQueue<Integer> queue = new BankersQueue<>(front, back);
        queue.remove();
        List<Integer> frontExpected = new ArrayList<>(List.of(1, 2));
        List<Integer> backExpected = new ArrayList<>(List.of(4, 5, 6));
        BankersQueue<Integer> expectedList = new BankersQueue<>(frontExpected, backExpected);
        assertTrue(queue.equals(expectedList));
    }

    @Test
    void transferFromBackWhenRemove() {
        List<Integer> front = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> back = new ArrayList<>(List.of(4, 5, 6));
        BankersQueue<Integer> queue = new BankersQueue<>(front, back);
        for (int i = 0; i < 4; i++) {
            queue.remove();
        }
        List<Integer> frontExpected = new ArrayList<>(List.of(6, 5));
        List<Integer> backExpected = new ArrayList<>(List.of());
        BankersQueue<Integer> expectedList = new BankersQueue<>(frontExpected, backExpected);
        assertTrue(queue.equals(expectedList));
    }

    @Test
    void isEmpty() {
        BankersQueue<Integer> queue = new BankersQueue<>();
        assertTrue(queue.isEmpty());
    }

    @Test
    void getSize() {
        List<Integer> front = new ArrayList<>(List.of(1, 2, 3, 4));
        List<Integer> back = new ArrayList<>(List.of(5, 6));
        BankersQueue<Integer> queue = new BankersQueue<>(front, back);
        int size = 6;
        assertEquals(size, queue.size());
    }
}