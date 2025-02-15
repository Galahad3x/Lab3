public interface Queue <E> {
    void add(E e);
    void remove();
    E element();
    boolean isEmpty();
    int size();
}
