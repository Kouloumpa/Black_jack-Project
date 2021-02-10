public class Dealer {
    // Ορισμός ενό αντικειμένου της κλάσης River και δημιουργία ενός αντικειμένου της κλάσης Hand
    private River river;
    private Hand dealer_hand = new Hand();
    // Δημιουργός της κλάσης με όρισμα ένα αντικείμενο της κλάσης River
    public Dealer(River input_river){
        this.river = input_river;
    }
    // Συνάρτηση αλλαγής της μεταβλητής dealer_hand
    public void setDealer_hand(Hand dealer_hand) {
        this.dealer_hand = dealer_hand;
    }
    // Συνάρτηση επιστροφής της μεταβλητής dealer_hand
    public Hand getDealer_hand() {
        return dealer_hand;
    }
    // Συνάρτηση τραβήγματος κάρτας από το river και προσθήκη της στο χέρι του dealer
    public void draw(){
        this.dealer_hand.addCard(this.river.nextCard());
    }
    // Συνάρτηση τραβήγματος κάρτας από το river και προσθήκη της στο χέρι του παίκτη
    public void deal(Player player){
        Card new_card = this.river.nextCard();
        Hand player_hand = player.getCasino_hand();
        player_hand.addCard(new_card);
        player.setCasino_hand(player_hand);
    }
    // Συνάρτηση για το τράβηγμα καρτών από τον dealer όσο το score των καρτών είναι μικρότερο του 17
    public void play(){
        while (this.dealer_hand.score() < 17){
            draw();
        }
    }
    // Συνάρτηση σύγκρισης του score του χεριού του dealer με το χέρι του παίκτη
    public void settle(Player player){
        if(this.dealer_hand.score() <= 21){
            if(player.getCasino_hand().score() < this.dealer_hand.score()){
                player.loses(); // αν ο παίκτης χάσει αφαιρείται το ποντάρισμα από το υπόλοιπό του
            }
            else if (player.getCasino_hand().score() > this.dealer_hand.score()){
                player.wins();  // αν ο παίκτης κερδίσει προστίθεται το ποντάρισμά του στο υπόλοιπό του
            }
            else{
                System.out.println(player.getCasino_customer().getCustomer_name() + " : Push, no one wins!");   // αν ο dealer και ο παίκτης έχουν το ίδιο score δεν αλλάζει το υπόλοιπο του παίκτη
            }
        }
        else{
            player.wins();
        }
    }
    // Συνάρτηση επιστροφής του χεριού του dealer σαν String
    public String toString(){
        return "Dealer: " + this.dealer_hand.toString();
    }

    public static void main(String[] args) {
        // Δημιουργία αντικειμένου της κλάσης River με μία τράπουλα
        River river1 = new River(1);
        // Δημιουργία αντικειμένου της κλάσης Dealer με όρισμα το river που δημιουργήθηκε από πάνω
        Dealer dealer1 = new Dealer(river1);
        // Κλήση της συνάρτησης play() για το γέμισμα του χεριού του dealer
        dealer1.play();
        System.out.println(dealer1.toString());
        // Δημιουργία αντικειμένου της κλάσης CasinoCustomer με όνομα παίκτη player_1 και υπόλοιπο 50
        CasinoCustomer customer1 = new CasinoCustomer("player_1", 50);
        // Δημιουργία αντικειμένου της κλάσης Player με όρισμα το CasinoCustomer που δημιουργήθηκε από πάνω
        Player player1 = new Player(customer1);
        // Μοίρασμα δύο καρτών στον παίκτη player_1
        dealer1.deal(player1);
        dealer1.deal(player1);
        System.out.println(player1.toString());
        // Κλήση της συνάρτησης settle() με όρισμα τον παίκτη player_1 για την σύγκριση του χεριού του με τον dealer
        dealer1.settle(player1);
    }
}
