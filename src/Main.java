import java.util.Scanner;
import java.util.Stack;

public class Main {

    private static Stack<Integer> trekkStatus = new Stack<Integer>();

    private static void finnOgFlytt(Sjakkbrett brett, int nesteTrekk, boolean firstCall) {

        // sjekker at problemet ikke er løst, altså om antall trekk tatt er mindre enn antall ruter på brettet.
        if ( brett.getTrekknr() <= brett.getSquaresInBoard() ) {

            // sjekker om det er flere mulige trekk videre som ikke er testet enda.
            if (brett.getPossibleMoves().size() > nesteTrekk) {
                brett.flyttBrikke( brett.getPossibleMoves().get(nesteTrekk).get(0), brett.getPossibleMoves().get(nesteTrekk).get(1) );
                trekkStatus.push(nesteTrekk);
                finnOgFlytt(brett, 0, false);
            }
            // Hvis det ikke er flere utesta trekk videre, så går man et trekk tilbake
            else {
                brett.flyttTilbake();
                int nextMove = trekkStatus.pop();

                // Hvis man har gått helt tilbake til start posisjonen så har man testet alle mulige trekk, og det er derfor
                // ingen løsning.
                if (trekkStatus.size() == 0) {
                    System.out.println("Finnes ingen løsning");
                }
                else {
                    finnOgFlytt(brett, nextMove + 1, false);
                }
            }
        }
        else {
            // printer løsningen
            brett.printBrett();
        }

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Størrelse på sjakkbrett           : ");
        int boardSize = scanner.nextInt();
        System.out.print("Startposisjon springer (x-verdi)  : ");
        int startX = scanner.nextInt();
        System.out.print("Startposisjon springer (y-verdi)  : ");
        int startY = scanner.nextInt();

        Sjakkbrett brett = new Sjakkbrett(boardSize, startX, startY);
        finnOgFlytt(brett, 0, true);

    }
}
