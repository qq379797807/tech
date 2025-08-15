package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ChessResult;
import com.example.demo.model.Luozi;
import com.example.demo.model.User;
import com.example.demo.service.ChessService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class ChessController {

    private final ChessService service;

    public ChessController(ChessService service) {
        this.service = service;
    }

    @PostMapping("/luozi")
    public ChessResult luozi(@RequestBody Luozi luozi) {

        System.out.println(luozi);

        return service.luozi(luozi);
    }

    @GetMapping("/state")
    public ArrayList<Luozi> state() {

        return service.state();
    }
}