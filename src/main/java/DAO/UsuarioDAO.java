/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author caioe
 */
import Models.Usuario;
import Models.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//Data acess Object

public class UsuarioDAO {

    public Usuario[] obtemUsuarios() throws Exception {

        String sql = "Select * from usuario";

        try (Connection con = ConexaoDB.obtemConexao();
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ps.executeQuery();) {
            
            int totalDeUsuarios = rs.last() ? rs.getRow() : 0;
            Usuario[] usuarios = new Usuario[totalDeUsuarios];
            rs.beforeFirst();
            for (int i = 0; rs.next(); i++) {
                String login = rs.getString("login");
                String nome = rs.getString("nome");
                String tipo = rs.getString("tipo");
                String senha = rs.getString("senha");
                usuarios[i] = new Usuario(login, nome, senha, tipo);
            }
            return usuarios;
        }
    }

    public Usuario buscaUsuario(Usuario usuario) {

        //montar query sql
        String sql = "Select * from usuario where login = ? AND senha = ?";

        try {
            //pega a conexao
            Connection con = ConexaoDB.obtemConexao();

            //prepara a sql
            PreparedStatement pst = con.prepareStatement(sql);

            //preenche os campos
            pst.setString(1, usuario.getLogin());
            pst.setString(2, usuario.getSenha());

            //solicitar a execucao e aguardar o resultado
            ResultSet resultado = pst.executeQuery();
            resultado.next();
            String login = resultado.getString("login");
            String nome = resultado.getString("nome");
            String tipo = resultado.getString("tipo");
            String senha = resultado.getString("senha");
            Usuario u = new Usuario(login, nome, senha, tipo);
            return u;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void cadastraUsuario(Usuario usuario) throws Exception {
        String sql = "Insert Into usuario (senha, login, tipo, nome)values(?,?,?,?)";
        try (Connection conn = ConexaoDB.obtemConexao();
                PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, usuario.getSenha());
            pst.setString(2, usuario.getLogin());
            pst.setString(3, usuario.getTipo());
            pst.setString(4, usuario.getNome());
            pst.execute();
        }
    }

    public void atualizaUsuario(Usuario usuario) throws Exception {
        String sql = "update usuario set nome = ?, tipo = ? ,senha = ? where login = ?";

        try (Connection conn = ConexaoDB.obtemConexao();
                PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getTipo());
            pst.setString(3, usuario.getSenha());
            pst.setString(4, usuario.getLogin());

            pst.execute();
        }
    }

    public void apagaUsuario(Usuario usuario) throws Exception {
        String sql = "Delete from usuario where login = ?";

        try (Connection conn = ConexaoDB.obtemConexao();
                PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, usuario.getLogin());
            pst.execute();
        }
    }

}
