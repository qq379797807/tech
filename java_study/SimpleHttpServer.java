import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class SimpleHttpServer {

    public static void main(String[] args) throws IOException {
        // 创建 HTTP 服务器，监听 8000 端口
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        
        // 设置路由和处理程序
        server.createContext("/", new RootHandler());
        server.createContext("/hello", new HelloHandler());
        server.createContext("/api", new ApiHandler());
        server.createContext("/user", new UserHandler());
        
        // 启动服务器
        server.setExecutor(null); // 使用默认执行器
        server.start();
        System.out.println("服务器已启动，访问地址: http://localhost:8000");
        System.out.println("中文乱码问题已修复!");
    }
    
    // 根路径处理程序
    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // String response = "<html><head><meta charset='UTF-8'></head><body>" +
            //         "<h1>Java HttpServer 示例 - 中文乱码已修复</h1>" +
            //         "<p>可用端点：</p>" +
            //         "<ul>" +
            //         "<li><a href='/hello'>/hello</a> - 简单问候</li>" +
            //         "<li><a href='/api'>/api</a> - JSON API</li>" +
            //         "<li><a href='/user'>/user</a> - 用户信息</li>" +
            //         "</ul>" +
            //         "</body></html>";

            String response="你好 java";
            
            // 设置正确的字符编码
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            sendResponse(exchange, response);
        }
    }
    
    // /hello 路径处理程序
    static class HelloHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "<html><head><meta charset='UTF-8'></head><body>" +
                    "<h1>你好，世界！</h1>" +
                    "<p>这是一个支持中文的 Java HTTP 服务</p>" +
                    "<p>测试中文：编码问题已解决！✓</p>" +
                    "<a href='/'>返回首页</a>" +
                    "</body></html>";
            
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            sendResponse(exchange, response);
        }
    }
    
    // /api 路径处理程序
    static class ApiHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "{\"message\": \"欢迎使用Java API服务\", \"status\": \"成功\", \"author\": \"张三\", \"notes\": \"中文乱码问题已解决\"}";
            
            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
            sendResponse(exchange, response);
        }
    }
    
    // /user 路径处理程序 - 模拟用户数据
    static class UserHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // 模拟用户数据
            String userData = "<html><head><meta charset='UTF-8'></head><body>" +
                    "<h1>用户信息</h1>" +
                    "<table border='1'>" +
                    "<tr><th>ID</th><th>姓名</th><th>邮箱</th></tr>" +
                    "<tr><td>1</td><td>张三</td><td>zhangsan@example.com</td></tr>" +
                    "<tr><td>2</td><td>李四</td><td>lisi@example.com</td></tr>" +
                    "<tr><td>3</td><td>王五</td><td>wangwu@example.com</td></tr>" +
                    "</table>" +
                    "<a href='/'>返回首页</a>" +
                    "</body></html>";
            
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            sendResponse(exchange, userData);
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