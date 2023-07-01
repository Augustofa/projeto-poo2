/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import POJOs.Consulta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.text.ParseException;

/**
 *
 * @author Augusto
 */
public class DAOConsulta {
    private Connection con;
    
    private PreparedStatement comando;
    
    private final String[] comandosBuscas = {"nome_medico = ?", "crm_medico = ?",
            "nome_paciente = ?", "cpf_paciente = ?"};
    
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
    
    public boolean insereConsulta(Consulta consulta){
        conectar();
        String sql = "INSERT INTO Consultas(nome_medico, crm_medico, " + 
                "nome_paciente, cpf_paciente, data, horario, receitas)" +
                " VALUES(?,?,?,?,?,?,?)";
        
        try{
            comando = con.prepareStatement(sql);
            comando.setString(1, consulta.getNomeMedico());
            comando.setString(2, consulta.getCrmMedico());
            comando.setString(3, consulta.getNomePaciente());
            comando.setString(4, consulta.getCpfPaciente());
            comando.setDate(5, geral.Utils.converteStringToDateSql(consulta.getData()));
            comando.setTime(6, geral.Utils.converteStringToTimeSql(consulta.getHorario()));
            comando.setString(7, consulta.getReceitas());
            
            comando.execute();
            return true;
        }catch(SQLException | ParseException e){
              JOptionPane.showMessageDialog(null, "Erro ao inserir registro."+e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE, null);
        }finally{
            fechar();
        }
        return false;
    }
    
    public  ArrayList<Consulta> selecionarTodosRegistros(){
        conectar();
        
        ResultSet rs;
        String sql = "SELECT * FROM Consultas";
        
        ArrayList<Consulta> listaConsultas = new ArrayList<>();
        try{
            comando = con.prepareStatement(sql);
            rs = comando.executeQuery();
            while(rs.next()){
                ArrayList<Object> dadosConsulta = new ArrayList<>();
                dadosConsulta.add(rs.getString("nome_medico"));
                dadosConsulta.add(rs.getString("crm_medico"));
                dadosConsulta.add(rs.getString("nome_paciente"));
                dadosConsulta.add(rs.getString("cpf_paciente"));
                dadosConsulta.add(geral.Utils.converteDateSqlToString(rs.getDate("data")));
                dadosConsulta.add(geral.Utils.converteTimeSqlToString(rs.getTime("horario")));
                dadosConsulta.add(rs.getString("receitas"));
                dadosConsulta.add(rs.getInt("id"));
                
                Consulta consulta = new Consulta(dadosConsulta);
                listaConsultas.add(consulta);
            }
            fechar();
            return listaConsultas;
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Erro ao buscar registro."+e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE, null);
            fechar();
            return null;
        }   
    }
    
    public boolean removeConsulta(int id){
        conectar();
        
        String sql = "DELETE FROM Consultas WHERE id = ?";
        try{
            comando = con.prepareStatement(sql);
            comando.setInt(1, id);
            
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
    
    public boolean alteraConsulta(Consulta consulta){
        conectar();
        String sql = "UPDATE Consultas SET nome_medico = ?, crm_medico = ?, "
                + "nome_paciente = ?, cpf_paciente = ?, data = ?, horario = ?, "
                + "receitas = ? WHERE id = ?";
        try{
            comando = con.prepareStatement(sql);
            comando.setString(1, consulta.getNomeMedico());
            comando.setString(2, consulta.getCrmMedico());
            comando.setString(3, consulta.getNomePaciente());
            comando.setString(4, consulta.getCpfPaciente());
            comando.setDate(5, geral.Utils.converteStringToDateSql(consulta.getData()));
            comando.setTime(6, geral.Utils.converteStringToTimeSql(consulta.getHorario()));
            comando.setString(7, consulta.getReceitas());
            comando.setInt(8, consulta.getId());
            comando.executeUpdate();
            return true;
        }catch(SQLException | ParseException e){
            JOptionPane.showMessageDialog(null, "Erro ao atualizar registro."+e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE, null);
        }finally{
             fechar();
         }
         return false;
    }
    
    public ArrayList<Consulta> buscaConsulta(String[] busca){
        conectar();
        
        ResultSet rs;
        String sql = "SELECT * FROM Consultas WHERE (";
        
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
        sql += ") ORDER BY data DESC";
        if(!varios){
            sql = sql.substring(0, sql.length() - 20);
        }
        
        ArrayList<Consulta> listaConsultas = new ArrayList<>();
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
                ArrayList<Object> dadosConsulta = new ArrayList<>();
                dadosConsulta.add(rs.getString("nome_medico"));
                dadosConsulta.add(rs.getString("crm_medico"));
                dadosConsulta.add(rs.getString("nome_paciente"));
                dadosConsulta.add(rs.getString("cpf_paciente"));
                dadosConsulta.add(geral.Utils.converteDateSqlToString(rs.getDate("data")));
                dadosConsulta.add(geral.Utils.converteTimeSqlToString(rs.getTime("horario")));
                dadosConsulta.add(rs.getString("receitas"));
                dadosConsulta.add(rs.getInt("id"));
                
                Consulta consulta = new Consulta(dadosConsulta);
                listaConsultas.add(consulta);
            }
            fechar();
            return listaConsultas;
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Erro ao buscar registro."+e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE, null);
            fechar();
            return null;
        }
    }
}
