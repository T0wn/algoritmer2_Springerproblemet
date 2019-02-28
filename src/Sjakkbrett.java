import java.util.ArrayList;

public class Sjakkbrett {
    private int[][] brett;
    private int trekknr = 1;
    private int[] currentPos = new int[2];


    public Sjakkbrett(int size, int x, int y) {
        brett = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                brett[i][j] = 0;
            }
        }

        brett[x-1][y-1] = trekknr;
        trekknr++;
        this.setCurrentPos(x - 1, y - 1);
    }

    private void setCurrentPos(int x, int y) {
        currentPos = new int[]{x, y};
    }

    public int getTrekknr() { return trekknr; }

    public int getSquaresInBoard() { return brett.length * brett.length; }


    public void printBrett() {
        for (int i = 0; i < brett.length; i++) {
            for (int j = 0; j < brett.length; j++) {
                if (brett[j][i] < 10) {
                    System.out.print("0");
                }
                System.out.print(brett[j][i] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }


    public ArrayList<ArrayList<Integer>> getPossibleMoves() {
        int[] xSel = {1, 1, -1, -1, 2, 2, -2, -2};
        int[] ySel = {2, -2, 2, -2, 1, -1, 1, -1};

        ArrayList<ArrayList<Integer>> returner = new ArrayList<ArrayList<Integer>>();
        for (int j = 0; j < 8; j++) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            // sjekker om trekket er innenfor brettet
            if ( (currentPos[0] + xSel[j]) <= brett.length - 1 && (currentPos[1] + ySel[j]) <= brett.length - 1
                    && (currentPos[0] + xSel[j]) >= 0 && (currentPos[1] + ySel[j]) >= 0 ) {
                // sjekker om trekket er ledig
                if ( brett[ currentPos[0] + xSel[j] ][ currentPos[1] + ySel[j] ] == 0 ) {
                    temp.add(currentPos[0] + xSel[j]);
                    temp.add(currentPos[1] + ySel[j]);
                    returner.add( temp );
                }
            }
        }
        return returner;
    }


    public void flyttBrikke(int x, int y) {
        brett[x][y] = trekknr;
        trekknr++;
        this.setCurrentPos(x, y);
    }


    public void flyttTilbake() {
        brett[ currentPos[0] ][ currentPos[1] ] = 0;
        trekknr -= 2;
        for (int i = 0; i < brett.length; i++) {
            for (int j = 0; j < brett.length; j++) {
                if (brett[i][j] == trekknr) {
                    setCurrentPos(i, j);
                }
            }
        }
        trekknr++;
    }
}
