/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AkashApplications.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author akash
 */
public class StockResultSet {
        static String url = "jdbc:derby:IMS;create=true";
        static Connection con = null;
        static Statement stmt = null;
        static ResultSet rs = null;
        public static ResultSet fetchStock(String query)
        {
            
            try{
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                con = DriverManager.getConnection(url);
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                
//                int size = 0;
//                while (rs.next()) 
//                {
//                    size++;
//                }
//                System.out.println("size of client = "+size);
                
            } catch (ClassNotFoundException ex) {
                System.err.println(ex);
            } catch (SQLException ex) {
                System.err.println(ex);
            }
            
            return rs;
        }
}
