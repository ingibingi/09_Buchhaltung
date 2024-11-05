import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        //Create Connection to MariaDB
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/haushaltsbuchung","root","");

        //Kategorien aus DB auslesen
        Statement statementKategorien = connection.createStatement();
        ResultSet resultSetKategorien = statementKategorien.executeQuery("SELECT * FROM t_Kategorie");
        while (resultSetKategorien.next()) {
            new Kategorie(resultSetKategorien);
        }

        //Buchungen aus DB auslesen
        Statement statementBuchungen = connection.createStatement();
        ResultSet resultSetBuchungen = statementBuchungen.executeQuery("SELECT * FROM t_Buchung");
        while (resultSetBuchungen.next()){
            new Buchung(resultSetBuchungen);
        }

        System.out.println("\nBuchungen");
        System.out.println(Buchung.getUebersichtBuchungen());

        GUI myGUI = new GUI();

    }
}
