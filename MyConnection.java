import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.DriverManager;
public class MyConnection {
    public static Connection getConnection()
   {
     Connection con=null;
     try
     {
         Class.forName("com.mysql.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost/studentinfo","root","");
     }catch(ClassNotFoundException ee)
     {
          JOptionPane.showMessageDialog(null,"not loaded");
     }catch(SQLException kk)
     {
         JOptionPane.showMessageDialog(null,"not connected");
     }
     return con;
   } 
}
