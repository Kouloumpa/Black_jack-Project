import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand_cards = new ArrayList<Card>(); //Δημιουργία ArrayList από Cards
    // Συνάρτηση προσθήκης κάρτας στο χέρι
    public void addCard(Card new_card){
        this.hand_cards.add(new_card);
    }
    // Συνάρτηση αλλαγής της τιμής της μεταβλητής hand_cards
    public void setHand_cards(ArrayList<Card> hand_cards) {
        this.hand_cards = hand_cards;
    }
    // Συνάρτηση επιστροφής της τιμής της μεταβλητής hand_cards
    public ArrayList<Card> getHand_cards() {
        return hand_cards;
    }

    // Συνάρτηση υπολογισμού βαθμολογίας χεριού
    public int score(){
        int hand_total_points = 0;
        boolean ace = false;
        for(Card j : hand_cards){
            if(j.isAce()){          // έλεγχος αν η κάρτα είναι άσσος
                ace = true;
            }
            hand_total_points = hand_total_points + j.getValue();
        }
        if(ace){
            if(hand_total_points + 10 <= 21){ hand_total_points = hand_total_points + 10;}
        }
        return hand_total_points;
    }
    // Συνάρτηση ελέγχου για Split
    public boolean canSplit(){
        Card card1 = hand_cards.get(0);
        Card card2 = hand_cards.get(1);
        return card1.getValue() == card2.getValue();  //Έλεγχος ισότητας τιμής κάρτας
    }
    // Συνάρτηση χωρισμού του χεριού σε δύο νέα
    public Hand[] split(){
        // Δημιουργία των 2 νέων χεριών
        Hand hand1 = new Hand();
        Hand hand2 = new Hand();
        hand1.addCard(this.hand_cards.get(0));
        hand2.addCard(this.hand_cards.get(1));
        // Αποθήκευση των νέων χεριών σε έναν πίνακα αντικειμένων Hand
        Hand[] split_hands = {hand1, hand2};
        return split_hands;
    }
    // Συνάρτηση ελέγχου για BlackJack
    public boolean isBlackjack(){
        return score() == 21;
    }
    // Συνάρτηση ελέγχου καψίματος
    public boolean isBust(){
        return score() > 21;
    }
    // Συνάρτηση εκτύπωσης καρτών χεριού
    public String toString(){
        String hand_print = "";
        for(Card j : this.hand_cards){
            hand_print = hand_print + j.toString() + " ";
        }
        return hand_print;
    }

    public static void main(String[] args) {
        // Δημιουργία αντικειμένου Hand
        Hand myhand = new Hand();
        // Δημιουργία αντικειμένου Card και προσθήκη του στο χέρι
        Card ace = new Card("Ace");
        myhand.addCard(ace);
        myhand.addCard(ace);
        System.out.println(myhand.canSplit());
        // Split των δύο άσσων
        Hand[] hands = myhand.split();
        System.out.println(hands[0].toString());
        System.out.println(hands[1].toString());
        // Δημιουργία αντικειμένου Card και προσθήκη του στο χέρι 1
        Card king = new Card("King");
        hands[0].addCard(king);
        System.out.println(hands[0].toString());
        System.out.println(hands[0].score());
        System.out.println(hands[0].isBlackjack());
        // Προσθήκη αντικειμένου Card στο χέρι 1
        hands[1].addCard(ace);
        System.out.println(hands[1].toString());
        System.out.println(hands[1].score());
        // Δημιουργία αντικειμένου Card και προσθήκη του στο χέρι 1
        Card ten = new Card("10");
        hands[1].addCard(ten);
        System.out.println(hands[1].toString());
        System.out.println(hands[1].score());
        System.out.println(hands[1].isBust());

    }
}
