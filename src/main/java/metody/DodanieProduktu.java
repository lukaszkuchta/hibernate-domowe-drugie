package metody;

import model.Kategoria;
import model.Produkt;

import java.util.Scanner;

public class DodanieProduktu {
    public Produkt dodanieProduktu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("podaj nazwe");
        String nazwa = scanner.nextLine();
        System.out.println("podaj kategorie(ZYWNOSC / NAPOJE / INNE");
        String kategoriaString = scanner.nextLine();
        kategoriaString = kategoriaString.toUpperCase();
        if(!kategoriaString.equals("ZYWNOSC") && !kategoriaString.equals("NAPOJE")){
            kategoriaString = "INNE";
        }
        Kategoria kategoria = Kategoria.valueOf(kategoriaString);


        return Produkt.builder()
                .nazwa(nazwa)
                .kategoria(kategoria)
                .build();
    }
}
