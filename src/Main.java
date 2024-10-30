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



        System.out.println("\nBuchungen");
        while (resultSetBuchungen.next()) {
            System.out.println(resultSetBuchungen.getString(1)+"\t"
                    + resultSetBuchungen.getString(2)+"\t"
                    + resultSetBuchungen.getString(3)+"\t"
                    + resultSetBuchungen.getString(4)+"\t"
                    + resultSetBuchungen.getString(5)+"\t"
                    + resultSetBuchungen.getString(6));
        }

        System.out.println("\nKategorien");
        while (resultSetKategorien.next()) {
            System.out.println(resultSetKategorien.getString(1)+"\t"
                    + resultSetKategorien.getString(2)+"\t"
                    + resultSetKategorien.getString(3)+"\t"
                    + resultSetKategorien.getString(4));
        }

        GUI myGUI = new GUI();


    }
}
