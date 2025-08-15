package com.example.demo.chess;


import java.util.ArrayList;

import com.example.demo.chess.piece.ChessPiece;
import com.example.demo.model.Luozi;



public class ChessBoard {

    ArrayList<ArrayList<ChessPiece>> boardCellGrid = new ArrayList<ArrayList<ChessPiece>>();


    // public ArrayList<Luozi> getState() {
    
    //     ArrayList<Luozi> state = new ArrayList<Luozi>();

    //     for (int i = 0; i < this.boardCellGrid.size(); i++) {
    //         ArrayList<ChessPiece> row = this.boardCellGrid.get(i);
    //         for (int j = 0; j < row.size(); j++) {
    //             ChessPiece piece = row.get(j);
    //             if (piece != null) {
    //                 state.add(new Luozi(i,j, piece.type ));
    //             }
    //         }
    //     }

    //     return state;
    // }


    public ChessBoard() {
        for (int i = 0; i < 7; i++) {
            ArrayList<ChessPiece> row = new ArrayList<ChessPiece>();

            for (int j = 0; j < 7; j++) {
                row.add(null);
            }

            this.boardCellGrid.add(row);
        }

        System.out.println(this.boardCellGrid);

    }

    public int run(ChessPiece cp, int row, int column) {
        this.boardCellGrid.get(row).set(column, cp);

        System.out.println(this.boardCellGrid);

        return this.check();
    }

    public int run(ChessPiece cp ,ArrayList<Integer> arr){
       return this.run(cp, arr.get(0),arr.get(1));
    }

    public int run(Luozi luozi) {
        ChessPiece cp = null;
        if (luozi.getColor().equals("black") ) {
            cp = new com.example.demo.chess.piece.ChessPieceBlack();
        } else if (luozi.getColor().equals("white")) {
            cp = new com.example.demo.chess.piece.ChessPieceWhite();
        }

        // 解析 cellId（从 "2-5" 格式转换为 x 和 y 坐标）
        var row = Integer.parseInt(luozi.getCellId().split("-")[0]);
        var column = Integer.parseInt(luozi.getCellId().split("-")[1]);

        return this.run(cp, row, column);
    } 

    /**
     * 
     * @return 0 没人赢,1 黑赢,2 白赢
     */
    private int check() {

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

                        return c1.type;
                    }
                }
            }
        }

        return 0; // 没人赢

    }

    public ArrayList<Luozi> getState() {
         ArrayList<Luozi> state = new ArrayList<Luozi>();

        for (int i = 0; i < this.boardCellGrid.size(); i++) {
            ArrayList<ChessPiece> row = this.boardCellGrid.get(i);
            for (int j = 0; j < row.size(); j++) {
                ChessPiece piece = row.get(j);
                if (piece != null) {
                    state.add(new Luozi(i,j, piece.type ));
                }
            }
        }

        return state;
    }


    

}
