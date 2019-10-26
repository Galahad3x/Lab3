import static java.lang.System.exit;

public class BankSimulator {

    public static int SERVICE_TIME = 120;
    public static int NUM_CLIENTS = 100;
    public static int NUM_CASHIERS = 10;
    public static BankClient[] cashiers;
    public static BankClient[] clients;
    public static BankersQueue<BankClient> doneClients;
    public static int time = 0;
    public static BankClient free = new BankClient(-1, 0);

    public static void main(String[] args) {
        float[] results = new float[NUM_CASHIERS];
        for (int i = 1; i <= NUM_CASHIERS; i++) {
            simulate(i);
            results[i - 1] = calculate();
            System.out.println("###################################");
        }

        for (int i = 0; i < NUM_CASHIERS; i++) {
            System.out.println((i + 1) + " caixers: " + results[i]);
        }
        exit(0);
    }

    private static float calculate() {
        float timeInBankTotal = 0;
        for (int i = 0; i < doneClients.size(); i++) {
            timeInBankTotal += (doneClients.element().exitTime - doneClients.element().arrivalTime);
            doneClients.remove();
        }
        return timeInBankTotal / doneClients.size();
    }

    private static void simulate(int numCaixers) {
        cashiers = new BankClient[numCaixers];
        clients = new BankClient[NUM_CLIENTS];
        doneClients = new BankersQueue<>();
        time = 0;
        BankersQueue<BankClient> queue = new BankersQueue<>();
        for (int i = 0; i < cashiers.length; i++) {
            cashiers[i] = free;
        }
        for (int i = 0; i < NUM_CLIENTS; i++) {
            clients[i] = new BankClient(15 * i, 0);
            queue.add(clients[i]);
        }
        while (doneClients.size() < NUM_CLIENTS) {
            while (!queue.isEmpty() && queue.element().arrivalTime <= time && hasSpace() && !queue.isEmpty()) {
                if (!queue.isEmpty()) {
                    attendClient(queue.element());
                    queue.remove();
                }
            }
            time += 15;
            modifyTime();
            System.out.println("Time is running: " + time);
        }
    }

    private static void modifyTime() {
        for (int i = 0; i < cashiers.length; i++) {
            if (!cashiers[i].equals(free)) {
                cashiers[i].service += 15;
            }
            if (!cashiers[i].equals(free) && cashiers[i].service >= SERVICE_TIME) {
                cashiers[i].exitTime = time;
                doneClients.add(cashiers[i]);
                cashiers[i] = free;
            }
        }
    }

    private static boolean hasSpace() {
        for (int i = 0; i < cashiers.length; i++) {
            if (cashiers[i].equals(free)) return true;
        }
        return false;
    }

    private static void attendClient(BankClient client) {
        for (int i = 0; i < cashiers.length; i++) {
            if (cashiers[i].equals(free)) {
                cashiers[i] = client;
                break;
            }
        }
    }
}
