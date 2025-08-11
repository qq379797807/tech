package wuzi;

public class Wuzi {
   public static void main(String[] args) {
      System.out.println("start");

      ChessBoard cb = new ChessBoard();

      ChessPiece c1 = new ChessPiece(1);
      cb.run(c1, 0, 1);
      if (cb.check() == 1) {
         System.out.println("黑赢");
      } else if (cb.check() == 2) {
         System.out.println("白赢");
      }

      ChessPiece c2 = new ChessPiece(2);
      cb.run(c2, 0, 2);
      if (cb.check() == 1) {
         System.out.println("黑赢");
      } else if (cb.check() == 2) {
         System.out.println("白赢");
      }

      ChessPiece c3 = new ChessPiece(2);
      cb.run(c3, 0, 3);
      if (cb.check() == 1) {
         System.out.println("黑赢");
      } else if (cb.check() == 2) {
         System.out.println("白赢");
      }

      ChessPiece c4 = new ChessPiece(2);
      cb.run(c4, 0, 4);
      if (cb.check() == 1) {
         System.out.println("黑赢");
      } else if (cb.check() == 2) {
         System.out.println("白赢");
      }

   }
}
