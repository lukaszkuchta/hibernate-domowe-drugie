package metody;

import com.sun.tools.javac.Main;
import model.Produkt;
import model.Sprzedaz;

import java.util.Scanner;

public class DodanieSprzedazy {

    public Sprzedaz dodanieSprzedazy(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("podaj cene");
        String cenaString = scanner.nextLine();
        double cena = Double.parseDouble(cenaString);
        System.out.println("podaj ilosc");
        String iloscString = scanner.nextLine();
        double ilosc = Double.parseDouble(iloscString);

        return Sprzedaz.builder()
                .produkt(SzukanyProdukt.szukanyProdukt)
                .cena(cena)
                .ilosc(ilosc)
                .build();

    }


}
