package dao;

import DbConnection.ConnectionDB;
import entidades.Senha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SenhaDao {

    private final Connection con;

    public SenhaDao() {
        this.con = ConnectionDB.getConnection();
    }

    public void cadastrar(Senha senha) {
        String sql = "INSERT INTO senhas (chave_secreta) VALUES (?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, senha.getKey());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Senha getSenha() {
        String sql = "SELECT * FROM senhas LIMIT 1";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Senha senha = new Senha(rs.getString("chave_secreta"));
                    senha.setId(rs.getInt("id"));
                    senha.setKey(rs.getString("chave_secreta"));
                    return senha;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }


}