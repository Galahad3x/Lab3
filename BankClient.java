public class BankClient {

    /**
     * Temps en que el client arriba al banc
     */
    public int arrivalTime;
    /**
     * Temps en que el client surt del banc
     */
    public int exitTime;
    /**
     * Temps que el client porta sent atès
     */
    public int service;

    /**
     * @param arrivalTime Temps que el client arriba al banc
     * @param exitTime    Temps que el client surt del banc
     */
    public BankClient(int arrivalTime, int exitTime) {
        this.arrivalTime = arrivalTime;
        this.exitTime = exitTime;
        this.service = 0;
    }

    /**
     * Compara 2 BankClient segons el seu arrivalTime
     *
     * @param other Client amb el que estem comparant
     * @return True si són iguals
     */
    public boolean equals(BankClient other) {
        return this.arrivalTime == other.arrivalTime;
    }
}
