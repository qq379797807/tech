package com.lty.wuzi3.controller;

import org.springframework.web.bind.annotation.RestController;

import com.lty.wuzi3.model.ChessResult;
import com.lty.wuzi3.model.Luozi;
import com.lty.wuzi3.service.ChessService;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController()
@RequestMapping("/api")
public class ChessController {

    private final ChessService chessService;


    public ChessController(ChessService chessService) {
        // Constructor logic if needed
        this.chessService = chessService;
    }


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }


    @PostMapping("/luozi")
    public ChessResult luozi(@RequestBody Luozi entity) {

        System.out.println(entity.getCellId());

        return this.chessService.luozi(entity);
 
    }

    @GetMapping("/state")
    public ArrayList<Luozi> getState() {
        return this.chessService.getState();
    }


     @PostMapping("/login")
    public int login() {
        return this.chessService.login();
    }

     @GetMapping("/logout")
    public boolean logou(@RequestParam int playerId) {
        return this.chessService.logout(playerId);
    }


}
