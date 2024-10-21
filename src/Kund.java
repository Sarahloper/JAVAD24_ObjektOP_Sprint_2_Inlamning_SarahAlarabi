import java.time.LocalDate;

public class Kund {
        private String personnummer;
        private String namn;
        private LocalDate datumSenasteBetalning;

        public Kund(String personnummer, String namn, LocalDate datumSenasteBetalning) {
            this.personnummer = personnummer;
            this.namn = namn;
            this.datumSenasteBetalning = datumSenasteBetalning;
        }

        public String getPersonnummer() {
            return personnummer;
        }

        public String getNamn() {
            return namn;
        }

        public LocalDate getDatumSenasteBetalning() {
            return datumSenasteBetalning;
        }

        public boolean ärAktivMedlem() {
            // Uträkning med LocalDate.now för att kontrollera om kunden har betalat avgiften för mindre än 1 år sedan
            return LocalDate.now().minusYears(1).isBefore(datumSenasteBetalning);
        }
    }

