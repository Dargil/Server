package Entidades;

import Entidades.Calificacion;
import Entidades.Categoria;
import Entidades.Empresa;
import Entidades.EventoPK;
import Entidades.Lugar;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-27T10:54:04")
@StaticMetamodel(Evento.class)
public class Evento_ { 

    public static volatile SingularAttribute<Evento, String> descripcion;
    public static volatile SingularAttribute<Evento, EventoPK> eventoPK;
    public static volatile SingularAttribute<Evento, byte[]> foto;
    public static volatile CollectionAttribute<Evento, Empresa> empresaCollection;
    public static volatile CollectionAttribute<Evento, Categoria> categoriaCollection;
    public static volatile SingularAttribute<Evento, Lugar> direccion;
    public static volatile SingularAttribute<Evento, String> nombre;
    public static volatile CollectionAttribute<Evento, Calificacion> calificacionCollection;

}