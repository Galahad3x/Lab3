public class BankClient {
    //TODO
    private int arrivalTime;
    private int exitTime;

    public BankClient(int arrivalTime,int exitTime){
        this.arrivalTime = arrivalTime;
        this.exitTime = exitTime;
    }

    void setArrivalTime(int arrivalTime){
        this.arrivalTime = arrivalTime;
    }

    void setExitTime(int exitTime){
        this.exitTime = exitTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getExitTime() {
        return exitTime;
    }
}
