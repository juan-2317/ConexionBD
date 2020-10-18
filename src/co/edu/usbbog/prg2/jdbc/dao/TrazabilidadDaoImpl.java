package co.edu.usbbog.prg2.jdbc.dao;

import co.edu.usbbog.prg2.jdbc.conexion.BDConection;
import co.edu.usbbog.prg2.jdbc.entity.Trazabilidad;
import co.edu.usbbog.prg2.jdbc.idao.ITrazabilidadDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TrazabilidadDaoImpl implements ITrazabilidadDao {

    @Override
    public List<Trazabilidad> listaDeTrazabilidad() {
        BDConection conex = new BDConection();
        List<Trazabilidad> miUsuario = new java.util.ArrayList<>();

        try {
            String sql = "SELECT * FROM traceability";
            Statement stmt = conex.getConnection().createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                Trazabilidad trazabilidad = new Trazabilidad();
                trazabilidad.setId(res.getInt("id"));
                trazabilidad.setUserId(res.getInt("userId"));
                trazabilidad.setOperationCrud(res.getString("operationCrud").charAt(0));
                trazabilidad.setTableName(res.getString("tableName"));
                trazabilidad.setTableId(res.getInt("tableId"));
                trazabilidad.setCreateDate(res.getDate("createDate"));
                trazabilidad.setAddressIP(res.getString("addressIP"));
                miUsuario.add(trazabilidad);
            }

            stmt.close();
            conex.desconectar();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return miUsuario;
    }

    @Override
    public boolean registrarAuditoria(Trazabilidad auditoria) {
        BDConection conex = new BDConection();
        boolean registrar = false;

        try {
            String sql = "INSERT INTO traceability (userId, operationCrud, tableName,"
                    + " tableId, createDate, addressIP) VALUES (?, ?, ?, ?, Now(), ?)";
            PreparedStatement stmt = conex.getConnection().prepareStatement(sql);
            stmt.setInt(1, auditoria.getUserId());
            stmt.setString(2, Character.toString(auditoria.getOperationCrud()));
            stmt.setString(3, auditoria.getTableName());
            stmt.setInt(4, auditoria.getTableId());
            stmt.setString(5, auditoria.getAddressIP());
            stmt.executeUpdate();

            registrar = true;
            stmt.close();
            conex.desconectar();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrar;
    }

}
