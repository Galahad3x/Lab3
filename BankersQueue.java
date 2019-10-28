import java.util.*;

public class BankersQueue<E> implements Queue<E>, Iterable<E> {
    /**
     * Llista de la part davantera
     */
    public List<E> front;
    /**
     * Llista de la part del darrera
     */
    public List<E> back;
    /**
     * Contador de modificacions de la cua
     */
    private int modCount;

    /**
     * Constructor a partir de 2 llistes
     *
     * @param front Llista front
     * @param back  Llista de back
     */
    public BankersQueue(List<E> front, List<E> back) {
        this.front = front;
        this.back = back;
        modCount = 0;
    }

    /**
     * Constructor d'una Queue buida
     */
    public BankersQueue() {
        this.front = new ArrayList<>();
        this.back = new ArrayList<>();
        modCount = 0;
    }

    /**
     * @param other L'altra cua amb la que comparem
     * @return True si tenen els mateixos elements i en el mateix ordre
     */
    public boolean equals(BankersQueue other) {
        return this.unify().equals(other.unify());
    }

    /**
     * Uneix les dues llistes front i back en una sola llista
     *
     * @return Llista que uneix front i back
     */
    public List<E> unify() {
        List<E> finl = new ArrayList<>();
        List<E> tempFront = new ArrayList<>(front);
        List<E> tempBack = new ArrayList<>(back);
        BankersQueue<E> temp = new BankersQueue<>(tempFront, tempBack);
        while (!temp.isEmpty()) {
            finl.add(temp.element());
            temp.remove();
        }
        return finl;
    }

    /**
     * Afegeix un element al final de la cua
     *
     * @param e Element a afegir
     */
    @Override
    public void add(E e) {
        back.add(e);
        modCount++;
    }

    /**
     * Elimina el primer element de la cua
     */
    @Override
    public void remove() {
        if (front.isEmpty()) {
            transfer();
        }
        front.remove(front.size() - 1);
        modCount++;
    }

    /**
     * Retorna el primer element de la cua
     *
     * @return Primer element de la cua
     * @throws NoSuchElementException si la llista està buida i per tant no hi ha cap element
     */
    @Override
    public E element() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (front.isEmpty()) {
            transfer();
        }
        return front.get(front.size() - 1);
    }

    /**
     * Transfereix tots els elements de back cap a front, invertint-los perquè quedin el l'ordre correcte
     */
    private void transfer() {
        if (front.isEmpty()) { //Sol ho farem si front està buit
            while (!back.isEmpty()) {
                E transfer = back.get(back.size() - 1);
                back.remove(back.size() - 1);
                front.add(transfer);
            }
        }
    }

    /**
     * @return True si la cua està buida
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Retorna el número d'elements que hi ha a la cua
     *
     * @return Mida de la cua
     */
    @Override
    public int size() {
        return front.size() + back.size();
    }

    /**
     * Retorna un QueueItr, que serveix com a iterador
     *
     * @return Iterador al principi de la cua
     */
    @Override
    public Iterator<E> iterator() {
        return new QueueItr();
    }

    /**
     * Retorna un QueueItr, que serveix com a iterador a partir de l'índex especificat
     *
     * @param index Índex on volem que comenci l'iterador
     * @return Iterador a l'índex especificat
     */
    public Iterator<E> iterator(int index) {
        return new QueueItr(index);
    }

    /**
     * Classe anidada, serveix com a iterador de la cua
     */
    private class QueueItr implements Iterator<E> {

        /**
         * Modificacions de la cua al crear l'iterador
         */
        int expectedModCount;
        /**
         * Iterador intern de la llista front
         */
        ListIterator<E> itf;
        /**
         * Iterador intern de la llista back
         */
        ListIterator<E> itb;

        /**
         * Crea un iterador a líndex que entra per paràmetre
         * @param index Índex on volem posicionar l'iterador
         * @throws IndexOutOfBoundsException si l'índex està fora de la llista
         */
        private QueueItr(int index) {
            if (index >= 0 && index < front.size()) {
                itf = front.listIterator(index);
                itb = back.listIterator();
            } else if (index > front.size() && index < size()) {
                itf = front.listIterator();
                itb = back.listIterator(index - front.size());
            } else {
                throw new IndexOutOfBoundsException();
            }
            this.expectedModCount = modCount;
        }

        /**
         * Crea un iterador al principi de la cua
         */
        private QueueItr() {
            if (!front.isEmpty()) {
                itf = front.listIterator(front.size() - 1);
            } else {
                itf = front.listIterator(0);
            }
            itb = back.listIterator();
            this.expectedModCount = modCount;
        }

        /**
         * @return True si hi ha un següent element
         */
        @Override
        public boolean hasNext() {
            return itf.hasPrevious() || itb.hasNext();
        }

        /**
         * @return El següent element de la cua
         * @throws NoSuchElementException si no hi ha element següent
         * @throws ConcurrentModificationException si s'ha modificat la cua durant el recorregut
         */
        @Override
        public E next() {
            if (expectedModCount == modCount) {
                if (itf.hasPrevious()) {
                    return itf.previous();
                } else if (itb.hasNext()) {
                    return itb.next();
                } else {
                    throw new NoSuchElementException();
                }
            } else {
                throw new ConcurrentModificationException();
            }
        }

        /**
         * @throws UnsupportedOperationException ja que no està implementada
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}