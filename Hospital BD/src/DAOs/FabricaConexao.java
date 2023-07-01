/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Augusto
 */
public class FabricaConexao {
    public static Connection conexao(){
        Connection con = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String url = ("jdbc:mysql://127.0.0.1/hospital");
            
            con = DriverManager.getConnection(url, "root", "vertrigo");
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return con;
    }
}
