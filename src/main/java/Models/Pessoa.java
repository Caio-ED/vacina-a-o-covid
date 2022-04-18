package Models;

import java.util.Date;

public class Pessoa implements Comparable<Pessoa>{
    
    private String rg;
    private String nome;
    private int idade;
    private String endereco;
    private boolean trabalhaComSaude;
    private Date data_vacinacao;

    public Pessoa() {}

    public Pessoa(String rg) {
        this.rg = rg;
    }

    public Pessoa(String rg,String nome, int idade, String endereco, boolean trabalhaComSaude) {
        this.rg = rg;
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.trabalhaComSaude = trabalhaComSaude;
    }
    public Pessoa(String rg,String nome, int idade, String endereco, boolean trabalhaComSaude,Date data_vacinacao) {
        this( rg, nome,  idade,  endereco, trabalhaComSaude);
        this.data_vacinacao = data_vacinacao; 
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public boolean isTrabalhaComSaude() {
        return trabalhaComSaude;
    }

    public void setTrabalhaComSaude(boolean trabalhaComSaude) {
        this.trabalhaComSaude = trabalhaComSaude;
    }

    public Date getData_vacinacao() {
        return data_vacinacao;
    }

    public void setData_vacinacao(Date data_vacinacao) {
        this.data_vacinacao = data_vacinacao;
    }
    
    

    @Override
    public int compareTo(Pessoa paciente) {
        return(paciente.getIdade() - this.idade);
    }

    
    
}
