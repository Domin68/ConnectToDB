import java.sql.*;
import java.util.Scanner;


public class PrintTable {
    /**
     * @param selectTableToPrint stands for table in SQL database
     */
    private String selectTableToPrint;
    /**
     * @param execute is sql code for select
     */
    private String execute;

    public String getSelectTableToPrint() {
        return selectTableToPrint;
    }

    /**
     * method setSelectTableToPrint() is for picking a table to print
     */
    public void setSelectTableToPrint() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Tables to choose:\n1.\"auta\" for Car List\n2.\"klienci\" for Client List\n3.\"wypozyczenia\" for Transactions");
        int choose=scan.nextInt();
        switch (choose){
            case 1:
                selectTableToPrint = "auta";
                break;
            case 2:
                selectTableToPrint = "klienci";
                break;
            case 3:
                selectTableToPrint = "wypozyczenia";
                break;
        }

    }

    public String getExecute() {
        return execute;
    }

    /**
     * setter in sql for setting select code
     */
        public void setExecute() {
        execute = "select * from " + getSelectTableToPrint();
    }

    public PrintTable() {
    }

    /**
     * method connectAndPrint() is for connecting with database and printing table
     */
    public void connectAndPrint(){
        try{
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/samochody","root","");
            Statement stmt=con.createStatement();
            setSelectTableToPrint();
            setExecute();
            ResultSet rs=stmt.executeQuery(getExecute());
            while(rs.next())
                switch (selectTableToPrint) {
                    case "auta":
                        System.out.println(rs.getInt(1) + ". " + rs.getString(2) + " "
                                + rs.getString(3) + ", " + rs.getInt(4) +
                                "km, " + rs.getInt(5) + ", " + rs.getString(6) + ", " + rs.getInt(7)
                        + " PLN");
                        break;
                    case "klienci":
                        System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  "
                                + rs.getString(3) + "  " + rs.getString(4) +
                                "  " + rs.getString(5) + "  " + rs.getString(6) + "  " + rs.getString(7));
                        break;
                    case "wypozyczenia":
                        System.out.println(rs.getInt(1) + "  " + rs.getInt(2) + "  "
                                + rs.getInt(3) + "  " + rs.getDate(4) +
                                "  " + rs.getDate(5) + "  " + rs.getInt(6));
                        break;
                    default:
                        System.out.println("No such Table in DataBase");
                        break;
                }

            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
}
