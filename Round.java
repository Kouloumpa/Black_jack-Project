import java.util.ArrayList;
import java.util.Scanner;

public class Round {
    // Δημιουργία αντικειμένου της κλάσης Scanner για είσοδο από το πληκτρολόγιο
    Scanner console = new Scanner(System.in);
    // Δημιουργία μεταβλητής κλάσης Dealer
    private Dealer round_dealer;
    // Δημιουργία δύο μεταβλητών ArrayList μία για τους παίχτες του γύρου και μία για αυτούς που ακόμα δεν έχουν παίξει με τον dealer
    private ArrayList<Player> round_players = new ArrayList<>();
    private ArrayList<Player> non_settled_players = new ArrayList<>();
    // Ο δημιουργός της κλάσης που παίρνει σαν όρισμα ένα αντικείμενο της κλάσης River και δημιουργεί ένα αντικείμενο της κλάσης dealer
    public Round(River river){
        round_dealer = new Dealer(river);
    }
    // Συνάρτηση δημιουργίας αντικειμένου και προσθήκη παίχτη στην μεταβλητή round_players παίρνοντας σαν όρισμα έναν casinoCustomer
    public void addPlayer(CasinoCustomer customer){
        Player player = new Player(customer);
        round_players.add(player);
    }
    // Συνάρτηση που καλείται για να παιχτεί ένας γύρος
    public void playRound(){
        // για κάθε παίχτη ζητάμε να ποντάρει και του μοιράζουμε ένα χαρτί
        for(Player i : round_players){
            non_settled_players.add(i);
            i.placeBet();
            round_dealer.deal(i);
        }
        // ο dealer τραβάει το πρώτο του χαρτί
        round_dealer.draw();
        System.out.println(round_dealer.toString());
        // μοιράζουμε το δεύτερο χαρτί σε κάθε παίχτη
        for(Player i : round_players){
            round_dealer.deal(i);
            System.out.println(i.toString());
        }
        // ο dealer τραβάει το δεύτερό του χαρτί
        round_dealer.draw();
        // ελέγχουμε αν ο dealer έκανε blackjack
        if(round_dealer.getDealer_hand().isBlackjack()){
            System.out.println("Dealer got BlackJack, sorry everyone.");
            for(Player i : round_players) {
                if(!i.getCasino_hand().isBlackjack()) {
                    round_dealer.settle(i);
                }
                non_settled_players.remove(i);
            }
        }
        // αρχικά ελέγχουμε αν ο παίκτης έκανε blackjack και αν δεν έκανε τον ρωτάμε αν θέλει να σταματήσει στις δύο κάρτες ή αν θέλει να συνεχίσει
        else{
            for(Player i : round_players){
                if(i.getCasino_hand().isBlackjack()) {
                    i.winsBlackJack();
                    non_settled_players.remove(i);
                }
                else{
                    System.out.println(i.toString() + ", do you want to stop here? 'y' for yes, 'n' for no");
                    char answer = console.next().charAt(0);
                    if(answer == 'n') {
                        // αν επιλέξει να συνεχίσει καλείται η playPlayer()
                        playPlayer(round_dealer, i);
                    }
                }
            }
            // αφού έχουν παίξει όλοι οι παίκτες παίζει ο dealer και κανονίζει όλες τις υποχρεώσεις του προς τους παίκτες
            round_dealer.play();
            System.out.println(round_dealer.toString());
            for(Player i : non_settled_players){
                round_dealer.settle(i);
            }
        }
    }
    // Συνάρτηση που ο παίκτης παίζει απλά το χέρι του τραβώντας κάρτες
    private void playNormalHand(Dealer dealer, Player player){
        dealer.deal(player);
        System.out.println(player.toString());
        if(player.getCasino_hand().isBust()){
            player.loses();
            non_settled_players.remove(player);
        }
        else {
            System.out.println(player.toString() + " draw another card? 'y' for yes, 'n' for no");
            char answer = console.next().charAt(0);
            if (answer == 'y') {
                playNormalHand(dealer, player);
            }
        }
    }
    // Συνάρτηση που ελέγχουμε αν ο παίκτης θέλει να κάνει Split Double ή να παίξει απλά το χέρι του και καλούμε την ανάλογη συνάρτηση
    private void playPlayer(Dealer dealer, Player player){
        if(player.getCasino_hand().canSplit()){
            if(player.wantsToSplit()){
                playSplitHand(dealer, player);
            }
            else if(player.wantsToDouble()){
                playDoubledHand(dealer, player);
            }
            else {
                playNormalHand(dealer, player);
            }
        }
        else if(player.wantsToDouble()){
            playDoubledHand(dealer, player);
        }
        else {
            playNormalHand(dealer, player);
        }
    }
    // Συνάρτηση στην οποία ο παίκτης τραβάει μία κάρτα μόνο διπλασιάζοντας το στοίχημά του
    private void playDoubledHand(Dealer dealer, Player player){
        player.doubleBet();
        dealer.deal(player);
        System.out.println(player.toString());
        if(player.getCasino_hand().isBust()){
            player.loses();
            non_settled_players.remove(player);
        }
    }
    // Συνάρτηση στην οποία ο παίκτης χωρίζει το χέρι του σε δύο νέα χέρια
    private void playSplitHand(Dealer dealer, Player player){
        Hand[] two_hands = player.getCasino_hand().split();
        // δημιουργούμε δύο νέα αντικείμενα Player όπου το καθένα έχει την μία από τις δύο κάρτες του αρχικού χεριού και το αρχικό ποντάρισμα
        Player split1 = new Player(player.getCasino_customer(), two_hands[0], player.getCasino_bet());
        Player split2 = new Player(player.getCasino_customer(), two_hands[1], player.getCasino_bet());
        non_settled_players.remove(player);
        // Προσθέτουμε τα καινούργια αντικείμενα στην λίστα με τους παίκτες όπου ο dealer δεν έχει ακόμα τακτοποιήσει
        non_settled_players.add(split1);
        non_settled_players.add(split2);
        // ο παίκτης παίζει κανονικά το κάθε ένα από τα δύο χέρια
        playNormalHand(dealer,split1);
        playNormalHand(dealer,split2);
    }

    public static void main(String[] args) {
        River river1 = new River(6);
        Round round1 = new Round(river1);
        CasinoCustomer customer1 = new CasinoCustomer("customer1", 50);
        CasinoCustomer customer2 = new CasinoCustomer("customer2", 100);
        CasinoCustomer customer3 = new CasinoCustomer("customer3", 200);
        round1.addPlayer(customer1);
        round1.addPlayer(customer2);
        round1.addPlayer(customer3);
        round1.playRound();
    }
}

