import java.io.IOException;
import java.util.Scanner;

public class BestGymEverSystem {
    private KundDatabas kundDatabas;
    private TräningsLogg träningsLogg;

    public BestGymEverSystem() {
        kundDatabas = new KundDatabas();
        träningsLogg = new TräningsLogg();
    }
    //läsa in kunddatabasen, med try catch om filen ej funkar eller hittas
    public void läsIn() {
        try {
            kundDatabas.läsIn("/Users/java/IdeaProjects/Objektorienterad Programmering och Java/Sprint1/JAVAD24 - ObjektOP - Sprint 2 - Inlamning - Sarah Alarabi/src/customers.txt");

        } catch (IOException e) {
            System.out.println("Tyvärr kunde systemet inte läsa in kunddata från filen: " + e.getMessage());
            return;
        }

        // Scanner för att läsa in input från användaren, läggs in i string variabel "input"
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vänligen fyll i kundens personnummer eller namn:");
        String input = scanner.nextLine();

        // Sen söker systemet efter kunden i kunddatabas klassen med hjälp av hitta kund metoden, lägger in i "kund"
        Kund kund = kundDatabas.hittaKund(input);

        //om kund returns null betyder det att den inte finns med och man får felmeddelande + kund loggas.
        if (kund == null) {
            System.out.println("Personen du fyllt i finns inte i filen och är inte behörig.");
            träningsLogg.loggaTräning(kund);

            //annars, kolla om kund är aktiv medlem genom ärAktivMedlem metoden.då skrivs det ut och kund loggas.
        } else if (kund.ärAktivMedlem()) {
            System.out.println(kund.getNamn() + " (" + kund.getPersonnummer() + ") är en nuvarande medlem.");
            träningsLogg.loggaTräning(kund);
            //annars är kund föredetta, skriv ut meddelande och logga
        } else {
            System.out.println(kund.getNamn() + " (" + kund.getPersonnummer() + ") är en föredetta kund.");
            träningsLogg.loggaTräning(kund);
        }

        // Stäng scanner
        scanner.close();
    }

    public static void main(String[] args) {
        BestGymEverSystem bestGymEverSystem = new BestGymEverSystem();
        bestGymEverSystem.läsIn();
    }
}