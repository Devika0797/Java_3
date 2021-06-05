package hw_1;

import java.util.ArrayList;
import java.util.Arrays;


public class Box<T extends Fruit> {
    private ArrayList <T> fruits; // список фруктов
    private String fruitsType; // тип фруктов

    public Box(T ... fruits) {
        this.fruits = new ArrayList<>(Arrays.asList(fruits));
        fruitsType = fruits.getClass().getTypeName();
    }

    //d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов
    //   и вес одного фрукта(вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
    public float getWeight() {
        float weight = 0.0f;
        for (T o : fruits) {
            weight += o.getWeight();
        }
        return weight;
    }

    // e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку
    //    с той, которую подадут в compare в качестве параметра, true - если их веса равны, false
    //    в противном случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
    public boolean compare (Box<?> box) {
        return Math.abs(this.getWeight() - box.getWeight()) < 0.0001;
    }

    //f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку
    //   (помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами), соответственно
    //   в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
    public void sprinkle (Box<T> box) {
        box.fruits.addAll(fruits);
        fruits.clear();
    }

    // g. Не забываем про метод добавления фрукта в коробку.
    public void add (T fruit) { // добавляем ОДИН фрукт
        fruits.add(fruit);
    }

}
