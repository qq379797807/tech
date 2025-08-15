package com.example.demo.model;

import com.example.demo.chess.piece.ChessPieceBlack;
import com.example.demo.chess.piece.ChessPieceWhite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Luozi {

    String cellId;

    String color;

    // public Luozi(String cellId, String color) {
    //     this.cellId = cellId;
    //     this.color = color;
    // }


   public Luozi(int row,int coulnm, int color) {
        
       this.cellId = row + "-" + coulnm;
       this.color = color==1? "black" : "white";
    
    }
    
}
