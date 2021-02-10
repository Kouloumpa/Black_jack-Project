public class CasinoCustomer {
    private float customer_balance;
    private String customer_name;

    public CasinoCustomer(String name, float balance){  // ο δημιουργός της κλάσης CasinoCustomer
        this.customer_name = name;
        this.customer_balance = balance;
    }
    // Συνάρτηση αλλαγής της τιμής της μεταβλητής customer_name
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
    // Συνάρτηση απόκτησης της τιμής της μεταβλητής customer_name
    public String getCustomer_name() {
        return customer_name;
    }
    // Συνάρτηση αλλαγής της τιμής της μεταβλητής customer_balance
    public void setCustomer_balance(float customer_balance) {
        this.customer_balance = customer_balance;
    }
    // Συνάρτηση απόκτησης της τιμής της μεταβλητής customer_balance
    public float getCustomer_balance() {
        return customer_balance;
    }
    // Συνάρτηση αφαίρεσης του πονταρίσματος από το υπόλοιπο του πελάτη
    public void payBet(float lost_bet){
        this.customer_balance = customer_balance - lost_bet;
    }
    // Συνάρτηση προσθήκης του πονταρίσματος στο υπόλοιπο του πελάτη
    public void collectBet(float won_bet){
        this.customer_balance = customer_balance + won_bet;
    }
    // Συνάρτηση ελέγχου αν το ποντάρισμα είναι μικρότερο του υπολοίπου του πελάτη
    public boolean canCover(float bet){
        return this.customer_balance >= bet;
    }
    // Συνάρτηση ελέγχου αν ο πελάτης έχει εναπομείναντο υπόλοιπο
    public boolean isBroke(){
        return this.customer_balance < 1;
    }
    // Συνάρτηση επιστροφής του ονόματος του πελάτη
    public String toString(){
        return this.customer_name;
    }
    // Συνάρτηση εκτύπωσης του ονόματος και του υπολοίπου του πελάτη
    public void printState(){
        System.out.println("Customer name: " + this.customer_name + ", Customer Balance: " + this.customer_balance);
    }
}
