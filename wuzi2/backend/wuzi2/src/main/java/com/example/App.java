package com.example;

import java.io.IOException;
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
public class App 
{
    public static void main( String[] args )  throws IOException
    {
         // 创建 HTTP 服务器，监听 8000 端口
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

          // 设置路由和处理程序
        server.createContext("/", new RootHandler());

        // 启动服务器
        server.setExecutor(null); // 使用默认执行器
        server.start();
        System.out.println("服务器已启动，访问地址: http://localhost:8000");
    }


    // 根路径处理程序
    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
          
            String response="{\"message\": \"Hello, World!\"}";
            
            // 设置正确的字符编码
            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
            sendResponse(exchange, response);
        }
    }

    // 通用响应发送方法 - 修复乱码的核心
    private static void sendResponse(HttpExchange exchange, String response) throws IOException {
        // 使用 UTF-8 编码转换字符串为字节
        byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);
        
        // 发送响应头时指定正确的长度
        exchange.sendResponseHeaders(200, responseBytes.length);
        
        // 写入响应体
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
        }
    }
    
}
