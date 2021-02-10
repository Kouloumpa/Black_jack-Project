public class Card {
    private final String card;  //αρχικοποίηση του ονόματος της κάρτας
    private int value;          //αρχικοποίηση της τιμής της κάρτας
    public Card (String rank){  //δημιουργός της κλάσης
        this.card = rank;

    }
    public int getValue (){     //συνάρτηση που επιστρέφει την τιμή της κάρτας ανάλογα με το όνομά της
        if(this.card == "Ace"){
            this.value = 1;
        }
        else if(this.card == "Jack" || this.card == "Queen" || this.card == "King"){
            this.value = 10;
        }
        else{
            this.value = Integer.parseInt(this.card);
        }
        return this.value;
    }
    public boolean isAce() {    // συνάρτηση που επιστρέφει αν η κάρτα είναι άσσος
        return this.card == "Ace";
    }
    public boolean equals(Card card1, Card card2){  // συνάρτηση που συγκρίνει δύο κάρτες ως προς το όνομά τους
        return card1.card == card2.card;

    }
    public String toString(){   // συνάρτηση που επιστρέφει το όνομα της κάρτας
        return this.card;
    }

}