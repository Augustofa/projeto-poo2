package geral;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Augusto
 */
public class Utils {
    public static Date converteStringToDate(String data) throws ParseException 
    {
        DateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = formatado.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            throw(new ParseException("Data inválida", 0));
        }
        return date;
    }
    
    public static String converteDateToString (Date data)
    {
        DateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
        String auxData = formatado.format(data);
        return auxData;
    }
    
    public static java.sql.Date converteStringToDateSql(String strData) throws ParseException{   
        Date auxData = new Date();
        try {
            auxData = converteStringToDate(strData);
        } catch (ParseException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            throw(ex);
        }
        
        return new java.sql.Date(auxData.getTime());
    }
    
    public static String converteDateSqlToString(java.sql.Date dataSql){
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        
        Date data = new Date(dataSql.getTime());
        
        return formato.format(data);
    }
    
    public static java.sql.Time converteStringToTimeSql(String strData){
        strData = strData.concat(":00");
        LocalTime time = LocalTime.parse(strData);
        
        java.sql.Time sqlTime = java.sql.Time.valueOf(time);
        
        return sqlTime;
    }
    
    public static String converteTimeSqlToString(java.sql.Time sqlTime){
        DateTimeFormatter formato = DateTimeFormatter.ISO_TIME;
        
        String strTime = sqlTime.toLocalTime().format(formato);
        strTime = strTime.substring(0, 5);
        
        return strTime;
    }
    
    public static boolean validaCpf(String cpf) throws ParseException{
        if(!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")){
            throw new ParseException("CPF inválido", 0);
        }
        return true;
    }
    
    public static boolean checaVazio(ArrayList<Object> dados) throws ParseException{
        for(Object o : dados){
            if(o == null || o.toString().equals("")){
                throw new ParseException("Todos os campos devem ser preenchidos", 0);
            }
        }
        return true;
    }
}
