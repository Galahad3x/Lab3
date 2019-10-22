import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BankersQueue <E> implements Queue<E> {
    private List<E> front = new ArrayList<>();
    private List<E> back = new ArrayList<>();

    //Lists constructor
    public BankersQueue(List<E> front, List<E> back) {
        this.front = front;
        this.back = back;
    }

    public boolean equals(BankersQueue other) {
        return front.equals(other.front) && back.equals(other.back);
    }
}
