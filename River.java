public class River {
    private int cards_left = 0;
    private int numberOfCards = 0;
    Card[] card_flow;   // αρχικοποίηση του πίνακα από αντικείμενα Card
    public River(int number_of_decks){  //δημιουργός της κλάσης με όρισμα τον αριθμό των τραπουλών
        int card_counter = 0;
        this.numberOfCards = number_of_decks * 52;  // υπολογισμός συνολικού αριθμού καρτών
        card_flow = new Card[number_of_decks * 52];
        // γέμισμα του πίνακα με αντικείμενα καρτών
        for(int i=0; i<number_of_decks; i++){
            String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
            for(String j : ranks){
                for(int k=0; k<4; k++){
                    card_flow[card_counter] = new Card(j);  // δημιουργεία αντικειμένου της κλάσης Card
                    card_counter +=1;
                }
            }
        }
        cards_left = card_flow.length;
    }
    // Συνάρτηση τυχαίας επιλογής κάρτας από το σύνολο του river
    public Card nextCard(){
        if(cards_left == 0){return null;}
        int randomNum = (int)(Math.random() * cards_left);
        // εναλλαγή θέσης της τυχαίας επιλογής με την τελευταία κάρτα στις εναπομείναντες
        Card temp = card_flow[randomNum];
        card_flow[randomNum] = card_flow[cards_left-1];
        card_flow[cards_left-1] = temp;
        // μείωση των εναπομείναντων καρτών κατά ένα
        cards_left -= 1;
        return temp;
    }
    // Συνάρτηση ελέγχου ποσοστού εναπομείναντων καρτών
    public boolean shouldRestart(){
        return (float) cards_left / (float) numberOfCards <= 0.25;
    }
    // ορισμός των εναπομείναντων καρτών ίσο με τον συνολικό αριθμό καρτών στο river
    public void restart(){
        cards_left = numberOfCards;
    }

    public static void main(String[] args) {
        // Δημιουργία αντικειμένου River με μία τράπουλα
        River river = new River(1);
        // Έλεγχος ποσοστού εναπομείναντων καρτών πρίν από κάθε τράβηγμα
        while (!river.shouldRestart()){
            // Τράβηγμα και εκτύπωση τυχαίας κάρτας
            System.out.println("Card Drawn: " + river.nextCard());
        }
        // Ανακάτεμα των τραπουλών
        river.restart();
        System.out.println("Cards have been shuffled");
        // Τράβηγμα τυχαίων καρτών μέχρι να τελειώσει το River
        for(int i=0; i<=river.numberOfCards; i++){
            System.out.println("Card Drawn: " + river.nextCard());
        }
    }
}
