package Entidades;

import Entidades.EmpresaPK;
import Entidades.Evento;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-28T14:33:07")
@StaticMetamodel(Empresa.class)
public class Empresa_ { 

    public static volatile SingularAttribute<Empresa, String> descripcion;
    public static volatile SingularAttribute<Empresa, String> ubicacion;
    public static volatile SingularAttribute<Empresa, EmpresaPK> empresaPK;
    public static volatile SingularAttribute<Empresa, String> correo;
    public static volatile SingularAttribute<Empresa, String> contrasenia;
    public static volatile SingularAttribute<Empresa, String> telefono;
    public static volatile SingularAttribute<Empresa, String> nombre;
    public static volatile CollectionAttribute<Empresa, Evento> eventoCollection;

}