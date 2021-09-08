/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import dao.PacienteDAO;
import java.util.Iterator;
import modelo.Paciente;

/**
 *
 * @author JulyellenS
 */
public class PacienteBS {
    
    PacienteDAO pacienteDAO;
    
    public PacienteBS(){
        
        pacienteDAO = new PacienteDAO();
    }
    
    //Método responsável por fazer a validação dos dados que serão cadastrados;
    //Verifica se todos os campos não nulos foram preenchidos.
    private void validarDados(Paciente paciente) throws Exception
    {
        
        if(paciente.getCpf()==0)
        {
            throw new Exception("O campo CPF é obrigatório.");
        }
         if(paciente.getNome() == null || paciente.getNome().equals(""))
        {
            throw new Exception("O campo Nome é obrigatório.");
        }
    }
    
    //Método responsável por adicionar o paciente após a validação dos dados;
    //Chama o método adionarPaciente do DAO.
    public void adicionarPaciente(Paciente paciente) throws Exception{

       validarDados(paciente);
       pacienteDAO.adicionarPaciente(paciente);
    }
    
    //Método responsável por chamar o método excluirPaciente do DAO. 
    public void excluirPaciente(int cpf) throws Exception{
        
       pacienteDAO.excluirPaciente(cpf);
    }
    
    //Método responsável por chamar o método consultarPaciente do DAO após a validação dos dados.
    //Consulta feita utilizando o parâmetro cpf.
    public Paciente consultarPaciente (int cpf) throws Exception {
        return pacienteDAO.consultarPaciente(cpf);
    }
    
    public Iterator consultarPaciente2 () throws Exception {
        return pacienteDAO.consultarPaciente2();
    }
    
    public void editarPaciente (Paciente paciente) throws Exception {
        validarDados(paciente);
        pacienteDAO.editarPaciente(paciente);
    }
    
}
