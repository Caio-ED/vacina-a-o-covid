package Models;
import java.util.Date;


public class Vacinacao {
    
    int codigo;
    private Date data;
    private String Paciente;
    private int vacinaAplicada;
    private String nomeAplicador;

    public Vacinacao() {
    }
    public Vacinacao(Date data, String Paciente, int vacinaAplicada, String nomeAplicador) {
        this.data = data;
        this.Paciente = Paciente;
        this.vacinaAplicada = vacinaAplicada;
        this.nomeAplicador = nomeAplicador;
    }
     public Vacinacao(int codigo, Date data, String Paciente, int vacinaAplicada, String nomeAplicador) {
         this(data,Paciente,vacinaAplicada,nomeAplicador);
         this.codigo = codigo;
     }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
     
    

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getPaciente() {
        return Paciente;
    }

    public void setPaciente(String Paciente) {
        this.Paciente = Paciente;
    }

    public int getVacinaAplicada() {
        return vacinaAplicada;
    }

    public void setVacinaAplicada(int vacinaAplicada) {
        this.vacinaAplicada = vacinaAplicada;
    }

    public String getNomeAplicador() {
        return nomeAplicador;
    }

    public void setNomeAplicador(String nomeAplicador) {
        this.nomeAplicador = nomeAplicador;
    }

}
