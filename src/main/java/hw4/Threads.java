package hw4;

public class Threads {

    //1. Создать три потока, каждый из которых выводит определенную букву (A, B и C)
    // 5 раз (порядок – ABСABСABС).Используйте wait/notify/notifyAll.

    private final Object locker = new Object();
    String current = "C";

    public static void main(String[] args) {

        Threads threads = new Threads();

        Thread t1 = new Thread(() -> {
            threads.printLetterA();
        });
        Thread t2 = new Thread(() -> {
            threads.printLetterB();
        });
        Thread t3 = new Thread(() -> {
            threads.printLetterC();
        });

        t1.start();
        t2.start();
        t3.start();
    }

        public void printLetterA(){
            synchronized (locker){
                for (int i = 0; i < 5; i++) {
                    while (!current.equals("C")){
                        try {
                            locker.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("A");
                    current = "A";
                    locker.notifyAll();
                }
            }
        }

        public void printLetterB(){
            synchronized (locker){
                for (int i = 0; i < 5; i++) {
                    while (!current.equals("A")){
                        try {
                            locker.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("B");
                    current = "B";
                    locker.notifyAll();
                }
            }
        }

        public void printLetterC(){
            synchronized (locker){
                for (int i = 0; i < 5; i++) {
                    while (!current.equals("B")){
                        try {
                            locker.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("C");
                   current = "C";
                    locker.notifyAll();
                }
            }
        }


}
