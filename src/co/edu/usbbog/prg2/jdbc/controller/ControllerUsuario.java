package co.edu.usbbog.prg2.jdbc.controller;

import java.util.List;

import co.edu.usbbog.prg2.jdbc.dao.UsuarioDaoImpl;
import co.edu.usbbog.prg2.jdbc.entity.Usuario;
import co.edu.usbbog.prg2.jdbc.idao.IUsuarioDao;

public class ControllerUsuario {

    public ControllerUsuario() {
    }

    // llama al DAO para guardar un cliente
    public void registrarUsuario(Usuario usuario) {
        IUsuarioDao dao = new UsuarioDaoImpl();
        dao.registrarUsuario(usuario);
    }

    public Usuario consultarUsuario(int id) {
        IUsuarioDao dao = new UsuarioDaoImpl();
        Usuario mUsuario = dao.consultarUsuario(id);
        return mUsuario;
    }

    // llama al DAO para obtener todos los clientes y luego los muestra en la
    // vista
    public List<Usuario> verListaDeUsuarios() {
        IUsuarioDao dao = new UsuarioDaoImpl();
        List<Usuario> usuarios = dao.listaDeUsuarios();
        return usuarios;
    }

    public void actualizarUsuario(Usuario usuario) {
        IUsuarioDao dao = new UsuarioDaoImpl();
        dao.actualizar(usuario);
    }

    public void eliminarUsuario(int id) {
        IUsuarioDao dao = new UsuarioDaoImpl();
        dao.eliminar(id);
    }
}
