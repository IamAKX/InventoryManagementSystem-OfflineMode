/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AkashApplications.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author akash
 */
public class UpdateStock {
        static String url = "jdbc:derby:IMS;create=true";
        static Connection con = null;
        static Statement stmt = null;
        
        public static int update(String query)
        {
            int result = -999;
            System.err.println(query);  
            try{
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                con = DriverManager.getConnection(url);
                stmt = con.createStatement();
                result = stmt.executeUpdate(query);
                
                stmt.close();
                con.close();
            } catch (ClassNotFoundException ex) {
                System.err.println(ex);
            } catch (SQLException ex) {
                System.err.println(ex);
            }
            
            return result;
            
        }
}
