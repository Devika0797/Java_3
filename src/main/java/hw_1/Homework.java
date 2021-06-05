package hw_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Homework {
    public static void main(String[] args) {
        //1. Написать метод, который меняет два элемента массива местами.
        // (массив может быть любого ссылочного типа);
        String[] arr = {"111", "abc", "333", "444", "cde"};

        System.out.println("Задание 1.\n" + "Заданный массив: " + Arrays.toString(arr));
        swapElements(arr, 1,3);
        System.out.println("Изменение элементов массива: " + Arrays.toString(arr));
        System.out.println("===============");

        //2. Написать метод, который преобразует массив в ArrayList;
        System.out.println("Задание 2.");
        List<String> arrayList = convertToList(arr);
        System.out.println("Преобразование массива: " + arrayList);
        System.out.println("===============");

        //3. Большая задача:
        //a. +Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)

        //b. +Класс Box в который можно складывать фрукты, коробки условно сортируются
        //   по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;

        //c. Для хранения фруктов внутри коробки можете использовать ArrayList;

        //d.+ Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов
        //   и вес одного фрукта(вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);

        //e. +Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку
        //   с той, которую подадут в compare в качестве параметра, true - если их веса равны, false
        //   в противном случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);

        //f.+ Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку
        //   (помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами), соответственно
        //   в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;

        //g. +Не забываем про метод добавления фрукта в коробку.
        System.out.println("Задание 3.");
        Box<Apple> appleBox1 = new Box<>(new Apple(), new Apple(), new Apple());
        Box<Apple> appleBox2 = new Box<>(new Apple(), new Apple(), new Apple(), new Apple());
        Box<Orange> orangeBox1 = new Box<>(new Orange(), new Orange(), new Orange());
        Box<Orange> orangeBox2 = new Box<>(new Orange(), new Orange(), new Orange(), new Orange());

        appleBox1.add(new Apple());
        appleBox2.add(new Apple());
        orangeBox1.add(new Orange());
        orangeBox2.add(new Orange());
        System.out.println("Коробка 1: " + appleBox1.getWeight());
        System.out.println("Коробка 2: " + appleBox2.getWeight());
        System.out.println("Коробка 3: " + orangeBox1.getWeight());
        System.out.println("Коробка 4: " + orangeBox2.getWeight());
        System.out.println("Сравнить коробки 1 и 2: " + appleBox1.compare(appleBox2));
        System.out.println("Сравнить коробки 3 и 4: " + orangeBox1.compare(orangeBox2));
        appleBox1.sprinkle(appleBox2);
        orangeBox1.sprinkle(orangeBox2);
        System.out.println("Коробка 1: " + appleBox1.getWeight());
        System.out.println("Коробка 2: " + appleBox2.getWeight());
        System.out.println("Коробка 3: " + orangeBox1.getWeight());
        System.out.println("Коробка 4: " + orangeBox2.getWeight());

    }
// МЕТОДЫ
    //1. swap elements
    public static <T> void swapElements(T[] arr, int i1, int i2) {
        T var = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = var;
    }
    // 2. convert array
    public static <T> List <T> convertToList(T[] arr) {
       return new ArrayList<T>(Arrays.asList(arr));
    }

}
