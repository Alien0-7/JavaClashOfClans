package Grafica.JavaClashOfClans;

public class MyMain {
    public static void main(String[] args) {

        long startTime = System.nanoTime();

        new MainWindow();

        long endTime = System.nanoTime();
        System.out.println("[INFO] " + "GUI opened in " + (endTime - startTime) + " nanoseconds, and that equals to " + (endTime - startTime)/Math.pow(10,9) + " seconds");
    }
}
