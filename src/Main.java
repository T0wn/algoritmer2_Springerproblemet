import java.util.Stack;

public class Main {

    public static Stack<Integer> trekkStatus = new Stack<Integer>();

    public static void finnOgFlytt(Sjakkbrett brett, int nesteTrekk) {
        if ( brett.getTrekknr() <= brett.getSquaresInBoard() ) {
            if (brett.getPossibleMoves().size() > nesteTrekk) {
                brett.flyttBrikke( brett.getPossibleMoves().get(nesteTrekk).get(0), brett.getPossibleMoves().get(nesteTrekk).get(1) );
                trekkStatus.push(nesteTrekk);
                System.out.println(trekkStatus);
                brett.printBrett();
                finnOgFlytt(brett, 0);
            }
            else {
                brett.flyttTilbake();
                brett.printBrett();
                finnOgFlytt(brett, trekkStatus.pop() + 1);
            }
        }
    }

    public static void main(String[] args) {

//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Størrelse på sjakkbrett           : ");
//        int boardSize = scanner.nextInt();
//        System.out.print("Startposisjon springer (x-verdi)  : ");
//        int startX = scanner.nextInt();
//        System.out.print("Startposisjon springer (y-verdi)  : ");
//        int startY = scanner.nextInt();

//        Sjakkbrett brett = new Sjakkbrett(boardSize, startX, startY);
        Sjakkbrett brett = new Sjakkbrett(4, 2, 1);
        brett.printBrett();
        finnOgFlytt(brett, 0);

    }

}
