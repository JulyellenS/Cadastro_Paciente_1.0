/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author JulyellenS
 */
public class ConexaoBD implements IDatabase {
    
    public static Connection con = null;
    
        public void carregarDriver(){
            
            try {
                
                Class.forName(DRIVER);
                
            }catch (ClassNotFoundException cnfe) {
                   System.out.println("Driver JDBC não encontrado : " + cnfe.getMessage());
            }
            
            
        }
        
    public Connection getConexao(){
        
        try {
            con = DriverManager.getConnection(URL, USUARIO, SENHA);
                return con;
                
       }catch  (SQLException sqle) {
                System.out.println("Erro na conexão ao Banco de Dados : " + sqle.getMessage());    
       }
        return null;
        }
    
    public void fecharConexao(){
        
        try{
            
            con.close();
            
        }catch (SQLException sqle) {
            System.out.println("Erro no fechamento da conexão : " + sqle.getMessage());
        }
    }
    
}
