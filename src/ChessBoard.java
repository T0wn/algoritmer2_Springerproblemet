import java.util.ArrayList;

public class ChessBoard {
    private int[][] board;
    private int moveNr = 1;
    private int[] currentPos;
    private static int[] xDelta = {1, 1, -1, -1, 2, 2, -2, -2};
    private static int[] yDelta = {2, -2, 2, -2, 1, -1, 1, -1};

    public ChessBoard(int size, int x, int y) {
        board = new int[size][size];
        moveTo(x-1, y-1);
    }

    public void solve() {
        if (moveNr > Math.pow(board.length, 2)) {
            System.out.println("Solution:");
            printBoard();
            System.exit(0);
        } else {

            ArrayList<Integer> moves = possibleMoves();
            for (int i = 0; i < moves.size(); i++) {
                int newX = currentPos[0] + xDelta[moves.get(i)];
                int newY = currentPos[1] + yDelta[moves.get(i)];

                moveTo(newX, newY);
                solve();
            }
            if (moveNr == 2) {
                System.out.println("No solution!");
                System.exit(0);
            }

            moveBack();

        }
    }

    private void setCurrentPos(int x, int y) {
        currentPos = new int[]{x, y};
    }

    public void moveTo(int x, int y) {
        board[y][x] = moveNr;
        moveNr++;
        setCurrentPos(x, y);
    }

    public void moveBack() {
        board[currentPos[1]][currentPos[0]] = 0;
        moveNr -= 1;

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                if (board[y][x] == moveNr - 1) {
                    setCurrentPos(x, y);
                    break;
                }
            }
        }
    }

    public ArrayList<Integer> possibleMoves() {
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < xDelta.length; i++) {
            try {
                if (board[currentPos[1] + yDelta[i]][currentPos[0] + xDelta[i]] == 0) {
                    result.add(i);
                }
            } catch (Exception outOfBoundsException) {}
        }

        return result;
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] < 10) {
                    System.out.print("0");
                }
                System.out.print(board[j][i] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
