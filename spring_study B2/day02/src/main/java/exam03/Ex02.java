package exam03;

import java.sql.*;

public class Ex02 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String user = "scott", password="tiger";

        try(Connection conn = DriverManager.getConnection(url,user,password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM DEPT2")){

            while(rs.next()){
                System.out.println(rs.getString("DNAME"));
                System.out.println(rs.getString("LOC"));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
