/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.ConexaoDB;
import Models.Vacina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author caioe
 */
public class VacinaDAO {
    
    public Vacina[] obtemVacinas() throws Exception {

        String sql = "Select * from vacina";

        try (Connection con = ConexaoDB.obtemConexao();
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ps.executeQuery();) {
            
            int totalDeUsuarios = rs.last() ? rs.getRow() : 0;
            Vacina[] vacinas = new Vacina[totalDeUsuarios];
            rs.beforeFirst();
            for (int i = 0; rs.next(); i++) {
                
                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome_Vacina");
                int quantidade = rs.getInt("quantidade_Vacina");
                vacinas[i] = new Vacina(codigo, nome, quantidade);
            }
            return vacinas;
        }
    }

    public Vacina buscaVacina(Vacina vacina) {

        //montar query sql
        String sql = "Select * from vacina where codigo = ?";

        try {
            //pega a conexao
            Connection con = ConexaoDB.obtemConexao();

            //prepara a sql
            PreparedStatement pst = con.prepareStatement(sql);

            //preenche os campos
            pst.setInt(1, vacina.getCodigo());

            //solicitar a execucao e aguardar o resultado
            ResultSet resultado = pst.executeQuery();
            resultado.next();
            
            int codigo = resultado.getInt("codigo");
            String nome = resultado.getString("nome_Vacina");
            int quantidade = resultado.getInt("quantidade_Vacina");
            Vacina vacinaAchada = new Vacina(codigo, nome, quantidade);
            return vacinaAchada;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void cadastraVacina(Vacina vacina) throws Exception {
        String sql = "Insert Into vacina (nome_Vacina, quantidade_Vacina)values(?,?)";
        try (Connection conn = ConexaoDB.obtemConexao();
                PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, vacina.getNome());
            pst.setInt(2, vacina.getQuantidade());
 
            pst.execute();
        }
    }

    public void atualizaVacina(Vacina vacina) throws Exception {
        String sql = "update vacina set nome_Vacina = ?, quantidade_Vacina = ? where codigo = ?";

        try (Connection conn = ConexaoDB.obtemConexao();
                PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, vacina.getNome());
            pst.setInt(2, vacina.getQuantidade());
            pst.setInt(3, vacina.getCodigo());
            pst.execute();
        }
    }

    public void apagaVacina(Vacina vacina) throws Exception {
        String sql = "Delete from vacina where codigo = ?";

        try (Connection conn = ConexaoDB.obtemConexao();
                PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, vacina.getCodigo());
            pst.execute();
        }
    }
    
    public void DebitaVacina(Vacina vacina) throws Exception {
        String sql = "update vacina set quantidade_vacina = quantidade_vacina - 1 where codigo = ?";

        try (Connection conn = ConexaoDB.obtemConexao();
                PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, vacina.getCodigo());
            pst.execute();
        }
    }
}
