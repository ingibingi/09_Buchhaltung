import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Kategorie {
    static ArrayList<Kategorie> listeKategorien = new ArrayList<>();
    int id;
    String bezeichnung;
    String kurzbezeichnung;
    Boolean istEingang;

    public Kategorie(ResultSet SingleResultSet) throws SQLException {
            this.id = SingleResultSet.getInt(1);
            this.bezeichnung = SingleResultSet.getString(2);
            this.kurzbezeichnung = SingleResultSet.getString(3);
            this.istEingang = SingleResultSet.getBoolean(4);
            listeKategorien.add(this);
    }

    public Kategorie(String Bezeichnung, String Kurzbezeichnung, Boolean istEingang){
        this.id = listeKategorien.size()+1;
        this.bezeichnung =Bezeichnung;
        this.kurzbezeichnung =Kurzbezeichnung;
        this.istEingang=istEingang;
        listeKategorien.add(this);
    }

    @Override
    public String toString() {
        return this.bezeichnung;
    }
}
