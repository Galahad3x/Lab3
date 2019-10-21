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

    private Object[] elements;

    private int head = 0;   //Index of the first element
    private int size = 0;   //Number of elements

    //Default capacity
    private static final int DEFAULT_CAPACITY = 10;

    //Constructors
    public BankersQueue() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public BankersQueue(int capacity) {
        elements = new Object[capacity];
    }

    @Override
    public void add(E e) {
        if (size == elements.length) {
            throw new NoSuchElementException("Cola llena");
        } else {
        elements[(head + size) % elements.length] = e;
        size++;
        }
    }

    @Override
    @SuppressWarnings("Unchecked")
    public void remove() {
        if(isEmpty()) {
            throw new NoSuchElementException("Cola vacia");
        } else {
            elements[head] = null;
            head = (head + 1) % size;
            size--;
        }
    }

    @Override
    public E element() {
        if (isEmpty()) {
            return null;
        } else {
            return (E) elements[head];
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}