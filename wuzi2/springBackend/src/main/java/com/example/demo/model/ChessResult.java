package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChessResult {
    
    int result; // 1 for black win, 2 for white win
}
