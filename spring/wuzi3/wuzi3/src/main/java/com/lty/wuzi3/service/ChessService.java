package com.lty.wuzi3.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.lty.wuzi3.chess.ChessBoard;
import com.lty.wuzi3.model.ChessResult;
import com.lty.wuzi3.model.Luozi;

@Service
public class ChessService {


    ChessBoard cb= new ChessBoard();


    public ChessResult luozi(Luozi entity) {

        var res=this.cb.run(entity);

        System.out.println(res);

        return new ChessResult(res);
    }


    public ArrayList<Luozi> getState() {

        return this.cb.getState();
    }
    
}
