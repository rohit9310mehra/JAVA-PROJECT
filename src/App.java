
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int value = 5;
        try {
            while (!(value == 0)) {
                // Load and register the Driver Class
                Class.forName("com.mysql.cj.jdbc.Driver");
                // System.out.println("*");

                // Get Connection
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_info", "root",
                        "Accurate31052005@#$!");
                System.out.println("+-------------------------------------------+");
                System.out.println("Press 1 for Insert value into Database");
                System.out.println("Press 2 for Read All Value from Database");
                System.out.println("Press 3 for Update value in Database");
                System.out.println("Press 4 for Read single Value from Database");
                System.out.println("Press 5 Delete value From Database");
                System.out.println("Press 0 for Exit");
                System.out.println("+--------------------------------------------+");
                System.out.println("Enter Value:- ");

                int valu = sc.nextInt();

                switch (valu) {
                    case 1:
                        try {

                            // Make prepare statement for Insert Record
                            PreparedStatement stmt = con.prepareStatement("insert into student values(?,?,?)");

                            Scanner sc1 = new Scanner(System.in);
                            System.out.println("Please Enter Name");
                            String name = sc1.nextLine();
                            System.out.println("Please Enter Course");
                            String course = sc1.nextLine();
                            System.out.println("Please Enter Id");
                            int id = sc1.nextInt();

                            stmt.setInt(1, id);// 1 specifies the first parameter in the query
                            stmt.setString(2, name);
                            stmt.setString(3, course);

                            int i = stmt.executeUpdate(); // This method are used to execute Query

                            if (i > 0) {
                                System.out.println("Data Saved Succesfully");
                            } else {
                                System.out.println("Query Problem!");
                            }

                        } catch (Exception e) {
                            System.out.println("DB error");
                        }
                        break;

                    case 2:
                        try {

                            Statement stmt1 = con.createStatement();

                            // Get All the Records
                            ResultSet rs = stmt1.executeQuery("select * from student");
                            System.out.println("ID\tNAME\tCOURSE");
                            System.out.println("-------------------------");
                            while (rs.next()) {
                                System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
                            }

                        } catch (Exception e) {
                            System.out.println("DB error");
                        }
                        break;
                    case 3:
                        try {
                            Scanner sc2 = new Scanner(System.in);
                            System.out.println("Please Enter Course For update");
                            String course1 = sc2.nextLine();
                            System.out.println("Please Enter Existing ID");
                            int id1 = sc2.nextInt();

                            String sql = ("update student set course=" + "'" + course1 + "'" + "where id=" + id1);
                            PreparedStatement p = con.prepareStatement(sql);
                            p.execute();
                            System.out.println("Data Updated");

                        } catch (Exception e) {
                            System.out.println("DB error");
                        }
                        break;
                    case 4:
                        try {
                            Scanner sc3 = new Scanner(System.in);
                            System.out.println("Please Enter Id of Which you Want Data:-");
                            int id2 = sc3.nextInt();

                            // Create Statement
                            Statement stmt2 = con.createStatement();

                            // Get All the Records
                            ResultSet rs1 = stmt2.executeQuery("select * from student where id=" + id2);
                            System.out.println("ID\tNAME\tCOURSE");
                            System.out.println("------------------------------");
                            while (rs1.next()) {
                                System.out.println(rs1.getInt(1) + "\t" + rs1.getString(2) + "\t" + rs1.getString(3));
                            }

                        } catch (Exception e) {
                            System.out.println("DB error");
                        }
                        break;
                    case 5:
                        try {
                            Scanner sc4 = new Scanner(System.in);
                            System.out.println("Please Enter ID");
                            int id3 = sc4.nextInt();

                            String sql1 = ("Delete from student " + "where id=" + id3);
                            PreparedStatement stmt3 = con.prepareStatement(sql1);
                            stmt3.execute();
                            System.out.println("Data deleted");

                        } catch (Exception e) {
                            System.out.println("DB error");
                        }
                        break;
                    case 0:
                        try {

                            System.out.println("Thanks for Using our product");
                            System.exit(0);
                        } catch (Exception e) {
                            System.out.println("DB error");
                        }
                        break;
                    default:
                        System.out.println("Please Enter Value From Given Choices");

                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        sc.close();

    }
}
