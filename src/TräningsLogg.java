import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TräningsLogg {
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Spara träningsinformation om kunden
    public void loggaTräning(Kund customer) {
        String idag = LocalDate.now().format(dateFormatter);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("training_log.txt", true))) {
            writer.write(customer.getPersonnummer() + ", " + customer.getNamn() + ", " + idag);
            writer.newLine();
            System.out.println("Tack, nu har träningsuppgiterna sparats.");
        } catch (IOException e) {
            System.out.println("Tyvärr kunde systemet inte spara träningsuppgifterna: " + e.getMessage());
        }
    }
}
