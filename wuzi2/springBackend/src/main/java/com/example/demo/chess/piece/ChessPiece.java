package com.example.demo.chess.piece;


public abstract class ChessPiece {

    // 黑子type=1
 //  ba1  2
    public int type;

    public ChessPiece( int _type ){
        this.type=_type;
    }

    public void Cheat(int type){
        this.type=type;
    }

}

