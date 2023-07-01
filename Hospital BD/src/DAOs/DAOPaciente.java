/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import POJOs.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Augusto
 */
public class DAOPaciente {
    private Connection con;
    
    private PreparedStatement comando;
    
    private final String[] comandosBuscas = {"nome = ?", "cpf = ?"};
    
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
    
    public boolean inserePaciente(Paciente paciente){
        conectar();
        String sql = "INSERT INTO Pacientes(nome, data_nascimento, sexo, " + 
                "tipo_sanguineo, endereco, cpf, convenio," +
                "telefone, historico) VALUES(?,?,?,?,?,?,?,?,?)";
        
        try{
            comando = con.prepareStatement(sql);
            comando.setString(1, paciente.getNome());
            comando.setDate(2, geral.Utils.converteStringToDateSql(paciente.getDataNascimento()));
            comando.setString(3, Character.toString(paciente.getSexo()));
            comando.setString(4, paciente.getTipoSanguineo());
            comando.setString(5, paciente.getEndereco());
            comando.setString(6, paciente.getCpf());
            comando.setString(7, paciente.getConvenio());
            comando.setString(8, paciente.getTelefone());
            comando.setString(9, paciente.getHistorico());
            
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
    
    public  ArrayList<Paciente> selecionarTodosRegistros(){
        conectar();
        
        ResultSet rs;
        String sql = "SELECT * FROM Pacientes";
        
        ArrayList<Paciente> listaPacientes = new ArrayList<>();
        try{
            comando = con.prepareStatement(sql);
            rs = comando.executeQuery();
            while(rs.next()){
                ArrayList<Object> dadosPaciente = new ArrayList<>();
                dadosPaciente.add(rs.getString("nome"));
                dadosPaciente.add(geral.Utils.converteDateSqlToString(rs.getDate("data_nascimento")));
                dadosPaciente.add(rs.getString("sexo"));
                dadosPaciente.add(rs.getString("tipo_sanguineo"));
                dadosPaciente.add(rs.getString("endereco"));
                dadosPaciente.add(rs.getString("cpf"));
                dadosPaciente.add(rs.getString("convenio"));
                dadosPaciente.add(rs.getString("telefone"));
                dadosPaciente.add(rs.getString("historico"));
                
                Paciente paciente = new Paciente(dadosPaciente);
                listaPacientes.add(paciente);
            }
            fechar();
            return listaPacientes;
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Erro ao buscar registro."+e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE, null);
            fechar();
            return null;
        }   
    }
    
    public boolean removePaciente(String cpf){
        conectar();
        
        String sql = "DELETE FROM Pacientes WHERE cpf=?";
        try{
            comando = con.prepareStatement(sql);
            comando.setString(1, cpf);
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
    
    public boolean alteraPaciente(Paciente paciente){
        conectar();
        String sql = "UPDATE Pacientes SET nome = ?, data_nascimento = ?, sexo = ?, "
                + "tipo_sanguineo = ?, endereco = ?, convenio = ?, "
                + "telefone = ?, historico = ? WHERE cpf=?";
        try{
           comando = con.prepareStatement(sql);
           comando.setString(1, paciente.getNome());
           comando.setDate(2, geral.Utils.converteStringToDateSql(paciente.getDataNascimento()));
           comando.setString(3, Character.toString(paciente.getSexo()));
           comando.setString(4, paciente.getTipoSanguineo());
           comando.setString(5, paciente.getEndereco());
           comando.setString(6, paciente.getConvenio());
           comando.setString(7, paciente.getTelefone());
           comando.setString(8, paciente.getHistorico());
           comando.setString(9, paciente.getCpf());
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
    
    public ArrayList<Paciente> buscaPaciente(String[] busca){
        conectar();
        
        ResultSet rs;
        String sql = "SELECT * FROM Pacientes WHERE (";
        
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
        
        ArrayList<Paciente> listaPacientes = new ArrayList<>();
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
                ArrayList<Object> dadosPaciente = new ArrayList<>();
                dadosPaciente.add(rs.getString("nome"));
                dadosPaciente.add(geral.Utils.converteDateSqlToString(rs.getDate("data_nascimento")));
                dadosPaciente.add(rs.getString("sexo"));
                dadosPaciente.add(rs.getString("tipo_sanguineo"));
                dadosPaciente.add(rs.getString("endereco"));
                dadosPaciente.add(rs.getString("cpf"));
                dadosPaciente.add(rs.getString("convenio"));
                dadosPaciente.add(rs.getString("telefone"));
                dadosPaciente.add(rs.getString("historico"));
                
                Paciente paciente = new Paciente(dadosPaciente);
                listaPacientes.add(paciente);
            }
            fechar();
            return listaPacientes;
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Erro ao buscar registro."+e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE, null);
            fechar();
            return null;
        }
    }
}
