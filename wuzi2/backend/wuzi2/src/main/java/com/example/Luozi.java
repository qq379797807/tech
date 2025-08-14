package com.example;

import com.example.piece.ChessPiece;
import com.example.piece.ChessPieceBlack;
import com.example.piece.ChessPieceWhite;

public class Luozi {

    int row;
    int column;
    // int color; 
    
    ChessPiece piece;// 1 for black, 2 for white
    
    public Luozi(int row,int coulnm, int color) {
        
        this.row = row;
        this.column = coulnm;
        // this.color = "black".equals(color) ? 1 : 2;
        this.piece = color==1? new ChessPieceBlack() : new ChessPieceWhite();

    }

    public Luozi(String json) {
        // 移除 JSON 字符串的花括号
        json = json.substring(1, json.length() - 1);
        
        // 分割成键值对
        String[] pairs = json.split(",");
        String cellId = "";
        String color = "";
        
        // 解析每个键值对
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            String key = keyValue[0].trim().replace("\"", "");
            String value = keyValue[1].trim().replace("\"", "");
            
            if ("cellId".equals(key)) {
                cellId = value;
            } else if ("color".equals(key)) {
                color = value;
            }
        }
        
        // 解析 cellId（从 "2-5" 格式转换为 x 和 y 坐标）
        this.row = Integer.parseInt(cellId.split("-")[0]);
        this.column = Integer.parseInt(cellId.split("-")[1]);
        
        // 设置颜色（1 表示黑色，2 表示白色）
        this.piece = "black".equals(color)? new ChessPieceBlack() : new ChessPieceWhite();
    }
}
