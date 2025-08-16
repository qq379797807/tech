package com.lty.wuzi3.service;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.stereotype.Service;

import com.lty.wuzi3.chess.ChessBoard;
import com.lty.wuzi3.model.ChessResult;
import com.lty.wuzi3.model.Luozi;

@Service
public class ChessService {


    ChessBoard cb= new ChessBoard();
    private HashSet<Integer> playerSet =new HashSet<>();

    public ChessService() {
        this.playerSet.add(1);
        this.playerSet.add(2);
        // Constructor logic if needed
    }


    public ChessResult luozi(Luozi entity) {

        var res=this.cb.run(entity);

        System.out.println(res);

        return new ChessResult(res);
    }


    public ArrayList<Luozi> getState() {

        return this.cb.getState();
    }


    public int login() {
        if (playerSet.isEmpty()) {
            return -1; // or throw an exception to indicate no more players can join
        }
        
        int playerId = this.playerSet.iterator().next();
        this.playerSet.remove(playerId);
        
        return playerId;
    }


    public boolean logout(int playerId) {
        if (this.playerSet.contains(playerId)) {
            return false; // Player not logged in
        }
        
        this.playerSet.add(playerId);
        return true;
    }
    
}
