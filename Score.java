
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mnc
 */
public class Score {
    public void insertUpdateDeleteStudent(char operation,Integer sid,Integer cid,Double score,String remarks)
    {
        Connection con=MyConnection.getConnection();
        PreparedStatement ps;
        if(operation == 'i')
        {
            try {
                ps=con.prepareStatement("INSERT INTO `score`(`student_id`, `course_id`, `student_score`, `description`) VALUES (?,?,?,?)");
               ps.setInt(1,sid);
               ps.setInt(2,cid);
               ps.setDouble(3,score);
               ps.setString(4,remarks);
                if(ps.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null,"Score Added !!");
                }
               } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Failed to add Score !!");
            }
        }
        
           if(operation == 'u')
        {
            try {
                ps=con.prepareStatement("UPDATE `score` SET `student_score`=?,`description`=? WHERE `student_id`=? AND`course_id`=?");
                ps.setDouble(1,score);
                ps.setString(2,remarks);
                ps.setInt(3,sid);
                ps.setInt(4,cid);
                if(ps.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null,"Score is Updated !!");
                }
               } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Sore not updated");
            }
        }
         if(operation == 'r')
        {
            try {
                ps=con.prepareStatement("DELETE FROM score WHERE student_id=? AND course_id=?");
                
                ps.setInt(1,sid);
                 ps.setInt(2,cid);
                if(ps.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null,"Score is Removed !!");
                }
               } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Score not Removed");
            }
        
        }
    }
      public void fillScoreJTable(JTable table)
          {
            Connection con = MyConnection.getConnection();
            PreparedStatement ps;
         try {
             ps=con.prepareStatement("SELECT * FROM score");
             ResultSet rs=ps.executeQuery();
             DefaultTableModel model=(DefaultTableModel)table.getModel();
             Object row[];
             while(rs.next())
             {
                 row =new Object[4];
                 row[0]=rs.getInt(1);
                 row[1]=rs.getInt(2);
                 row[2]=rs.getDouble(3);
                 row[3]=rs.getString(4);
                 model.addRow(row);
             }
         } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Table is not filled");
         }
          }
      public void showAllScores(JTable table)
          {
            Connection con = MyConnection.getConnection();
            PreparedStatement ps;
         try {
             ps=con.prepareStatement("SELECT * FROM score");
             ResultSet rs=ps.executeQuery();
             DefaultTableModel model=(DefaultTableModel)table.getModel();
             Object row[];
             while(rs.next())
             {
                 row =new Object[4];
                 row[0]=rs.getInt(1);
                 row[1]=rs.getInt(2);
                 row[2]=rs.getDouble(3);
                 row[3]=rs.getString(4);
                 model.addRow(row);
             }
         } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Table is not filled");
         }
          }
}
