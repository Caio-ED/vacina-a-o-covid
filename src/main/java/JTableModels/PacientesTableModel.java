/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JTableModels;

import DAO.PessoaDAO;
import Models.Pessoa;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author caioe
 */
public class PacientesTableModel extends AbstractTableModel {

    private List<Pessoa> pessoas;
    private String[] colunas = {"Nome", "RG", "Idade", "Endereço", "Trabalha na Saude", "Data Vacinação"};

    public PacientesTableModel() throws Exception {
        PessoaDAO pessoaDAO = new PessoaDAO();
        this.pessoas = Arrays.asList(pessoaDAO.obtemPacientes());
    }

    public PacientesTableModel(Pessoa pessoa) throws Exception {
        PessoaDAO pessoaDAO = new PessoaDAO();
        this.pessoas = Arrays.asList(pessoaDAO.buscaPaciente(pessoa));
    }

    @Override
    public int getRowCount() {
        return pessoas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return this.pessoas.get(rowIndex).getNome();
            case 1:
                return this.pessoas.get(rowIndex).getRg();
            case 2:
                return this.pessoas.get(rowIndex).getIdade();
            case 3:
                return this.pessoas.get(rowIndex).getEndereco();

            case 4:
                return this.pessoas.get(rowIndex).isTrabalhaComSaude();
                
            case 5:
                return this.pessoas.get(rowIndex).getData_vacinacao();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return this.colunas[column];
    }

    @Override
    public Class getColumnClass(int columnIndex) {
            return getValueAt(0, columnIndex).getClass();
      
    }
}
