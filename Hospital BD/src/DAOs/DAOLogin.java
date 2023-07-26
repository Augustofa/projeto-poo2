package DAOs;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JOptionPane;

public class DAOLogin {
    private Connection con;
    
    private PreparedStatement comando;
    
    /* define essencialmente a forca do algoritmo 
    (quantas iteracoes ele fara para chegar na hash) */
    private final int iteracoes = 65536;
    
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
    
    public boolean insereLogin(String usuario, String senha){
        conectar();
        String sql = "INSERT INTO Logins(usuario, senha, salt)"
                + " VALUES(?,?,?)";
        
        String[] hashSenha = senhaToHash(senha, null);
        try{
            comando = con.prepareStatement(sql);
            
            comando.setString(1, usuario);
            comando.setString(2, hashSenha[0]);
            comando.setString(3, hashSenha[1]);
            
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
    
    public boolean verificaLogin(String usuario, String senha){
        conectar();
        String sql = "SELECT * FROM Logins WHERE usuario = ?";
        
        ResultSet rs;
        
        try {
            comando = con.prepareStatement(sql);
            
            comando.setString(1, usuario);
            
            rs = comando.executeQuery();
            
            if(rs.next()){
                String hashEncontrado = rs.getString("senha");
                String salt = rs.getString("salt");
                
                String hashTeste = senhaToHash(senha, fromHex(salt))[0];

                return (hashEncontrado.equals(hashTeste));
            }else{
                return false;
            }
        } catch (SQLException | NoSuchAlgorithmException ex) {
            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /* Retorna um array com a senha e o salt usado, respectivamente
    o parametro salt deve ser passado como null caso possa ser aleatorio */
    private String[] senhaToHash(String senha, byte[] salt){
        try {
            if(salt == null){
                SecureRandom random = new SecureRandom();
                salt = new byte[16];
                random.nextBytes(salt);            
            }
            // Gera um salt (bytes "lixo" pra ser concatenado na senha)
            
            // Converte a (senha + salt) em uma chave criptografica pra ser lida pelo hash
            KeySpec chaveSpec = new PBEKeySpec(senha.toCharArray(), salt, iteracoes, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            
            byte[] hash = factory.generateSecret(chaveSpec).getEncoded();
            
            String[] conjunto = {toHex(hash), toHex(salt)};
            return conjunto;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private static String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);

        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }
    
    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i < bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}