/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import POJOs.Medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Augusto
 */
public class DAOMedico {
    private Connection con;
    
    private PreparedStatement comando;
    
    private final String[] comandosBuscas = {"nome = ?", "crm = ?"};
    
    private void conectar(){
        con = FabricaConexao.conexao();
    }
    
    private void fechar(){
        try{
            comando.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão"+e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }
    
    public boolean insereMedico(Medico medico){
        conectar();
        String sql = "INSERT INTO Medicos(nome, telefone, endereco, " + 
                "num_identificador, crm, email, convenios," +
                "especialidade, formacoes) VALUES(?,?,?,?,?,?,?,?,?)";
        
        try{
            comando = con.prepareStatement(sql);
            comando.setString(1, medico.getNome());
            comando.setString(2, medico.getTelefone());
            comando.setString(3, medico.getEndereco());
            comando.setString(4, medico.getNumIdentificador());
            comando.setString(5, medico.getCrm());
            comando.setString(6, medico.getEmail());
            comando.setString(7, medico.getConvenios());
            comando.setString(8, medico.getEspecialidade());
            comando.setString(9, medico.getFormacoes());
            
            comando.execute();
            return true;
        }catch(SQLException e){
              JOptionPane.showMessageDialog(null, "Erro ao inserir registro."+e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE, null);
        }finally{
            fechar();
        }
        return false;
    }
    
    public  ArrayList<Medico> selecionarTodosRegistros(){
        conectar();
        
        ResultSet rs;
        String sql = "SELECT * FROM Medicos";
        
        ArrayList<Medico> listaMedicos = new ArrayList<>();
        try{
            comando = con.prepareStatement(sql);
            rs = comando.executeQuery();
            while(rs.next()){
                ArrayList<Object> dadosMedico = new ArrayList<>();
                dadosMedico.add(rs.getString("nome"));
                dadosMedico.add(rs.getString("telefone"));
                dadosMedico.add(rs.getString("endereco"));
                dadosMedico.add(rs.getString("num_identificador"));
                dadosMedico.add(rs.getString("crm"));
                dadosMedico.add(rs.getString("email"));
                dadosMedico.add(rs.getString("convenios"));
                dadosMedico.add(rs.getString("especialidade"));
                dadosMedico.add(rs.getString("formacoes"));
                
                Medico medico = new Medico(dadosMedico);
                listaMedicos.add(medico);
            }
            fechar();
            return listaMedicos;
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Erro ao buscar registro."+e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE, null);
            fechar();
            return null;
        }   
    }
    
    public boolean removeMedico(String crm){
        conectar();
        
        String sql = "DELETE FROM Medicos WHERE crm=?";
        try{
            comando = con.prepareStatement(sql);
            comando.setString(1, crm);
            comando.executeUpdate();
            return true;
        }catch(SQLException e){
              JOptionPane.showMessageDialog(null, "Erro ao excluir registro."+e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE, null);
        }finally{
            fechar();
        }
        return false;
    }
    
    public boolean alteraMedico(Medico medico){
        conectar();
        String sql = "UPDATE Medicos SET nome = ?, telefone = ?, endereco = ?, "
                + "num_identificador = ?, email = ?, convenios = ?, "
                + "especialidade = ?, formacoes = ? WHERE crm=?";
        try{
           comando = con.prepareStatement(sql);
           comando.setString(1, medico.getNome());
           comando.setString(2, medico.getTelefone());
           comando.setString(3, medico.getEndereco());
           comando.setString(4, medico.getNumIdentificador());
           comando.setString(5, medico.getEmail());
           comando.setString(6, medico.getConvenios());
           comando.setString(7, medico.getEspecialidade());
           comando.setString(8, medico.getFormacoes());
           comando.setString(9, medico.getCrm());
           comando.executeUpdate();
           return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao atualizar registro."+e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE, null);
        }finally{
             fechar();
         }
         return false;
    }
    
    public ArrayList<Medico> buscaMedico(String[] busca){
        conectar();
        
        ResultSet rs;
        String sql = "SELECT * FROM Medicos WHERE (";
        
        boolean varios = false;
        for(int i = 0; i < comandosBuscas.length; i++){
            if(busca[i] != null){
                if(varios){
                    sql += " AND ";
                }
                sql += comandosBuscas[i];
                varios = true;
            }
        }
        sql += ")";
        if(!varios){
            sql = sql.substring(0, sql.length() - 9);
        }
        
        ArrayList<Medico> listaMedicos = new ArrayList<>();
        try{
            comando = con.prepareStatement(sql);
            int j = 1;
            for(int i = 0; i < comandosBuscas.length; i++){
                if(busca[i] != null){
                    comando.setString(j++, busca[i]);
                }
            }
            rs = comando.executeQuery();
            while(rs.next()){
                ArrayList<Object> dadosMedico = new ArrayList<>();
                dadosMedico.add(rs.getString("nome"));
                dadosMedico.add(rs.getString("telefone"));
                dadosMedico.add(rs.getString("endereco"));
                dadosMedico.add(rs.getString("num_identificador"));
                dadosMedico.add(rs.getString("crm"));
                dadosMedico.add(rs.getString("email"));
                dadosMedico.add(rs.getString("convenios"));
                dadosMedico.add(rs.getString("especialidade"));
                dadosMedico.add(rs.getString("formacoes"));
                
                Medico medico = new Medico(dadosMedico);
                listaMedicos.add(medico);
            }
            fechar();
            return listaMedicos;
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Erro ao buscar registro."+e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE, null);
            fechar();
            return null;
        }   
    }
}
