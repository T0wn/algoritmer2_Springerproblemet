import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Størrelse på sjakkbrett           : ");
        int boardSize = scanner.nextInt();
        System.out.print("Startposisjon springer (x-verdi)  : ");
        int startX = scanner.nextInt();
        System.out.print("Startposisjon springer (y-verdi)  : ");
        int startY = scanner.nextInt();

        ChessBoard board = new ChessBoard(boardSize, startX, startY);
        board.printBoard();
        board.solve();
    }
}
