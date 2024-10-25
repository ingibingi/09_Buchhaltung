import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/haushaltsbuchung","root","");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM t_Buchung");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1)+"\t"
                                +resultSet.getString(4)+"\t"
                                +resultSet.getString(6)+"\t"
                                +resultSet.getString(5));
        }
    }
}
