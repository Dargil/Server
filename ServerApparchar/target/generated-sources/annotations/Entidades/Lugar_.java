package Entidades;

import Entidades.Evento;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-25T17:06:38")
@StaticMetamodel(Lugar.class)
public class Lugar_ { 

    public static volatile SingularAttribute<Lugar, String> descripcion;
    public static volatile SingularAttribute<Lugar, Double> coordenadaY;
    public static volatile SingularAttribute<Lugar, Double> coordenadaX;
    public static volatile SingularAttribute<Lugar, String> direccion;
    public static volatile SingularAttribute<Lugar, String> nombre;
    public static volatile CollectionAttribute<Lugar, Evento> eventoCollection;

}