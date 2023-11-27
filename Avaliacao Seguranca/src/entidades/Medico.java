package entidades;

public class Medico extends Usuario {
    private String especialidade;

    Medico() {

    }

    public Medico(String especialidade) {
        super();
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}