package entidades;

public class Paciente extends Usuario {

    public Paciente() {

    }

    public Paciente(String nome, String cpf, String login, String senha) {
        super(nome, cpf, login, senha);
    }
}