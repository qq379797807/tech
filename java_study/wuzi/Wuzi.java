package wuzi;

import java.util.ArrayList;

import wuzi.piece.ChessPiece;
import wuzi.piece.ChessPieceBlack;
import wuzi.piece.ChessPieceWhite;

public class Wuzi {
   public static void main(String[] args) {
      System.out.println("start");

      ChessBoard cb = new ChessBoard();

      ChessPiece c1 = new ChessPieceBlack();
      cb.run(c1, 0, 1);

      ChessPiece c2 = new ChessPieceWhite();
      ArrayList<Integer> aaa = new ArrayList<Integer>();
      aaa.add(0);
      aaa.add(2);
      cb.run(c2, aaa);

      ChessPiece c3 = new ChessPieceWhite();
      cb.run(c3, 0, 3);

      ChessPiece c4 = new ChessPieceWhite();
      cb.run(c4, 0, 4);

   }
}
