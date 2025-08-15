package com.example.demo.service;

import com.example.demo.chess.ChessBoard;
import com.example.demo.model.ChessResult;
import com.example.demo.model.Luozi;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ChessService {

    ChessBoard cb=new ChessBoard();
    
    public ChessService() {
        
       
    }


     public ChessResult luozi(Luozi luozi) {

        int result=this.cb.run(luozi);

        return new ChessResult(result);
    }


     public ArrayList<Luozi> state() {
       return this.cb.getState();
     }

   
}