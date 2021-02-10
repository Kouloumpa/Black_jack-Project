import java.util.Scanner;   // βιβλιοθήκη για το διάβασμα εισόδου από το πληκτρολόγιο

public class Player {
    Scanner console = new Scanner(System.in);   // Δημιουργία αντικειμένου για το διάβασμα από το πληκτρολόγιο
    // αρχικοποίηση μεταβλητών τύπου CasinoCustomer, Hand και μιας τύπου float
    private CasinoCustomer casino_customer;
    private  Hand casino_hand = new Hand();
    private float casino_bet;
    // ο πρώτος δημιουργός της κλάσης Player με ένα όρισμα, το όνομα του πελάτη
    public Player(CasinoCustomer customer){
        this.casino_customer = customer;
    }
    // ο δεύτερος δημιουργός της κλάσης Player με τρία ορίσματα, το όνομα του πελάτη, το χέρι του και το ποντάρισμά του
    public Player(CasinoCustomer customer, Hand hand, float bet){
        this.casino_customer = customer;
        this.casino_hand = hand;
        this.casino_bet = bet;
    }
    // Συνάρτηση αλλαγής της τιμής της μεταβλητής casino_bet
    public void setCasino_bet(float casino_bet) {
        this.casino_bet = casino_bet;
    }
    // Συνάρτηση απόκτησης της τιμής της μεταβλητής casino_bet
    public float getCasino_bet() {
        return this.casino_bet;
    }
    // Συνάρτηση αλλαγής της τιμής της μεταβλητής casino_hand
    public void setCasino_hand(Hand casino_hand) {
        this.casino_hand = casino_hand;
    }
    // Συνάρτηση απόκτησης της τιμής της μεταβλητής casino_hand
    public Hand getCasino_hand() {
        return this.casino_hand;
    }
    // Συνάρτηση αλλαγής της τιμής της μεταβλητής casino_customer
    public void setCasino_customer(CasinoCustomer casino_customer) {
        this.casino_customer = casino_customer;
    }
    // Συνάρτηση απόκτησης της τιμής της μεταβλητής casino_customer
    public CasinoCustomer getCasino_customer() {
        return this.casino_customer;
    }
    // Συνάρτηση προσθήκης του πονταρίσματος στο υπόλοιπο του παίκτη
    public void wins(){
        this.casino_customer.collectBet(this.casino_bet);
        System.out.println(this.casino_customer.toString() + " you won " + this.casino_bet + "$");
    }
    // Συνάρτηση προσθήκης του BlackJack στο υπόλοιπο του παίκτη
    public void winsBlackJack(){
        this.casino_customer.collectBet((float) (this.casino_bet*1.5));
        System.out.println(this.casino_customer.toString() + " you got Black Jack! You won " +this.casino_bet*1.5 + "$");
    }
    // Συνάρτηση αφαίρεσης του πονταρίσματος από το υπόλοιπο του παίκτη
    public void loses(){
        this.casino_customer.payBet(this.casino_bet);
        System.out.println(this.casino_customer.toString() + " you lost " + this.casino_bet + "$");
    }
    // Συνάρτηση τοποθέτησης πονταρίσματος από το πληκτρολόγιο
    public void placeBet(){
        float customer_balance = casino_customer.getCustomer_balance();
        System.out.println(casino_customer.toString() + ", place your bet.");
        System.out.println("Available balance: " + customer_balance);
        float input_bet = console.nextFloat();
        // έλεγχος αν το ποσό που πληκτρολογήθικε είναι μεγαλύτερο του 0 και μικρότερο του υπολοίπου του παίκτη
        while(input_bet < 1 || input_bet > customer_balance){
            System.out.println("Not valid bet");
            System.out.println(casino_customer.toString() + ", place your bet.");
            input_bet = console.nextFloat();
        }
        this.casino_bet = input_bet;
    }
    // Διπλασιασμός του πονταρίσματος
    public void doubleBet(){
        this.casino_bet = 2 * this.casino_bet;
    }
    // Συνάρτηση που επιστρέφει το αν ο παίκτης θέλει να κάνει διπλασιασμό ή όχι
    public boolean wantsToDouble(){
        float customer_balance = casino_customer.getCustomer_balance();
        if(this.casino_bet * 2 <= customer_balance){
            System.out.println(casino_customer.getCustomer_name() + ", do you want to Double the bet? Press 'y' for yes, 'n' for no");
            char answer = console.next().charAt(0);
            return answer == 'y';
        }
        else return false;
    }
    // Συνάρτηση που επιστρέφει το αν ο παίκτης θέλει να κάνει Split ή όχι
    public boolean wantsToSplit(){
        float customer_balance = casino_customer.getCustomer_balance();
        if(this.casino_bet * 2 <= customer_balance){
            System.out.println( casino_customer.getCustomer_name() + ", do you want to Split the hand? Press 'y' for yes, 'n' for no");
            char answer = console.next().charAt(0);
            return answer == 'y';
        }
        else return false;
    }
    // Συνάρτηση επιστροφής του ονόματος του παίκτη και του χεριού του σε μορφή String
    public String toString(){
        String player_hand = this.casino_customer.toString() + ": " + this.casino_hand.toString();
        return player_hand;
    }

    public static void main(String[] args) {
        // Δημιουργία αντικειμένου CasinoCustomer με όνομα Player_1 και υπόλοιπο 50
        CasinoCustomer customer_1 = new CasinoCustomer("Player_1", 50);
        // Δημιουργία αντικειμένου Player με όρισμα το αντικείμενο CasinoCustomer
        Player player_1 = new Player(customer_1);
        // Κλήση της placeBet για να δοθεί ποντάρισμα από το πληκτρολόγιο
        player_1.placeBet();
        boolean double_bet = player_1.wantsToDouble();
        // Αν ο παίκτης πατήσει ότι θέλει να κάνει διπλασιασμό διπλασιάζουμε το ποντάρισμα
        if(double_bet){player_1.doubleBet();}
        // Κλήση της wins() για την προσθήκη του πονταρίσματος στο υπόλοιπο του παίκτη
        player_1.wins();
        // Εκτύπωση ονόματος και υπολοίπου πελάτη
        player_1.casino_customer.printState();
        System.out.println(player_1.casino_customer.getCustomer_balance());
        player_1.winsBlackJack();
        player_1.casino_customer.printState();
        System.out.println(player_1.casino_customer.getCustomer_balance());
        player_1.loses();
        player_1.casino_customer.printState();
        System.out.println(player_1.casino_customer.getCustomer_balance());
    }
}