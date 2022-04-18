package Models;


import java.sql.Connection;
import java.sql.DriverManager;


public class ConexaoDB {
    
    private static String host = "localhost";
    private static String door = "3306";
    private static String db = "filavacinacao";
    private static String user = "root";
    private static String senha = "190920";
    
    //Conecta com o banco
    //retorna conecao estabelecida com a base.
    public static Connection obtemConexao() {
        
        try{
            return DriverManager.getConnection("jdbc:mysql://"+host+":"+door+"/"+db,user,senha);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
