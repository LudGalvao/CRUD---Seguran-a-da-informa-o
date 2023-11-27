package dao;

import DbConnection.ConnectionDB;
import entidades.ValoresReferencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ValoresReferenciaDao {

    private final Connection con;

    public ValoresReferenciaDao() throws Exception {
        this.con = ConnectionDB.getConnection();
    }

    public List<ValoresReferencia> listar() throws Exception {
        String sql = "SELECT * FROM valorespadroes";
        PreparedStatement stmt = this.con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<ValoresReferencia> valores = new ArrayList<>();
        while (rs.next()) {
            ValoresReferencia valor = new ValoresReferencia();
            valor.setId(rs.getInt("id"));
            valor.setDescricao(rs.getString("descricao"));
            valor.setLimiteInferior(rs.getInt("limite_inferior"));
            valor.setLimiteSuperior(rs.getInt("limite_superior"));
            valor.setUnidade(rs.getString("unidade"));
            valores.add(valor);
        }
        stmt.close();
        rs.close();
        con.close();
        return valores;
    }
}