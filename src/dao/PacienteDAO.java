/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import modelo.Paciente;

/**
 *
 * @author JulyellenS
 */
public class PacienteDAO {
    
   private ConexaoBD database = null;
   private PreparedStatement ps;
   private Connection con;
   private ResultSet rs;
   
   public PacienteDAO(){
   }
   
   //Método adicionar paciente
   
   public void adicionarPaciente(Paciente paciente) throws Exception {
       
       try {
           //Fazendo a conexão com o banco de dados
           database = new ConexaoBD();
           con = database.getConexao();
           //Comando de banco de dados
           ps = con.prepareStatement("insert into pacientes (cpf,nome,endereco,telefone) values (?,?,?,?)");
           //atribuindo valores aos parametros
           ps.setInt(1, paciente.getCpf());
           ps.setString(2, paciente.getNome());
           ps.setString(3, paciente.getEndereco());
           ps.setString(4, paciente.getTelefone());
           
           //executando o comando do banco de dados
           ps.executeUpdate();
           ps.close();
       }catch(Exception e){ throw new Exception("Erro. CPF já cadastrado!" + "\n" + e.getMessage());
           
       }
       finally
        {
            database.fecharConexao();
        }    
       
   }
   
   public void excluirPaciente(int cpf) throws Exception{
         
        try{
            
            String sql = null;
            
             database = new ConexaoBD();
             con = database.getConexao();
             
             sql = "delete from pacientes where cpf = ?";
             
             ps = con.prepareStatement(sql);
             ps.setInt(1, cpf);
             
             ps.executeUpdate();
             ps.close();
        }
        catch(Exception e)
        {    
            throw new Exception(e.getMessage());
        }
        finally
        {
            database.fecharConexao();
        }
   }
   
   public Paciente consultarPaciente(int cpf) throws Exception {
       
        Paciente paciente = new Paciente ();
        
        try {
            
            String sql = null;
            
            database = new ConexaoBD();
            con = database.getConexao();
            
            sql = "select cpf, nome, endereco, telefone from pacientes where cpf = ?";
            
            ps = con.prepareStatement(sql);
            ps.setInt(1, cpf);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                paciente.setCpf(rs.getInt("cpf"));
                paciente.setNome(rs.getString("nome"));
                paciente.setEndereco(rs.getString("endereco"));
                paciente.setTelefone(rs.getString("telefone"));

            }
            rs.close();
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        finally {
        database.fecharConexao();
    }
        return paciente;
    }
    
    public Iterator consultarPaciente2() throws Exception {
        
        ArrayList ListaPacientes = new ArrayList();
        
        try{
            
            String sql = null;
            
            database = new ConexaoBD();
            con = database.getConexao();
            
            sql = "select nome, cpf, endereco, telefone from pacientes";
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while (rs.next()){

                Paciente paciente = new Paciente ();
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getInt("cpf"));
                paciente.setEndereco(rs.getString("endereco"));
                paciente.setTelefone(rs.getString("telefone"));

 
                ListaPacientes.add(paciente);
            }
            rs.close();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        finally{
            database.fecharConexao();
        }
        return ListaPacientes.iterator();
    }
    
    public void editarPaciente (Paciente paciente) throws Exception {
        
        try {
            database = new ConexaoBD();
            con = database.getConexao();
            
            ps = con.prepareStatement("update pacientes set nome = ?, endereco = ?, telefone = ? where cpf = ?");
            
            ps.setString(1, paciente.getNome());
            ps.setString(2, paciente.getEndereco());
            ps.setString(3,paciente.getTelefone());
            ps.setInt(4, paciente.getCpf());

            
            ps.executeUpdate();
            ps.close();

        }catch (Exception e){
            throw new Exception (e.getMessage()); 
        }
        finally {
            database.fecharConexao();
    }   
  } 
}
