import java.util.ArrayList;
import java.util.List;

public class BankersQueue <E> implements Queue<E> {
    private List<E> front;
    private List<E> back;

    //Lists constructor
    public BankersQueue(List<E> front, List<E> back) {
        this.front = front;
        this.back = back;
    }

    public BankersQueue() {
        this.front = new ArrayList<>();
        this.back = new ArrayList<>();
    }

    public boolean equals(BankersQueue other) {
        return this.unify().equals(other.unify());
    }

    private List<E> unify(){
        List<E> finl = new ArrayList<>();
        BankersQueue<E> temp = new BankersQueue<>(front,back);
        while (!temp.isEmpty()){
            finl.add(temp.element());
            temp.remove();
        }
        return finl;
    }

    @Override
    public void add(E e) {
        back.add(e);
    }

    @Override
    public void remove() {
        if(front.isEmpty()) {
            transfer();
        }
        front.remove(front.size()-1);
    }

    @Override
    public E element() {
        if(front.isEmpty()) {
            transfer();
        }
        return front.get(front.size()-1);
    }

    private void transfer() {
        if(front.isEmpty()){ //Sol ho farem si front està buit
            while(!back.isEmpty()){
                E transfer = back.get(back.size()-1);
                back.remove(back.size()-1);
                front.add(transfer);
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return front.size() + back.size();
    }
}