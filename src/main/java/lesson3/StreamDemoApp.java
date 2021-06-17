package lesson3;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamDemoApp {
    public static void main(String[] args) {
        String str = "My String";
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File("demo.txt"))){
            fileOutputStream.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fileInputStream = new FileInputStream("demo.txt")) {
            byte[] bytes = new byte[100];
            fileInputStream.read(bytes);
            System.out.print(new String(bytes));
        } catch (IOException e ) {
            e.printStackTrace();
        }
//        byte[] bytes = {65, 66, 67}; // массив
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);//байтовый массив
//        int x;
//        while ((x = byteArrayInputStream.read())!= -1) { // считывание и присвоение переменной х. считываем х доо тех пор пока он существует и выводим на экран
//            System.out.println(x);
//        }


    }
}
