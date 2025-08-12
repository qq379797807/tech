import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) throws IOException {
        // System.out.println("sss");

        // Main.readFile();
        Main.readFile();

        // System.out.println("aaaa\nbbb");
    }


    public static void readFile() throws IOException {
        // 创建一个FileReader对象:
        // Reader reader = new FileReader("C:\\Users\\37979\\Desktop\\lty\\day2\\data.txt"); // 字符编码是???
        Reader reader= new FileReader("data.txt",StandardCharsets.UTF_8);

        StringBuffer sb=new StringBuffer();


        for (;;) {
            int n = reader.read(); // 反复调用read()方法，直到返回-1
            if (n == -1) {
                break;
            }
            // System.out.print((char)n); 
            sb.append((char)n);// 打印char
        }
        reader.close(); // 关闭流

        String result=sb.toString();
        System.out.println(result);

    }


}