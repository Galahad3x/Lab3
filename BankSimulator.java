import static java.lang.System.exit;

public class BankSimulator {

    /**
     * Temps que tarda un caixer a atendre un client
     */
    public static int SERVICE_TIME = 120;
    /**
     * Número de clients que van al banc
     */
    public static int NUM_CLIENTS = 100;
    /**
     * Número de caixers que té el banc
     */
    public static int NUM_CASHIERS = 10;
    /**
     * Array on guardarem el client que està atenent cada caixer
     */
    public static BankClient[] cashiers;
    /**
     * Cua amb els clients que el banc ja ha atès
     */
    public static BankersQueue<BankClient> doneClients;
    /**
     * Temps de la simulació
     */
    public static int time = 0;
    /**
     * Client amb temps d'arribada -1, serveix per comprovar si els caixers estan lliures
     */
    public static BankClient free = new BankClient(-1, 0);

    /**
     * Funció principal, executa totes les simulacions i mostra els resultats
     */
    public static void main(String[] args) {
        float[] results = new float[NUM_CASHIERS];
        for (int i = 1; i <= NUM_CASHIERS; i++) {
            simulateBank(i);
            results[i - 1] = calculateTimeInBank();
            System.out.println("###################################");
        }
        for (int i = 0; i < NUM_CASHIERS; i++) {
            System.out.println((i + 1) + " caixers: " + results[i]);
        }
        exit(0);
    }

    /**
     * @return Resultat de la simulació
     */
    private static float calculateTimeInBank() {
        float timeInBankTotal = 0;
        for (int i = 0; i < doneClients.size(); i++) {
            timeInBankTotal += (doneClients.element().exitTime - doneClients.element().arrivalTime);
            doneClients.remove();
        }
        return timeInBankTotal / doneClients.size();
    }

    /**
     * Realitza una simulació amb el nombre de caixers indicat
     *
     * @param numCaixers Número de caixers amb els que treballem
     */
    private static void simulateBank(int numCaixers) {
        cashiers = new BankClient[numCaixers];
        doneClients = new BankersQueue<>();
        time = 0;
        BankersQueue<BankClient> queue = new BankersQueue<>();
        for (int i = 0; i < cashiers.length; i++) {
            cashiers[i] = free;
        }
        for (int i = 0; i < NUM_CLIENTS; i++) {
            queue.add(new BankClient(15 * i, 0));
        }
        while (doneClients.size() < NUM_CLIENTS) {
            while (!queue.isEmpty() && queue.element().arrivalTime <= time && hasSpace()) {
                if (!queue.isEmpty()) {
                    attendClient(queue.element());
                    queue.remove();
                }
            }
            time += 15;
            modifyTime();
            System.out.println("Time: " + time);
        }
    }

    /**
     * Actualitza els temps que els clients porten sent atesos, i els afegeix a doneClients si han acabat
     */
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

    /**
     * @return True si hi ha algun caixer lliure
     */
    private static boolean hasSpace() {
        for (int i = 0; i < cashiers.length; i++) {
            if (cashiers[i].equals(free)) return true;
        }
        return false;
    }

    /**
     * Assigna el client al primer caixer lliure
     *
     * @param client Client que volem atendre
     */
    private static void attendClient(BankClient client) {
        for (int i = 0; i < cashiers.length; i++) {
            if (cashiers[i].equals(free)) {
                cashiers[i] = client;
                break;
            }
        }
    }
}
