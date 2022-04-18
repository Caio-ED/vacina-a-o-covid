/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.ConexaoDB;
import Models.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author caioe
 */
public class PessoaDAO {
    
    public Pessoa[] obtemPacientes() throws Exception {

        String sql = "select nome, rg, idade, endereco, trabalhaComSaude, data_vacinacao from pessoa p left join vacinacao v on p.rg = v.rg_paciente order by idade desc";

        try (Connection con = ConexaoDB.obtemConexao();
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ps.executeQuery();) {
            
            int totalDePacientes = rs.last() ? rs.getRow() : 0;
            Pessoa[] pacientes = new Pessoa[totalDePacientes];
            rs.beforeFirst();
            for (int i = 0; rs.next(); i++) {
                try{
                String rg = rs.getString("rg");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                String endereco = rs.getString("endereco");
                boolean trabalhaComSaude = rs.getBoolean("trabalhaComSaude");
                Date data_vacinacao = new Date(rs.getDate("data_vacinacao").getTime());
                pacientes[i] = new Pessoa(rg, nome, idade, endereco, trabalhaComSaude, data_vacinacao);
                }catch(Exception e){
                    
                String rg = rs.getString("rg");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                String endereco = rs.getString("endereco");
                boolean trabalhaComSaude = rs.getBoolean("trabalhaComSaude");
                pacientes[i] = new Pessoa(rg, nome, idade, endereco, trabalhaComSaude);
                }
            }
            return pacientes;
        }
    }
    public Pessoa[] obtemFila() throws Exception {

        String sql = "Select * from pessoa p where not exists(select * from vacinacao v where p.rg = v.rg_paciente)";

        try (Connection con = ConexaoDB.obtemConexao();
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ps.executeQuery();) {
            
            int totalDePacientes = rs.last() ? rs.getRow() : 0;
            Pessoa[] pacientes = new Pessoa[totalDePacientes];
            rs.beforeFirst();
            for (int i = 0; rs.next(); i++) {
                
                String rg = rs.getString("rg");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                String endereco = rs.getString("endereco");
                boolean trabalhaComSaude = rs.getBoolean("trabalhaComSaude");
                pacientes[i] = new Pessoa(rg, nome, idade, endereco, trabalhaComSaude);
            }
            return pacientes;
        }
    }
    

    public Pessoa buscaPaciente(Pessoa pessoa) {
        //montar query sql
        String sql = "Select * from pessoa where rg = ?";

        try {
            //pega a conexao
            Connection con = ConexaoDB.obtemConexao();

            //prepara a sql
            PreparedStatement pst = con.prepareStatement(sql);

            //preenche os campos
            pst.setString(1, pessoa.getRg());

            //solicitar a execucao e aguardar o resultado
            ResultSet resultado = pst.executeQuery();
            resultado.next();
            String rg = resultado.getString("rg");
            String nome = resultado.getString("nome");
            int idade = resultado.getInt("idade");
            String endereco = resultado.getString("endereco");
            boolean trabalhaComSaude = resultado.getBoolean("trabalhaComSaude");

            Pessoa u = new Pessoa(rg, nome, idade, endereco, trabalhaComSaude);
            return u;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void cadastraPaciente(Pessoa pessoa) throws Exception {
        String sql = "Insert Into pessoa (rg, nome, idade, endereco, trabalhaComSaude)values(?,?,?,?,?)";
        try (Connection conn = ConexaoDB.obtemConexao();
                PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, pessoa.getRg());
            pst.setString(2, pessoa.getNome());
            pst.setInt(3, pessoa.getIdade());
            pst.setString(4, pessoa.getEndereco());
            pst.setBoolean(5, pessoa.isTrabalhaComSaude());

            pst.execute();
        }
    }

    public void atualizaPaciente(Pessoa pessoa) throws Exception {
        String sql = "update pessoa set nome = ?, idade = ?, endereco = ?, trabalhaComSaude = ? where rg = ? ";

        try (Connection conn = ConexaoDB.obtemConexao();
                PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, pessoa.getNome());
            pst.setInt(2, pessoa.getIdade());
            pst.setString(3, pessoa.getEndereco());
            pst.setBoolean(4, pessoa.isTrabalhaComSaude());
            pst.setString(5, pessoa.getRg());

            pst.execute();
        }
    }

    public void apagaPaciente(Pessoa pessoa) throws Exception {
        String sql = "Delete from pessoa where rg = ?";

        try (Connection conn = ConexaoDB.obtemConexao();
                PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, pessoa.getRg());
            pst.execute();
        }
    }
 
}
