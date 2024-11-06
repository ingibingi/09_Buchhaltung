import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    //Create Connection to MariaDB
    static Connection connection;
    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/haushaltsbuchung","root","");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {

        //Kategorien aus DB auslesen
        Statement statementKategorien = connection.createStatement();
        ResultSet resultSetKategorien = statementKategorien.executeQuery("SELECT * FROM t_Kategorie");
        while (resultSetKategorien.next()) {
            new Kategorie(resultSetKategorien);
        }

        //Buchungen aus DB auslesen
        Main.loadBuchungenAlle();
        Buchung.removeFilter();

        System.out.println("\nBuchungen");
        System.out.println("Übersicht: "+Buchung.getUebersichtBuchungen());

        GUI myGUI = new GUI();
    }

    public static void loadBuchungenAlle () throws SQLException {
        Statement statementBuchungen = Main.connection.createStatement();
        ResultSet resultSetBuchungen = statementBuchungen.executeQuery("SELECT * FROM t_Buchung");
        Buchung.listeBuchungenAlle = new ArrayList<>();
        while (resultSetBuchungen.next()){
            new Buchung(resultSetBuchungen);
        }
    }

    public static void loadBuchungenAuswahl (LocalDate dtFrom, LocalDate dtUntil, Kategorie kategorie) throws SQLException{
        //Build SQL-Query
        String myQuery = "";

        if (dtFrom != null) {
            myQuery.concat(dtFrom + " <= b.Datum");
        }

        if (dtUntil != null) {
            if (myQuery.length()>0){
                myQuery.concat(" AND ");
            }
            myQuery.concat(dtUntil + " <= b.Datum");
        }

        if (kategorie != null) {
            if (myQuery.length()>0){
                myQuery.concat(" AND ");
            }
            myQuery.concat(kategorie.id + " = b.Kategorie");
        }

        if (myQuery.length()>0){
            myQuery = "SELECT * FROM t_Buchung b WHERE " + myQuery;
        } else {
            myQuery = "SELECT * FROM t_Buchung";
        }

        Statement statementBuchungen = connection.createStatement();
        ResultSet resultSetBuchungen = statementBuchungen.executeQuery(myQuery);
        Buchung.listeBuchungenAuswahl = new ArrayList<>();
        while (resultSetBuchungen.next()){
            new Buchung(resultSetBuchungen);
        }
    }

    public static void insertBuchung(Buchung buchung) throws SQLException {
        String myQuery = "INSERT INTO t_Buchung " +
            "(`ID`, `ZuletztVeraendert`, `Kategorie`, `Datum`, `Zusatzinfo`, `Betrag`) " +
            "VALUES (";
            //ID
                if(buchung.id>0){
                    myQuery = myQuery.concat("'"+buchung.id+"',");
                }else {
                    myQuery = myQuery.concat("NULL,");
                }
            //ZuletztVeraendert
                 myQuery = myQuery.concat("current_timestamp(),");
            //Kategorie
                myQuery = myQuery.concat(buchung.kategorie.id+",");
            //Datum
                if(buchung.datum.equals(null)){
                    myQuery = myQuery.concat("CURDATE(),");
                } else {
                    myQuery = myQuery.concat("'"+buchung.datum + "',");
                }
            //Zusatzinfo
                    myQuery = myQuery.concat("'"+buchung.zusatzinfo+"',");
            //Betrag
                myQuery = myQuery.concat("'"+buchung.betrag+"'");
                myQuery = myQuery.concat(");");

            System.out.println("myQuery: "+myQuery);

            Statement statementBuchungen = connection.createStatement();
            statementBuchungen.executeUpdate(myQuery);
    }
}
