public class BankClient {
    public int arrivalTime;
    public int exitTime;
    public int service;

    public BankClient(int arrivalTime, int exitTime) {
        this.arrivalTime = arrivalTime;
        this.exitTime = exitTime;
        this.service = 0;
    }

    public boolean equals(BankClient other) {
        return this.arrivalTime == other.arrivalTime;
    }
}
