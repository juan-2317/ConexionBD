package co.edu.usbbog.prg2.jdbc.vista;

import java.util.List;

import javax.swing.JOptionPane;

import co.edu.usbbog.prg2.jdbc.entity.Usuario;
import co.edu.usbbog.prg2.jdbc.util.Util;
import co.edu.usbbog.prg2.jdbc.controller.ControllerUsuario;
import co.edu.usbbog.prg2.jdbc.controller.ControllerTrazabilidad;
import co.edu.usbbog.prg2.jdbc.entity.Trazabilidad;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class EjecAplicacion {
    // UsuarioDaoImpl miUsuarioDAOImpl;

    public static void main(String[] args) {

        EjecAplicacion miPrincipal = new EjecAplicacion();
        miPrincipal.verMenu();
    }

    private void verMenu() {

        String textoMenu = "Menú Principal\n\n";
        textoMenu += "Ingrese alguna de las opciones del Menú\n";
        textoMenu += "1. Registrar Usuario\n";
        textoMenu += "2. Consultar un Registro de Usuario\n";
        textoMenu += "3. Ver Lista De Usuarios\n";
        textoMenu += "4. Actualizar un registro\n";
        textoMenu += "5. Eliminar registro\n";
        textoMenu += "6. Ver trazabilidad de los Usuarios\n";
        textoMenu += "7. Salir.\n\n";

        try {
            int seleccion = Integer.parseInt(JOptionPane.showInputDialog(textoMenu));
            defineSeleccion(seleccion);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error en el ingreso de Datos, "
                    + "solo se permiten valores númericos",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            verMenu();
        }
    }

    private void defineSeleccion(int seleccion) {
        String doc;

        switch (seleccion) {
            case 1:
                registrarUsuario();
                verMenu();
                break;
            case 2:
                doc = JOptionPane.showInputDialog("Ingrese el ID del Usuario");
                buscarUsuario(Integer.parseInt(doc));
                verMenu();
                break;
            case 3:
                obtenerRegistros();
                verMenu();
                break;
            case 4:
                doc = JOptionPane.showInputDialog("Ingrese el ID del Usuario");
                actualizarRegistro(Integer.parseInt(doc));
                verMenu();
                break;
            case 5:
                doc = JOptionPane.showInputDialog("Ingrese el ID del Usuario");
                eliminarRegistro(Integer.parseInt(doc));
                verMenu();
                break;
            case 6:
                verTrazabilidad();
                verMenu();
                break;
            case 7:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Ingrese un numero valido", "ADVERTENCIA",
                        JOptionPane.WARNING_MESSAGE);
                verMenu();
                break;
        }
    }

    private void buscarUsuario(int id) {

        ControllerUsuario cu = new ControllerUsuario();
        Usuario usu = cu.consultarUsuario(id);

        if (usu.getId() != 0) {
            String resultado = "********************* DATOS DEL USUARIO ********************\n";
            resultado += "Id: " + usu.getId() + "\n";
            resultado += "Nombre Usuario: " + usu.getFullName() + "\n";
            resultado += "Login Usuario: " + usu.getUserName() + "\n";
            resultado += "Clave Usuario: " + usu.getPassword() + "\n";
            resultado += "Estado del Registro: " + usu.getActive() + "\n";
            JOptionPane.showMessageDialog(null, resultado, "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "La identificacion que ha ingresado"
                    + " no corresponde a ningun usuario", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void obtenerRegistros() {

        ControllerUsuario cu = new ControllerUsuario();
        List<Usuario> listaUsuario = cu.verListaDeUsuarios();
        Usuario miUsuario;

        if (listaUsuario.size() > 0) {
            // se recorre la lista de Usuario asignandose cada posicion en un
            // objeto Usuario
            String resultado = "********************* DATOS DE LOS USUARIOS ********************\n";
            for (int i = 0; i < listaUsuario.size(); i++) {
                miUsuario = listaUsuario.get(i);
                resultado += "Id: " + miUsuario.getId() + "\n";
                resultado += "Nombre Usuario: " + miUsuario.getFullName() + "\n";
                resultado += "Login Usuario: " + miUsuario.getUserName() + "\n";
                resultado += "Clave Usuario: ********\n";
                resultado += "Estado del Registro: " + miUsuario.getActive() + "\n";

            }
            JOptionPane.showMessageDialog(null, resultado, "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Actualmente no "
                    + "existen registros de personas", "INFORMACIÓN",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void registrarUsuario() {

        ControllerUsuario cu = new ControllerUsuario();
        Usuario miUsuario = new Usuario();

        String mensajeIngreso = "Ingrese\n\n";
        String datosSolicitados[] = {"UserName : ", "Password : ", "E-mail: ", "Nombres y Apellidos: ",
            "Celular: ", "Tipo Usuario: "};
        String datosUsuario[] = new String[6];
        for (int i = 0; i < datosSolicitados.length; i++) {
            // solicita el ingreso del dato y se almacena en el arreglo de
            // datosUsuario
            datosUsuario[i] = JOptionPane.showInputDialog(null, mensajeIngreso + datosSolicitados[i],
                    "Datos Usuario", JOptionPane.INFORMATION_MESSAGE);
            // System.out.println(datosSolicitados[i] + datosUsuario[i]);
        }
        miUsuario.setId(0);
        miUsuario.setUserName(datosUsuario[0]);
        miUsuario.setPassword(Util.getStringMessageDigest(datosUsuario[1], Util.MD5));
        miUsuario.setFullName(datosUsuario[3]);
        miUsuario.setEmailAddress(datosUsuario[2]);
        miUsuario.setPhoneNumber(datosUsuario[4]);
        miUsuario.setTypeUser(datosUsuario[5]);
        miUsuario.setActive("A");
        cu.registrarUsuario(miUsuario);

        // Auditoria al insertar un nuevo registro
        registrarCambio('C', 0);

    }

    private void actualizarRegistro(int id) {
        ControllerUsuario cu = new ControllerUsuario();
        Usuario miUsuario = cu.consultarUsuario(id);

        if (miUsuario.getId() != 0) {
            String mensajeIngreso = "Ingrese\n\n";
            String datosSolicitados[] = {"Password : ", "E-mail: "};
            String[] datosUsuario = new String[2];

            datosUsuario[0] = JOptionPane.showInputDialog(null, mensajeIngreso
                    + datosSolicitados[0], "Datos Usuario",
                    JOptionPane.INFORMATION_MESSAGE);
            datosUsuario[1] = JOptionPane.showInputDialog(null, mensajeIngreso
                    + datosSolicitados[1], "Datos Usuario",
                    JOptionPane.INFORMATION_MESSAGE);

            miUsuario.setId(id);
            miUsuario.setPassword(Util.getStringMessageDigest(datosUsuario[0], Util.MD5));
            miUsuario.setEmailAddress(datosUsuario[1]);
            cu.actualizarUsuario(miUsuario);

            // Auditoria al actualizar un registro
            registrarCambio('U', id);
        } else {
            JOptionPane.showMessageDialog(null, "La identificacion que ha ingresado"
                    + " no corresponde a ningun usuario", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void eliminarRegistro(int id) {
        ControllerUsuario cu = new ControllerUsuario();
        Usuario miUsuario = cu.consultarUsuario(id);

        if (miUsuario.getId() != 0) {
            if (!("I".equals(miUsuario.getActive()))) {
                cu.eliminarUsuario(id);
                // Auditoria al eliminar un registro
                registrarCambio('D', id);
            } else {
                JOptionPane.showMessageDialog(null, "El registro ya está inactivo",
                        "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "La identificacion que ha ingresado"
                    + " no corresponde a ningun usuario",
                    "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void verTrazabilidad() {
        ControllerTrazabilidad ct = new ControllerTrazabilidad();
        List<Trazabilidad> listaUsuario = ct.listaDeTrazabilidad();
        Trazabilidad trazabilidad;

        if (listaUsuario.size() > 0) {
            // se recorre la lista de Usuario asignandose cada posicion en un
            // objeto Usuario
            String resultado = "********************* AUDITORIA DE USO ********************\n";
            for (int i = 0; i < listaUsuario.size(); i++) {
                trazabilidad = listaUsuario.get(i);
                resultado += "Id: " + trazabilidad.getId() + "\n";
                resultado += "Código de usuario: " + trazabilidad.getUserId() + "\n";
                resultado += "Operación CRUD: " + trazabilidad.getOperationCrud() + "\n";
                resultado += "Nombre de la tabla: " + trazabilidad.getTableName() + "\n";
                resultado += "Código del usuario: " + trazabilidad.getTableId() + "\n";
                resultado += "Fecha de creación: " + trazabilidad.getCreateDate() + "\n";
                resultado += "Dirección IP: " + trazabilidad.getAddressIP() + "\n";

            }
            JOptionPane.showMessageDialog(null, resultado, "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Actualmente no " + "existen registros de auditoria",
                    "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void registrarCambio(char crud, int idUsuario) {
        ControllerTrazabilidad ct = new ControllerTrazabilidad();
        Trazabilidad auditoria = new Trazabilidad();

        auditoria.setId(0);
        auditoria.setUserId(0);
        auditoria.setOperationCrud(crud);
        auditoria.setTableName("usuario");
        auditoria.setTableId(idUsuario);
        auditoria.setCreateDate(null);

        try {
            auditoria.setAddressIP(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(null, "El nombre del host no puede ser resuelto"
                    + " en la dirección IP", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
        }
        ct.registrarAuditoria(auditoria);

    }
}
