import interfejsy.DodanieProduktu;
import model.Produkt;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("co chcesz zrobic? trzeba wybrac liczbe");
        System.out.println("1 - dodanie produktu");
        System.out.println("2 - dodanie sprzedazy, jesli produkt istnieje");
        System.out.println("3 - wyswietlenie listy produktu");
        System.out.println("4 - wyswietlenie listy sprzedazy");
        System.out.println("5 - wyswietlenie listy sprzedazy danego produktu");
        System.out.println("6 - usuwanie sprzedazy");
        System.out.println("7 - usuwanie produktu, razem ze sprzedazami");
        System.out.println("8 - usuwanie produktu, sprzedaze dla tego produktu beda null");
        String odpowiedz = scanner.nextLine();

        if(odpowiedz.equals("1")){
            try(Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                DodanieProduktu dodanieProduktu = new DodanieProduktu();
                session.persist(dodanieProduktu.dodanieProduktu());
                transaction.commit();
            }catch (Exception e){
                System.err.println("blad");
            }
        }
    }
}
