import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Kategorie {
    static ArrayList<String> ListeKategorien = new ArrayList<>();
    int ID;
    String Bezeichnung;
    String Kurzbezeichnung;
    Boolean istEingang;

    public Kategorie(ResultSet SingleResultSet) throws SQLException {
            this.ID = SingleResultSet.getInt(1);
            this.Bezeichnung = SingleResultSet.getString(2);
            this.Kurzbezeichnung = SingleResultSet.getString(3);
            this.istEingang = SingleResultSet.getBoolean(4);
            ListeKategorien.add(Bezeichnung);
    }

    public Kategorie(int ID, String Bezeichnung, String Kurzbezeichnung, Boolean istEingang){
        this.ID = ID;
        this.Bezeichnung=Bezeichnung;
        this.Kurzbezeichnung=Kurzbezeichnung;
        this.istEingang=istEingang;
        ListeKategorien.add(Bezeichnung);
    }
}
