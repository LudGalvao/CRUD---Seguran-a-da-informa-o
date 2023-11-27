// Classe CalcioDao
package dao;

import DbConnection.ConnectionDB;
import entidades.calcio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalcioDao {
    private Connection con;

    public CalcioDao() {
        this.con = ConnectionDB.getConnection();
    }

    public void adiciona(calcio calcio) {
        String sql = "insert into calcio (mg_per_liter) values (?)";
        try {
            try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, calcio.getMgPerLiter());
                stmt.executeUpdate();

                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        calcio.setId(generatedId);
                    } else {
                        throw new RuntimeException("Falha ao obter o ID gerado pelo banco de dados.");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void remove(Integer id) {
        String sql = "delete from calcio where id = ?";
        try {
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.execute();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(calcio calcio, Integer id) {
        String sql = "update calcio set mg_per_liter = ? where id = ?";
        try {
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, calcio.getMgPerLiter());
                stmt.setInt(2, id);
                stmt.execute();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<calcio> lista() {
        String sql = "select * from Calcio";
        try {
            try (PreparedStatement stmt = con.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                List<calcio> calcios = new ArrayList<>();
                while (rs.next()) {
                    calcio calcio = new calcio();
                    calcio.setId(rs.getInt("id"));
                    calcio.setMgPerLiter(rs.getString("mg_per_liter"));
                    calcios.add(calcio);
                }
                return calcios;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    public calcio buscaPorId(Integer id) {
        String sql = "select * from calcio where id = ?";
        try {
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    calcio calcio = new calcio();
                    while (rs.next()) {
                        calcio.setId(rs.getInt("id"));
                        calcio.setMgPerLiter(rs.getString("mg_per_liter"));
                    }
                    return calcio;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    private void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
