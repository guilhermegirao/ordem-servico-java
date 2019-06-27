/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.connection.ConnectionFactory;
import application.model.OrdemModel;
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
public class OrdemDAO {
    private final Connection connection;
    
    public OrdemDAO () throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }
    
    public List<OrdemModel> listarOrdens () {
        List<OrdemModel> list = new ArrayList<>();
        String sql = "SELECT * FROM `ordem`";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
           
            while (rs.next()) {
                OrdemModel o = new OrdemModel();
                
                o.setId(rs.getString("id"));
                o.setNome(rs.getString("nome"));
                o.setCliente(rs.getString("cliente"));
                o.setOrcamento(rs.getString("orcamento"));
                o.setCaracteristicas(rs.getString("caracteristicas"));
                o.setDefeitos(rs.getString("defeitos"));
                o.setStatus(rs.getString("status"));
                o.setData_cadastro(rs.getString("data_cadastro"));
                
                list.add(o);
            }
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return list;
    }
    
    public void editarOrdens (OrdemModel om) {
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("UPDATE `ordem` SET `nome` = ?, `cliente` = ?, `orcamento` = ?, `caracteristicas` = ?, `defeitos` = ?, `status` = ?, `data_cadastro` = ? WHERE `id` = ?");
            stmt.setString(1, om.getNome());
            stmt.setString(2, om.getCliente());
            stmt.setString(3, om.getOrcamento());
            stmt.setString(4, om.getCaracteristicas());
            stmt.setString(5, om.getDefeitos());
            stmt.setString(6, om.getStatus());
            stmt.setString(7, om.getData_cadastro());
            stmt.setString(8, om.getId());
           
            stmt.execute();
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }
    
    public void cadastrarOrdens (OrdemModel om) {
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("INSERT INTO `ordem` (`nome`, `cliente`, `orcamento`, `caracteristicas`, `defeitos`, `status`, `data_cadastro`) VALUES (?, ?, ?, ?, ?, ?, ?);");
            stmt.setString(1, om.getNome());
            stmt.setString(2, om.getCliente());
            stmt.setString(3, om.getOrcamento());
            stmt.setString(4, om.getCaracteristicas());
            stmt.setString(5, om.getDefeitos());
            stmt.setString(6, om.getStatus());
            stmt.setString(7, om.getData_cadastro());
           
            stmt.execute();
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }
    
    public int apagarOrdens (String id) {
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("DELETE FROM `ordem` WHERE `id` = ?");
            stmt.setString(1, id);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
        
        return 0;
    }
    
    public OrdemModel listarOrdem (String id) {
        String sql = "SELECT * FROM `ordem` WHERE `id` = "+id;
        OrdemModel o = new OrdemModel();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(); 
           
            while (rs.next()) {
                o.setId(rs.getString("id"));
                o.setNome(rs.getString("nome"));
                o.setCliente(rs.getString("cliente"));
                o.setOrcamento(rs.getString("orcamento"));
                o.setCaracteristicas(rs.getString("caracteristicas"));
                o.setDefeitos(rs.getString("defeitos"));
                o.setStatus(rs.getString("status"));
                o.setData_cadastro(rs.getString("data_cadastro"));
            }
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return o;
    }
}
