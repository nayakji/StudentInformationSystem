
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Course {
    
     public void insertUpdateDeleteStudent(char operation,Integer id,String label,Integer hours)
    {
        Connection con=MyConnection.getConnection();
        PreparedStatement ps;
        if(operation == 'i')
          {
            try {
                ps=con.prepareStatement("INSERT INTO course ( label, hours_number) VALUES (?,?);");
                ps.setString(1,label);
                ps.setInt(2,hours);
                if(ps.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null,"New Course Added !!");
                }
               } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Failed to add new Course !!");
            }
        }
        
           if(operation == 'u')
        {
            try {
                ps=con.prepareStatement("UPDATE course set label=?,hours_number=? where id=?");
                ps.setString(1,label);
                ps.setInt(2,hours);
                ps.setInt(3,id);
                if(ps.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null,"Course Data is Updated !!");
                }
               } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Course data is not updated");
            }
        }
         if(operation == 'r')
        {
            try {
                ps=con.prepareStatement("DELETE FROM `course` WHERE id=?");
                
                ps.setInt(1,id);
                if(ps.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null,"Course is Removed !!");
                }
               } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Course is not Removed");
            }
        
        }
     
    
}
     public void fillCourseJTable(JTable table)
          {
            Connection con = MyConnection.getConnection();
            PreparedStatement ps;
         try {
             ps=con.prepareStatement("SELECT * FROM course");
             ResultSet rs=ps.executeQuery();
             DefaultTableModel model=(DefaultTableModel)table.getModel();
             Object row[];
             while(rs.next())
             {
                 row =new Object[3];
                 row[0]=rs.getInt(1);
                 row[1]=rs.getString(2);
                 row[2]=rs.getInt(3);
                 model.addRow(row);
             }
         } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Table is not filled");
         }
          }
     
     public void fillCourseCombo(JComboBox combo)
          {
            Connection con = MyConnection.getConnection();
            PreparedStatement ps;
         try {
             ps=con.prepareStatement("SELECT * FROM course");
             ResultSet rs=ps.executeQuery();
             while(rs.next())
             {
                 combo.addItem(rs.getString(2));
             }
         } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Table is not filled");
         }
          }
     public int getCourseId(String label)
     {
         int id=0;
         Connection con = MyConnection.getConnection();
         PreparedStatement ps;
         try {
             ps=con.prepareStatement("SELECT * FROM course where label=?");
             ps.setString(1, label);
             ResultSet rs=ps.executeQuery();
              if(rs.next())
             {
                 id=rs.getInt(1);
             }
         } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Table is not filled");
         }
         return id;
     }
}