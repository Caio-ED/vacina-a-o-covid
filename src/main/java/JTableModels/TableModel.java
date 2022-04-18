/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JTableModels;

import Models.Pessoa;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author caioe
 */
public class TableModel extends AbstractTableModel {
    private List<Pessoa> pessoas = new ArrayList<>();
    private String[] colunas = {"Nome", "RG", "Idade", "Endereço", "Trabalha na Saude"};
    
    public TableModel(List<Pessoa> fila)  {
        this.pessoas = fila;
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
