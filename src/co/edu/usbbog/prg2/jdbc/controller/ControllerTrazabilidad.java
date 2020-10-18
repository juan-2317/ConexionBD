package co.edu.usbbog.prg2.jdbc.controller;

import co.edu.usbbog.prg2.jdbc.dao.TrazabilidadDaoImpl;
import co.edu.usbbog.prg2.jdbc.entity.Trazabilidad;
import co.edu.usbbog.prg2.jdbc.idao.ITrazabilidadDao;
import java.util.List;

public class ControllerTrazabilidad {

    public List<Trazabilidad> listaDeTrazabilidad() {
        ITrazabilidadDao dao = new TrazabilidadDaoImpl();
        List<Trazabilidad> usuarios = dao.listaDeTrazabilidad();
        return usuarios;
    }
    
    public void registrarAuditoria(Trazabilidad auditoria) {
        ITrazabilidadDao dao = new TrazabilidadDaoImpl();
        dao.registrarAuditoria(auditoria);
    }
}
