/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.ConexaoDB;
import Models.Vacina;
import Models.Vacinacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author caioe
 */
public class VacinacaoDAO {
    
    public void cadastraVacinacao (Vacinacao vacinacao) throws Exception{
        String sql = "Insert into vacinacao(data_vacinacao,rg_paciente,codigo_vacina,login_atendente) values (?,?,?,?)";
        
        try (Connection conn = ConexaoDB.obtemConexao();
            PreparedStatement pst = conn.prepareStatement(sql)) {
            //convertendo util.Date para sql.Date pq o o sql.Date recebe um long;
            java.sql.Date dataSql = new java.sql.Date(vacinacao.getData().getTime());
            pst.setDate(1,dataSql);
            pst.setString(2, vacinacao.getPaciente());
            pst.setInt(3, vacinacao.getVacinaAplicada());
            pst.setString(4, vacinacao.getNomeAplicador());

 
            pst.execute();
        }
    }
    
    public Vacinacao[] todasVacinacoes() throws Exception {
        String sql = "select * from vacinacoes";
        
        try (Connection con = ConexaoDB.obtemConexao();
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ps.executeQuery();) {
            
            int totalDeVacinacoes = rs.last() ? rs.getRow() : 0;
            Vacinacao[] todasvacinacoes = new Vacinacao[totalDeVacinacoes];
            rs.beforeFirst();
            for (int i = 0; rs.next(); i++) {
                
                int codigo = rs.getInt("codigo_Vacinacao");
                int vacina = rs.getInt("codigo_Vacina");
                Date data = rs.getDate("data_Vacinacao");
                String rgPaciente = rs.getString("rg_paciente");
                String loginAtendente = rs.getString("login_Atendente");
                
                todasvacinacoes[i] = new Vacinacao(codigo,data, rgPaciente, vacina, loginAtendente);
            }
    
        return todasvacinacoes;
        }
    }
    
    public Vacinacao vacinacoesPorPessoa(Vacinacao vacinacao){
        String sql = "Select * from vacina where codigo = ?";

        try {
            //pega a conexao
            Connection con = ConexaoDB.obtemConexao();

            //prepara a sql
            PreparedStatement pst = con.prepareStatement(sql);

            //preenche os campos
            pst.setInt(1, vacinacao.getCodigo());

            //solicitar a execucao e aguardar o resultado
            ResultSet rs = pst.executeQuery();
            rs.next();
            
            int codigo = rs.getInt("codigo_Vacinacao");
            int vacina = rs.getInt("codigo_Vacina");
            Date data = rs.getDate("data_Vacinacao");
            String rgPaciente = rs.getString("rg_paciente");
            String loginAtendente = rs.getString("login_Atendente");
            Vacinacao vacinacaoAchada = new Vacinacao(codigo,data, rgPaciente, vacina, loginAtendente);
            return vacinacaoAchada;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public int[] mediaDeVacinacoes(Date dataInicio, Date dataFinal){
        
        
        
        return null;
    }
         
}
