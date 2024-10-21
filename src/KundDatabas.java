import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class KundDatabas {
    private List<Kund> kunder;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //lista för att lägga in kunder i kunddatabas
    public KundDatabas() {
        kunder = new ArrayList<>();
    }

    // Metod för att kunna läsa in alla kunder från filen
    public void läsIn(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // För att hitta kommatecknet som separerar personnummer och namn
                int commaIndex = line.indexOf(",");

                // String manupilation för att kunna extrahera namn och personnummer
                String personnummer = line.substring(0, commaIndex).trim(); // allt från början fram till kommatecknet
                String namn = line.substring(commaIndex + 1).trim(); // allt efter kommatecknet till slutet av raden

                // Senaste betalningsdatumet finns på nästa rad
                String datumSenasteBetalningStr = reader.readLine();
                LocalDate datumSenasteBetalning = LocalDate.parse(datumSenasteBetalningStr, dateFormatter);

                // Av allt detta, skapa en ny kund och lägg till i listan enligt följande format
                kunder.add(new Kund(personnummer, namn, datumSenasteBetalning));
            }
        }
    }

    // Sök kund baserat på personnummer eller namn
    public Kund hittaKund(String input) {
        for (Kund kund : kunder) {
            if (kund.getPersonnummer().equals(input) || kund.getNamn().equalsIgnoreCase(input)) {
                return kund;
            }
        }
        return null; // Om kunden inte hittas
    }
}


