import java.util.*;

public class BankersQueue <E> implements Queue<E>, Iterable<E> {
    public List<E> front;
    public List<E> back;
    private int modCount;

    //Lists constructor
    public BankersQueue(List<E> front, List<E> back) {
        this.front = front;
        this.back = back;
        modCount = 0;
    }

    public BankersQueue() {
        this.front = new ArrayList<>();
        this.back = new ArrayList<>();
        modCount = 0;
    }

    public boolean equals(BankersQueue other) {
        return this.unify().equals(other.unify());
    }

    public List<E> unify(){
        List<E> finl = new ArrayList<>();
        List<E> tempFront = new ArrayList<>(front);
        List<E> tempBack = new ArrayList<>(back);
        BankersQueue<E> temp = new BankersQueue<>(tempFront,tempBack);
        while (!temp.isEmpty()){
            finl.add(temp.element());
            temp.remove();
        }
        return finl;
    }

    @Override
    public void add(E e) {
        back.add(e);
        modCount++;
    }

    @Override
    public void remove() {
        if(front.isEmpty()) {
            transfer();
        }
        front.remove(front.size()-1);
        modCount++;
    }

    @Override
    public E element() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        if(front.isEmpty()) {
            transfer();
        }
        return front.get(front.size() - 1);
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

    @Override
    public Iterator<E> iterator() {
        return new QueueItr();
    }

    public Iterator<E> iterator(int index) {
        return new QueueItr(index);
    }

    private class QueueItr implements Iterator<E> {

        int expectedModCount;
        ListIterator<E> itf;
        ListIterator<E> itb;

        private QueueItr(int index){
            if(index >= 0 && index < front.size()) {
                itf = front.listIterator(index);
                itb = back.listIterator();
            }else if(index > front.size() && index < size()){
                itf = front.listIterator();
                itb = back.listIterator(index-front.size());
            }else{
                throw new IndexOutOfBoundsException();
            }
            this.expectedModCount = modCount;
        }

        private QueueItr(){
            if(!front.isEmpty()){
                itf = front.listIterator(front.size()-1);
            }else{
                itf = front.listIterator(0);
            }
            itb = back.listIterator();
            this.expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return itf.hasPrevious() || itb.hasNext();
        }

        @Override
        public E next() {
            if(expectedModCount == modCount){
                if(itf.hasPrevious()){
                    return itf.previous();
                }else if(itb.hasNext()){
                    return itb.next();
                }else{
                    throw new NoSuchElementException();
                }
            }else{
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}