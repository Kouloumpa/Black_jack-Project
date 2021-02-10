import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Please type the number of players");
        int number_of_players = console.nextInt();
        // Δημιουργία αντικειμένου BlackjackTable με όρισμα τον αριθμό των παικτών που ορίστικε παραπάνω
        BlackjackTable table = new BlackjackTable(number_of_players);
        // Κλήση της συνάρτησης play() της κλάσης BlackjackTable για ξεκίνημα του γύρου
        table.play();
    }
}
