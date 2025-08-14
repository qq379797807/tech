package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

/**
 * Hello world!
 *
 */
public class App {

    static ChessBoard cb = new ChessBoard();

    public static void main(String[] args) throws IOException {
        // 创建 HTTP 服务器，监听 8000 端口
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // 设置路由和处理程序
        server.createContext("/api/luozi", new RootHandler());

        // 启动服务器
        server.setExecutor(null); // 使用默认执行器
        server.start();
        System.out.println("服务器已启动，访问地址: http://localhost:8000");
    }

    // 根路径处理程序
    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // 添加 CORS 头
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");

            // 处理 OPTIONS 请求（预检请求）
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            // 只处理 POST 请求
            if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                String response = "{\"error\": \"只支持POST请求\"}";
                sendResponse(exchange, response, 405);
                return;
            }

            // 读取请求体中的 JSON 内容
            StringBuilder sb = new StringBuilder();
            try (var is = exchange.getRequestBody()) {
                byte[] buffer = new byte[1024];
                int read;
                while ((read = is.read(buffer)) != -1) {
                    sb.append(new String(buffer, 0, read, StandardCharsets.UTF_8));
                }
            }
            String json = sb.toString();

            System.out.println("收到请求体: " + json);

            try {
                // 这里可以处理 json 内容
                String response = "{\"received\": " + json + ", \"status\": \"success\"}";

                // 设置正确的字符编码
                exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
                sendResponse(exchange, response);
            } catch (Exception e) {
                System.err.println("处理请求时发生错误: " + e.getMessage());
                e.printStackTrace();
                String errorResponse = "{\"error\": \"服务器内部错误\"}";
                exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
                sendResponse(exchange, errorResponse, 500);
            }
        }
    }

    // 通用响应发送方法 - 修复乱码的核心
    private static void sendResponse(HttpExchange exchange, String response) throws IOException {
        sendResponse(exchange, response, 200);
    }

    private static void sendResponse(HttpExchange exchange, String response, int statusCode) throws IOException {
        // 使用 UTF-8 编码转换字符串为字节
        byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);

        // 发送响应头时指定正确的长度
        exchange.sendResponseHeaders(statusCode, responseBytes.length);

        // 写入响应体
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
        }
    }

    public void startWuziqiGame() {
        // 这里可以添加五子棋游戏的启动逻辑
        System.out.println("五子棋游戏已启动");
        // 例如，初始化游戏状态、设置玩家等

    }

}
