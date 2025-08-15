
package com.example.demo;

public class ChessService {

    public String getChessBoard() {
        StringBuilder board = new StringBuilder();
        for (int row = 8; row >= 1; row--) {
            for (int col = 1; col <= 8; col++) {
                if ((row + col) % 2 == 0) {
                    board.append("W "); // White square
                } else {
                    board.append("B "); // Black square
                }
            }
            board.append("\n");
        }
        return board.toString();
    }
}