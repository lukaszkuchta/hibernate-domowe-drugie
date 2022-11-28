package metody;

import java.util.Scanner;

public class Usuwanie {
    public long usuwanieKtoreId(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id ktore chcesz usunac");
        String idString = scanner.nextLine();
        return Long.parseLong(idString);
    }
}
