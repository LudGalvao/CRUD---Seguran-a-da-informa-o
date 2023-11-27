package dao;

import DbConnection.ConnectionDB;
import entidades.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PacienteDao {

    private final Connection con;

    public PacienteDao() {
        this.con = ConnectionDB.getConnection();
    }

    public void adiciona(Paciente pacientes) {
        String sql = "insert into pacientes (nome, cpf, login, senha_hash) values (?,?,?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, pacientes.getNome());
            stmt.setString(2, pacientes.getCpf());
            stmt.setString(3, pacientes.getLogin());
            stmt.setString(4, pacientes.getSenha());
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Paciente> getPacientes() {
        String sql = "select * from pacientes";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            List<Paciente> pacientes1 = new ArrayList<>();
            ResultSet rset = stmt.executeQuery();
            Paciente pacientes = null;
            while (rset.next()) {
                pacientes = new Paciente();
                pacientes.setNome(rset.getString("nome"));
                pacientes.setCpf(rset.getString("cpf"));
                pacientes1.add(pacientes);
            }
            rset.close();
            stmt.close();
            con.close();
            return pacientes1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Paciente buscaPorLogin(String login) {
        String sql = "select * from pacientes where login = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            Paciente paciente = new Paciente();
            while (rs.next()) {
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setLogin(rs.getString("login"));
                paciente.setSenha(rs.getString("senha_hash"));
            }
            stmt.close();
            rs.close();
            con.close();
            return paciente;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}