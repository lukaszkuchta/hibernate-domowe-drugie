import jakarta.persistence.TypedQuery;
import metody.DodanieProduktu;
import metody.DodanieSprzedazy;
import metody.Usuwanie;
import model.Produkt;
import model.Sprzedaz;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
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
        } else if(odpowiedz.equals("3")){
            try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()){
                TypedQuery<Produkt> zapytanie = session.createQuery("FROM Produkt", Produkt.class);
                List<Produkt> lista = zapytanie.getResultList();
                lista.forEach(System.out::println);
            }catch (Exception e) {
                System.err.println("blad");
            }
        }else if(odpowiedz.equals("4")) {
            try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
                TypedQuery<Sprzedaz> zapytanie = session.createQuery("FROM Sprzedaz", Sprzedaz.class);
                List<Sprzedaz> lista = zapytanie.getResultList();
                lista.forEach(System.out::println);
            } catch (Exception e) {
                System.err.println("blad");
            }
        }else if(odpowiedz.equals("5")){
            try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()){
                TypedQuery<Sprzedaz> zapytanie = session.createQuery("FROM Sprzedaz", Sprzedaz.class);
                System.out.println("podaj id przedmiotu, ktorego chcesz zobaczyc sprzedaz");
                String idPrzedmiotuDoWyswietleniaString = scanner.nextLine();
                Long idPrzedmiotuDoWyswietlenia = Long.parseLong(idPrzedmiotuDoWyswietleniaString);
                Sprzedaz idProdukt = new Sprzedaz();
                List<Sprzedaz> lista = zapytanie.getResultList();
                if(idProdukt.getId() != null) {
                    for (Sprzedaz sprzedaz : lista) {
                        System.out.println(sprzedaz);
                    }
                }else{
                    System.out.println("null");
                }
            }
        }else if(odpowiedz.equals("6")){
            try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                Sprzedaz sprzedaz = session.get(Sprzedaz.class, new Usuwanie().usuwanieKtoreId());
                if (sprzedaz != null) {
                    session.remove(sprzedaz);
                } else {
                    System.err.println("Sprzedaz taka nie istnieje");
                }
                transaction.commit();
            }catch (Exception e) {
                System.err.println("blad");
            }
        }else if(odpowiedz.equals("7")){
            try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                Produkt produkt = session.get(Produkt.class, new Usuwanie().usuwanieKtoreId());
                if (produkt != null){
                    if (!produkt.getSprzedaz().isEmpty()){
                        for (Sprzedaz sprzedaz : produkt.getSprzedaz()) {
                            session.remove(sprzedaz);
                        }
                        session.remove(produkt);
                    }
                }else{
                    System.err.println("taki produkt nie istnieje");
                }
                transaction.commit();
            }catch (Exception e) {
                System.err.println("blad");
            }
        }else if(odpowiedz.equals("8")){
            try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                Produkt produkt = session.get(Produkt.class, new Usuwanie().usuwanieKtoreId());
                if (produkt != null){
                    if (!produkt.getSprzedaz().isEmpty()){
                        for (Sprzedaz sprzedaz : produkt.getSprzedaz()) {
                            Sprzedaz sprzedazZNullem = Sprzedaz.builder()
                                    .produkt(null)
                                    .cena(sprzedaz.getCena())
                                    .ilosc(sprzedaz.getIlosc())
                                    .build();
                            session.persist(sprzedazZNullem);
                        }
                        for (Sprzedaz sprzedaz : produkt.getSprzedaz()) {
                            session.remove(sprzedaz);
                        }
                        session.remove(produkt);
                    }
                } else{
                    System.err.println("taki produkt nie istnieje");
                }
                transaction.commit();
            } catch (Exception e) {
                System.err.println("blad");
            }
        } else{
            System.err.println("Nie poprawna komenda");
        }
    }
}
