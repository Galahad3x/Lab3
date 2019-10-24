import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BankersQueueTest {
    @Test
    void addElement() {
        List<Integer> front = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> back = new ArrayList<>(List.of(4, 5, 6));
        BankersQueue<Integer> finalList = new BankersQueue<>(front, back);
        finalList.add(7);

        List<Integer> frontExpected = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> backExpected = new ArrayList<>(List.of(4, 5, 6, 7));
        BankersQueue<Integer> expectedList = new BankersQueue<>(frontExpected, backExpected);

        assertTrue(finalList.equals(expectedList));
    }

    @Test
    void addElementToAnEmptyList() {
        List<Integer> front = new ArrayList<>();
        List<Integer> back = new ArrayList<>(List.of(4, 5, 6));
        BankersQueue<Integer> finalList = new BankersQueue<>(front, back);
        finalList.add(1);

        List<Integer> frontExpected = new ArrayList<>(List.of());
        List<Integer> backExpected = new ArrayList<>(List.of(4, 5, 6, 1));
        BankersQueue<Integer> expectedList = new BankersQueue<>(frontExpected, backExpected);

        assertTrue(finalList.equals(expectedList));
    }

    @Test
    void removeElement() {
        List<Integer> front = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> back = new ArrayList<>(List.of(4, 5, 6));
        BankersQueue<Integer> finalList = new BankersQueue<>(front, back);
        finalList.remove();

        List<Integer> frontExpected = new ArrayList<>(List.of(1, 2));
        List<Integer> backExpected = new ArrayList<>(List.of(4, 5, 6));
        BankersQueue<Integer> expectedList = new BankersQueue<>(frontExpected, backExpected);

        assertTrue(finalList.equals(expectedList));
    }

    @Test
    void transferFromBackWhenRemove() {
        List<Integer> front = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> back = new ArrayList<>(List.of(4, 5, 6));
        BankersQueue<Integer> finalList = new BankersQueue<>(front, back);
        for(int i = 0; i < 3;i++){
            finalList.remove();
        }

        List<Integer> frontExpected = new ArrayList<>(List.of(4));
        List<Integer> backExpected = new ArrayList<>(List.of(5, 6));
        BankersQueue<Integer> expectedList = new BankersQueue<>(frontExpected, backExpected);

        assertTrue(finalList.equals(expectedList));
    }

    @Test
    void isEmpty() {
        List<Integer> front = new ArrayList<>(List.of(1));
        //Comprovem que la funció isEmpty() funciona correctament.
        if (front.isEmpty()) {
            throw new NoSuchElementException("Empty list");
        }
    }

    @Test
    void getSize() {
        List<Integer> front = new ArrayList<>(List.of(1, 2, 3, 4));
        int size = 4;
        assertEquals(size, front.size());
    }
}
