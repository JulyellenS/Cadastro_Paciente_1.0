/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author JulyellenS
 */
public interface IDatabase {
    
        public final String DRIVER = "org.postgresql.Driver";
        public final String URL = "jdbc:postgresql://localhost:5432/bancopacientes";
        public final String USUARIO = "postgres";
        public final String SENHA = "senha";
    
}
