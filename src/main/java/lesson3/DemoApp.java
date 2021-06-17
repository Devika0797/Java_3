package lesson3;

import java.io.File;
import java.io.IOException;

public class DemoApp {
    public static void main(String[] args) throws IOException{
        File dir = new File("filedir");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(dir,"demo.txt"); //создается ССЫЛКА на файл
        if (!file.exists()) {
            file.createNewFile(); // boolean - если файла нету, то создай
        }
        System.out.println(file.getName()); // посмотреть имя
        System.out.println(file.getAbsoluteFile().getName());// путь к файлу
        System.out.println(file.getParentFile());// получение родительского файла, если таковой укаан
        System.out.println(file.isDirectory());// является ли директорией
        System.out.println(file.isFile());
        System.out.println(file.length());//размер файла
        System.out.println(file.list());// возвращает строчки . listFiles() - возвращает объекты
        System.out.println(file.delete());//удалить файл
        System.out.println(file.mkdir()); // создает только последюю директорию. mkdirs() - создает все дерево директории

    }
}
