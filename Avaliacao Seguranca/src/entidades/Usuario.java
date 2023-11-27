package entidades;

import utils.Hashing;

public class Usuario {

    private Integer id;
    private String login;
    private String senha;
    private String nome;

    private String cpf;

    public Usuario() {
    }

    public Usuario(String nome, String cpf, String login, String senha) {
        this.login = login;
        this.senha = Hashing.hashSha128(senha);
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}