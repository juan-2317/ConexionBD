package co.edu.usbbog.prg2.jdbc.idao;

import co.edu.usbbog.prg2.jdbc.entity.Trazabilidad;

public interface ITrazabilidadDao {
    
    public java.util.List<Trazabilidad> listaDeTrazabilidad();
    
    public boolean registrarAuditoria(Trazabilidad auditoria);
}
