/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guigi
 */
public class UsuarioDAO {
    private final Connection connection;
    
    public UsuarioDAO () throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }
    
    public boolean logar (String usuario, String senha) {
        String sql = "SELECT * FROM `usuario` WHERE `usuario` = ? AND `senha` = MD5(?)";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            
            ResultSet rs = stmt.executeQuery();
            boolean lock = rs.next();
            
            if (lock) 
                return true;
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return false;
    }
    
}
