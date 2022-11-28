import metody.DodanieProduktu;
import metody.DodanieSprzedazy;
import metody.SzukanyProdukt;
import model.Produkt;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

import static metody.SzukanyProdukt.szukanyProdukt;

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
        } else if(odpowiedz.equals("2")){
            try(Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                System.out.println("podaj id produktu");
                String idProduktu = scanner.nextLine();
                Long id = Long.parseLong(idProduktu);
                DodanieSprzedazy dodanieSprzedazy = new DodanieSprzedazy();
                szukanyProdukt = session.get(Produkt.class, id);
                if(szukanyProdukt != null) {
                    session.persist(dodanieSprzedazy.dodanieSprzedazy());
                    transaction.commit();
                }else{
                    System.err.println("taki produkt nie istnieje");
                }
            }catch (Exception e){
                System.err.println("blad");
            }
        }
    }
}
