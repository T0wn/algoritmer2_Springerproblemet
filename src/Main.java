import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static Stack<Integer> trekkStatus = new Stack<Integer>();

    public static void finnOgFlytt(Sjakkbrett brett, int nesteTrekk, boolean firstCall) {
        if ( brett.getTrekknr() <= brett.getSquaresInBoard() ) {
            if (trekkStatus.size() == 0 && !firstCall) {
                System.out.println("Finnes ingen løsning");
            }
            else if (brett.getPossibleMoves().size() > nesteTrekk) {
                brett.flyttBrikke( brett.getPossibleMoves().get(nesteTrekk).get(0), brett.getPossibleMoves().get(nesteTrekk).get(1) );
                trekkStatus.push(nesteTrekk);
                finnOgFlytt(brett, 0, false);
            }
            else {
                brett.flyttTilbake();
                finnOgFlytt(brett, trekkStatus.pop() + 1, false);
            }
        }
        else {
//            printer løsningen
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
