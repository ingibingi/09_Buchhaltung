import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;


public class Buchung {
    static ArrayList<Buchung> listeBuchungen = new ArrayList<>();
    int id;
    Timestamp zuletztVeraendert;
    Kategorie kategorie;
    LocalDate datum;
    String zusatzinfo;
    double betrag;

    public Buchung(ResultSet SingleResultSet) throws SQLException {
        this.id = SingleResultSet.getInt(1);
        this.zuletztVeraendert = Timestamp.valueOf(SingleResultSet.getString(2));
        int kategorieID = SingleResultSet.getInt(3);
        this.kategorie = Kategorie.findKategorieById(kategorieID);
        this.datum = LocalDate.parse(SingleResultSet.getString(4));
        this.zusatzinfo = SingleResultSet.getString(5);
        this.betrag = SingleResultSet.getDouble(6);
        listeBuchungen.add(this);
    }

    public Buchung(Kategorie kategorie, LocalDate datum, String zusatzinfo, double betrag){
        this.id = listeBuchungen.size()+1;
        this.zuletztVeraendert = new Timestamp(System.currentTimeMillis());
        this.kategorie = kategorie;
        this.datum = datum;
        this.zusatzinfo = zusatzinfo;
        this.betrag = betrag;
        listeBuchungen.add(this);
    }

    @Override
    public String toString() {
        return this.kategorie.istEingang.toString()+"\t"
                +this.datum.toString()+"\t"
                +this.kategorie.kurzbezeichnung.toString()+"\t"
                +this.betrag+" €";
    }

    public static Buchung findBuchungById(int id){
        return listeBuchungen.stream().filter(buchung -> id== buchung.id).findFirst().orElse(null);
    }


}
