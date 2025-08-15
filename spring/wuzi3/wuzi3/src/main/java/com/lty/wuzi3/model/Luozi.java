package com.lty.wuzi3.model;

import com.lty.wuzi3.chess.piece.ChessPieceBlack;
import com.lty.wuzi3.chess.piece.ChessPieceWhite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Luozi {

     String cellId;

     String color;


     public Luozi(int row, int coulnm, int color) {

          this.cellId = row + "-" + coulnm;
          // this.color = "black".equals(color) ? 1 : 2;
          this.color = color == 1 ? "black" : "white";

     }
}
