package hu.auditorium;

import hu.auditorium.controller.ChairService;
import hu.auditorium.model.domain.Chair;
import hu.auditorium.model.service.*;

import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;

public class App {
    
    private final ChairService chairService;
    private final Console console;
    private final FileWriter fileWriter;
    
    private App() {
        console = new Console(new Scanner(System.in));
        fileWriter = new FileWriter("szabad.txt");
        DataApi dataApi = new DataApi(new FileReader(), new DataParser());
        List<Chair> chairs = dataApi.getChairs("foglaltsag.txt","kategoria.txt");
        chairService = new ChairService(chairs);
    }

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        System.out.println("2. feladat");
        System.out.println("Adja meg a keresett szék helyét:");
        System.out.print(" -  sor száma: ");
        int row = console.read();
        System.out.print(" - szék száma: ");
        int number = console.read();
    }
}
