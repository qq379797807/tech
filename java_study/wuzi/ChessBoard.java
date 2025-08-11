package wuzi;


import java.util.ArrayList;

import wuzi.piece.ChessPiece;


public class ChessBoard {

    ArrayList<ArrayList<ChessPiece>> boardCellGrid = new ArrayList<ArrayList<ChessPiece>>();

    public ChessBoard() {
        for (int i = 0; i < 5; i++) {
            ArrayList<ChessPiece> row = new ArrayList<ChessPiece>();

            for (int j = 0; j < 5; j++) {
                row.add(null);
            }

            this.boardCellGrid.add(row);
        }

        System.out.println(this.boardCellGrid);

    }

    public void run(ChessPiece cp, int row, int coulnm) {
        this.boardCellGrid.get(row).set(coulnm, cp);

        System.out.println(this.boardCellGrid);

        this.check();
    }

    public void run(ChessPiece cp ,ArrayList<Integer> arr){
        this.run(cp, arr.get(0),arr.get(1));
    }

    /**
     * 
     * @return 0 没人赢,1 黑赢,2 白赢
     */
    private void check() {

        for (int x = 0; x < this.boardCellGrid.size(); x++) {

            ArrayList<ChessPiece> row = this.boardCellGrid.get(x);

            for (int i = 0; i < row.size() - 2; i++) {
                ChessPiece c1 = row.get(i);
                ChessPiece c2 = row.get(i + 1);
                ChessPiece c3 = row.get(i + 2);

                if (c1 != null && c2 != null && c3 != null) {
                    if (c1.type == c2.type && c1.type == c3.type) {
                        if (c1.type == 1) {
                            System.out.println("黑赢");
                        } else if (c1.type == 2) {
                            System.out.println("白赢");
                        }
                    }
                }
            }
        }

    }

}
