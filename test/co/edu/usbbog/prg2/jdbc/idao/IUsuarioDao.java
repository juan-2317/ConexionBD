package co.edu.usbbog.prg2.jdbc.idao;

import co.edu.usbbog.prg2.jdbc.entity.Usuario;

public interface IUsuarioDao {

    // Método para agregar un nuevo usuario
    public boolean registrarUsuario(Usuario usuario);

    // Método para obtener la lista de usuarios
    public java.util.List<Usuario> listaDeUsuarios();

    // Método para consultar un usuario por el <id>
    public Usuario consultarUsuario(int id);

    public void actualizar(Usuario usuario); // método para actualizar los datos
    // de un registro
    public void eliminar(int id); // método para eliminar de manera lógica
    // un registro
}
