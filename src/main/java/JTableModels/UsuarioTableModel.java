/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JTableModels;

import DAO.UsuarioDAO;
import Models.Usuario;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author caioe
 */
public class UsuarioTableModel extends AbstractTableModel {
    private List<Usuario>usuarios;
    private String[] colunas = {"Nome", "login", "tipo", "senha"};

    public UsuarioTableModel() throws Exception {
         UsuarioDAO usuarioDAO = new UsuarioDAO();
        this.usuarios = Arrays.asList(usuarioDAO.obtemUsuarios());
    }
    
    public UsuarioTableModel(Usuario usuario)throws Exception{
        UsuarioDAO pessoaDAO = new UsuarioDAO();
        this.usuarios = Arrays.asList(pessoaDAO.buscaUsuario(usuario));
    }

    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return this.usuarios.get(rowIndex).getNome();
            case 1:
                return this.usuarios.get(rowIndex).getLogin();
            case 2:
                return this.usuarios.get(rowIndex).getTipo();
            case 3:
                return this.usuarios.get(rowIndex).getSenha();
                
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return this.colunas[column];
    }
}
