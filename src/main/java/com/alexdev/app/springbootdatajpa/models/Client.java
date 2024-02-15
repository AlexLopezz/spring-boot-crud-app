package com.alexdev.app.springbootdatajpa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
    Esta clase representa a una tabla de nuestra base de datos, mediante anotaciones nosotros debemos indicarle
 los detalles de la misma... Por ejemplo: como queremos que se llame la tabla, atributos, restriciones, relaciones, etc!
 Esto se logra gracias a JPA(API) e Hibernate quien es el que implementa(por defecto) esta especificacion.
 */
@Entity // Indicamos a JPA que esta sera una tabla en la db.
@Table(name = "clients") // Anotamos a esta clase para que JPA lo marque y luego lo mapee a una tabla en nuesta DB
@Getter @Setter // Utilizamos lombok para que los campos de mi clase tengan sus respectivos metodos de acceso
public class Client implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id //Indicamos que sera la llave primaria de nuestra tabla a la hora de mapear en db
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Especificamos que este campo sera de tipo auto-incremental
    private Long id; //Importante que los tipos de los campos sean por referencia (Wrappers only en casos de primitivos)

    @NotEmpty // Indicamos que este campo siempre sera requerido a la hora de validar los datos.
    @Size(min = 3, max = 10) // Indicamos que este campo debe cumplir con un size o tamaño en especifico
    private String name;

    @NotEmpty // Indicamos que este campo siempre sera requerido a la hora de validar los datos.
    @Size(min = 3, max = 30) // Indicamos que este campo debe cumplir con un size o tamaño en especifico
    @Column(name = "last_name") // Indicamos el nombre de columna que tendra cuando se cree esta tabla en la db
    private String lastName;

    @NotEmpty // Indicamos que este campo siempre sera requerido a la hora de validar los datos.
    @Email // Indicamos que este campo debe tener un formato email. (ada@fasdd.com)
    private String email;

    private String profile;

    @NotNull // Indicamos que este campo debe ser requerido. Para objetos se utiliza esta anotacion, para String @NotEmpty
    @Column(name = "created_at") // Indicamos el nombre de columna que tendra cuando se cree esta tabla en la db
    @Temporal(TemporalType.DATE) // Especificamos que este campo se mapeara a el tipo de dato DATE en la bd
    @DateTimeFormat(pattern = "dd/MM/yyyy") // Indicamos un patron que debe seguir este campo para el tipo fecha sea valido.
    private Date createdAt;

}
