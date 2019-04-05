package Entidades;

import Entidades.CalificacionPK;
import Entidades.Cliente;
import Entidades.Evento;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-28T14:33:07")
@StaticMetamodel(Calificacion.class)
public class Calificacion_ { 

    public static volatile SingularAttribute<Calificacion, String> fecha;
    public static volatile SingularAttribute<Calificacion, Cliente> cliente;
    public static volatile SingularAttribute<Calificacion, byte[]> multimedia;
    public static volatile SingularAttribute<Calificacion, Evento> evento;
    public static volatile SingularAttribute<Calificacion, CalificacionPK> calificacionPK;
    public static volatile SingularAttribute<Calificacion, String> hora;
    public static volatile SingularAttribute<Calificacion, Double> porcentaje;
    public static volatile SingularAttribute<Calificacion, String> comentario;

}