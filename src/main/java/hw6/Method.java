package hw6;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Method {

    public static void main(String[] args) {
        Method method = new Method();

        System.out.println("Задание 1.");
        System.out.println(Arrays.toString(method.arrIncludeFour(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7})));
        System.out.println("Задание 2.");
        System.out.println(method.arrOneFour(new int[]{1, 4, 4, 1, 1, 4, 3}));

    }
    //2. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
    // Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
    // идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку, иначе в методе
    // необходимо выбросить RuntimeException. Написать набор тестов для этого метода (по 3-4 варианта входных данных).
    // Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].

    public int[] arrIncludeFour(int[] array) {
        List<Integer> fourNumbers = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 4) {
                fourNumbers.add(i);
            }
        }
        if(fourNumbers.isEmpty()){
            throw new RuntimeException("Массив должен содержать хотя бы одну 4");
        }
        int[] modArray = Arrays.stream(Arrays.stream(array).toArray()).skip(fourNumbers.get(fourNumbers.size() - 1) + 1).toArray();

        return modArray;
    }
    //3. Написать метод, который проверяет состав массива из чисел 1 и 4.
    // Если в нем нет хоть одной четверки или единицы, то метод вернет false;
    // Написать набор тестов для этого метода (по 3-4 варианта входных данных).
    public boolean arrOneFour(int[] array){
        boolean checkOne = Arrays.stream(array).anyMatch(z -> z == 1);
        boolean checkFour = Arrays.stream(array).anyMatch(z -> z == 4);
        int[] findAnotherNum = Arrays.stream(array).filter(z -> z != 4 && z != 1).toArray();
        if(checkOne && checkFour && findAnotherNum.length == 0){
            return true;
        }
        return false;
    }



}








