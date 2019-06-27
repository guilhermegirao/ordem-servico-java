/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.connection.ConnectionFactory;
import application.model.ClienteModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guigi
 */
public class ClienteDAO {
    private final Connection connection;
    
    public ClienteDAO () throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }
    
    public List<ClienteModel> listarClientes () {
        List<ClienteModel> list = new ArrayList<>();
        String sql = "SELECT * FROM `cliente`";
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
           
            while (rs.next()) {
                ClienteModel c = new ClienteModel();
                c.setCpf(rs.getString("cpf"));
                c.setData_cadastro(rs.getString("data_cadastro"));
                c.setEndereco(rs.getString("endereco"));
                c.setId(rs.getString("id"));
                c.setNome(rs.getString("nome"));
                c.setRg(rs.getString("rg"));
                c.setTelefone(rs.getString("telefone"));
                c.setTelefone_alternativo(rs.getString("telefone_alternativo"));
                
                list.add(c);
            }
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
        
        return list;
    }
    
    public void editarClientes (ClienteModel cm) {
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("UPDATE `cliente` SET `nome` = ?, `endereco` = ?, `cpf` = ?, `rg` = ?, `telefone` = ?, `telefone_alternativo` = ?, `data_cadastro` = ? WHERE `id` = ?");
            stmt.setString(1, cm.getNome());
            stmt.setString(2, cm.getEndereco());
            stmt.setString(3, cm.getCpf());
            stmt.setString(4, cm.getRg());
            stmt.setString(5, cm.getTelefone());
            stmt.setString(6, cm.getTelefone_alternativo());
            stmt.setString(7, cm.getData_cadastro());
            stmt.setString(8, cm.getId());
           
            stmt.execute();
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }
    
    public int apagarClientes (String id) {
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("DELETE FROM `cliente` WHERE `id` = ?");
            stmt.setString(1, id);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
        
        return 0;
    }
    
    public void cadastrarClientes (ClienteModel cm) {
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("INSERT INTO `cliente` (`nome`, `endereco`, `cpf`, `rg`, `telefone`, `telefone_alternativo`, `data_cadastro`) VALUES (?, ?, ?, ?, ?, ?, ?);");
            stmt.setString(1, cm.getNome());
            stmt.setString(2, cm.getEndereco());
            stmt.setString(3, cm.getCpf());
            stmt.setString(4, cm.getRg());
            stmt.setString(5, cm.getTelefone());
            stmt.setString(6, cm.getTelefone_alternativo());
            stmt.setString(7, cm.getData_cadastro());
           
            stmt.execute();
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }
    
    public ClienteModel listarCliente (String id) {
        String sql = "SELECT * FROM `cliente` WHERE `id` = "+id;
        PreparedStatement stmt = null;
        ClienteModel c = new ClienteModel();
        
        try {
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
           
            while (rs.next()) {
                c.setCpf(rs.getString("cpf"));
                c.setData_cadastro(rs.getString("data_cadastro"));
                c.setEndereco(rs.getString("endereco"));
                c.setId(rs.getString("id"));
                c.setNome(rs.getString("nome"));
                c.setRg(rs.getString("rg"));
                c.setTelefone(rs.getString("telefone"));
                c.setTelefone_alternativo(rs.getString("telefone_alternativo"));
            }
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
        
        return c;
    }
}
