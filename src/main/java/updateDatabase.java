import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class updateDatabase {
    /**
     * lets the user to work with database with sql "UPDATE"
     */
    public static void sqlSetter(){
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Write sql code to update database");
            String sqlToUpdate= scan.nextLine();
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/samochody","root","");
            Statement stmt=con.createStatement();
            stmt.executeUpdate(sqlToUpdate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
