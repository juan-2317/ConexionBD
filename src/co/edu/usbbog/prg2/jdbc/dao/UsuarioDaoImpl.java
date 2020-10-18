package co.edu.usbbog.prg2.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import co.edu.usbbog.prg2.jdbc.conexion.BDConection;
import co.edu.usbbog.prg2.jdbc.entity.Usuario;
import co.edu.usbbog.prg2.jdbc.idao.IUsuarioDao;

public class UsuarioDaoImpl implements IUsuarioDao {

    @Override
    public boolean registrarUsuario(Usuario usuario) {
        BDConection conex = new BDConection();
        boolean registrar = false;

        try {
            String sql = "INSERT INTO usuario (username, password, fullname,"
                    + " emailaddress, phonenumber, datelastpassword, active, usertype)"
                    + " VALUES (?, ?, ?, ?, ?, Now(), ?, ?)";
            PreparedStatement stmt = conex.getConnection().prepareStatement(sql);
            stmt.setString(1, usuario.getUserName());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getFullName());
            stmt.setString(4, usuario.getEmailAddress());
            stmt.setString(5, usuario.getPhoneNumber());
            stmt.setString(6, usuario.getActive());
            stmt.setString(7, usuario.getTypeUser());

            stmt.executeUpdate();
            registrar = true;
            stmt.close();
            conex.desconectar();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrar;
    }

    @Override
    public List<Usuario> listaDeUsuarios() {
        BDConection conex = new BDConection();
        List<Usuario> miUsuario = new java.util.ArrayList<>();

        try {
            String sql = "SELECT * FROM usuario";
            Statement stmt = conex.getConnection().createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(res.getInt("id"));
                usuario.setUserName(res.getString("userName"));
                usuario.setEmailAddress(res.getString("emailAddress"));
                usuario.setFullName(res.getString("fullName"));
                usuario.setPassword(res.getString("password"));
                usuario.setActive(res.getString("active"));
                usuario.setTypeUser(res.getString("userType"));
                miUsuario.add(usuario);
            }

            stmt.close();
            conex.desconectar();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return miUsuario;
    }

    @Override
    public Usuario consultarUsuario(int id) {
        BDConection conex = new BDConection();
        Usuario miUsuario = new Usuario();

        try {
            String sql = "SELECT * FROM usuario WHERE id = ?";
            PreparedStatement stmt = conex.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                miUsuario.setId(res.getInt("id"));
                miUsuario.setUserName(res.getString("userName"));
                miUsuario.setEmailAddress(res.getString("emailAddress"));
                miUsuario.setFullName(res.getString("fullName"));
                miUsuario.setPassword(res.getString("password"));
                miUsuario.setActive(res.getString("active"));
                miUsuario.setTypeUser(res.getString("userType"));
            }

            stmt.close();
            conex.desconectar();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return miUsuario;
    }

    @Override
    public void actualizar(Usuario usuario) {
        BDConection conex = new BDConection();

        try {
            String sql = "UPDATE usuario SET password = ?, emailAddress = ?,"
                    + " dateLastPassword = Now() WHERE id = ?";
            PreparedStatement stmt = conex.getConnection().prepareStatement(sql);
            stmt.setString(1, usuario.getPassword());
            stmt.setString(2, usuario.getEmailAddress());
            stmt.setInt(3, usuario.getId());
            stmt.executeUpdate();

            stmt.close();
            conex.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void eliminar(int id) {
        BDConection conex = new BDConection();

        try {
            String sql = "UPDATE usuario SET active = ? WHERE id = ?";
            PreparedStatement stmt = conex.getConnection().prepareStatement(sql);
            stmt.setString(1, "I");
            stmt.setInt(2, id);
            stmt.executeUpdate();

            stmt.close();
            conex.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
