import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackTable {
    // Δημιουργία αντικειμένου της κλάσης Scanner για είσοδο από το πληκτρολόγιο
    Scanner console = new Scanner(System.in);
    // Δημιουργία αντικειμένου της κλάσης River με 6 τράπουλες
    private River river = new River(6);
    // Δημιουργία μετβλητής ArrayList για την αποθήκευση των πελατών που θα ορίσει ο χρήστης
    private ArrayList<CasinoCustomer> casinoCustomers = new ArrayList<>();
    // Ο δημιουργός της κλάσης ο οποίος παίρνει σαν όρισμα τον αριθμό των παικτών και τους προσθέτει στην μεταβλητή casinoCustomers
    public BlackjackTable(int input_number_of_players){
        for (int i = 0; i < input_number_of_players; i++){
            this.casinoCustomers.add(createCasinoCustomer());
        }
    }
    // Συνάρτηση που καλείται στον δημιουργό και δημιουργεί αντικείμενα CasinoCustomer
    private CasinoCustomer createCasinoCustomer(){
        System.out.println("Please type your name");
        String name = console.next();
        System.out.println("Please type the amount of money that you are going to play");
        float money = console.nextFloat();
        return new CasinoCustomer(name, money);
    }
    // Συνάρτηση πραγματοποίησης ενός γύρου παιχνιδιού
    public void play(){
        System.out.println("---- New Round! ----");
        float balance = 0;
        // Για κάθε παίχτι στο τραπέζι ελέγχουμε αν έχει επαρκές υπόλοιπο
        for(CasinoCustomer casinoCustomer : this.casinoCustomers){
            if(casinoCustomer.getCustomer_balance() > balance){
                balance = casinoCustomer.getCustomer_balance();
            }
        }
        if( balance >= 1){
            // ελέγχουμε αν πρέπει να γίνει ανακάτεμα των τραπουλών
            if(this.river.shouldRestart()){
                this.river.restart();
            }
            // Δημιουργία αντικειμένου της κλάσης Round και προσθήκη των παικτών με επαρκές υπόλοιπο στον γύρο
            Round new_round = new Round(this.river);
            for(CasinoCustomer casinoCustomer : this.casinoCustomers){
                if(!casinoCustomer.isBroke()){
                    new_round.addPlayer(casinoCustomer);
                }
            }
            // Κλήση της μεθόδου playRound() για το παίξιμο του γύρου
            new_round.playRound();
            // Η συνάρτηση play() ξανακαλεί τον εαυτό της μέχρι όλοι οι παίκτες να μην έχουν επαρκές υπόλοιπο
            play();
        }
    }
}
