package com.example;

import java.io.IOException;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class StateHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // 添加 CORS 头
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

        if ("OPTIONS".equals(exchange.getRequestMethod())) {
            // 处理预检请求
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        // 处理实际请求

        var list = App.cb.getState();

        String response = this._toJson(list);

        // 设置正确的字符编码
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        App.sendResponse(exchange, response);
    }

    private String _toJson(ArrayList<Luozi> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < list.size(); i++) {
            Luozi luozi = list.get(i);
            sb.append("{");
            sb.append("\"cellId\":\"").append(luozi.row).append("-").append(luozi.column).append("\",");
            sb.append("\"color\":\"").append(luozi.piece.type == 1 ? "black" : "white").append("\"");
            sb.append("}");
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }

        sb.append("]");
        return sb.toString();
    }

}
