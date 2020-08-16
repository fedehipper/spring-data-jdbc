Spring Data JDCB es un proyecto de Spring que sirve para conectarnos a una
base de datos y poder hacer consultas sobre nuestras entidades de dominio.
Spring Data JDBC se basa en el concepto de Aggregate el cual es un patrón de diseño
que representa un conjunto de entidades que se relacionan por medio de referencias de objetos.

Como mapeo mis entidades?

Es necesario identificar Aggregates en nuestro dominio. Como ejemplo supongamos
que tenemos las siguientes clases de dominio:

```java
public class Alumno {
    private String apellidoYNombre;
    private Set<Curso> cursos;
}

public class Curso {
    private String codigo;
    private Long materiaId;
}

public class Materia {
    private String codigo;
}
```

En este domnio, para identificar Aggregates nos fijamos en las 
relaciones que se establecen entre las clases. Comenzando por Alumno
podemos ver que tiene un Set<Curso> y que Materia no tiene 
una referencia en otra clase, ni esta posee una referencia a otra.

En este caso identificamos dos Aggregates, uno está conformado por las clases Alumno y Curso y el otro
solo por la clase Materia. 

Un Aggregate se identifica mediante un root, que es el objeto mediante el 
cual se puede navegar por las referencias hasta llegar a 
todas las clases dentro del Aggregate. En el caso anterior Alumno y Materia
son los root de sus Aggregates.

* Una entidad solo puede ser parte de un Aggregate.
* Todas las relaciones dentro de un Aggregate deben ser unidireccionales.
(esto quiere decir que a nivel datos van a ser relaciones de uno a muchos o de uno a uno)(editado)

Como mapeo mi clase de dominio?

Dado mi schema.sql: 

```sql
CREATE TABLE materia (
    id BIGSERIAL PRIMARY KEY,
    codigo VARCHAR UNIQUE
);

CREATE TABLE alumno(
    id BIGSERIAL PRIMARY KEY,
    apellido_y_nombre VARCHAR
);

CREATE TABLE curso (
    id BIGSERIAL PRIMARY KEY,
    codigo VARCHAR,
    alumno_id BIGINT,
    materia_id BIGINT,
    FOREIGN KEY (materia_id) REFERENCES materia(id),
    FOREIGN KEY (alumno_id) REFERENCES alumno(id)
);
```

Para realizar el mapeo se debe incluir un id a cada clase de dominio
junto con un @Id, las entidades quedarán de esta manera:

```java
public class Alumno {
    @Id
    private Long id;
    private String apellidoYNombre;
    @Column(value = "alumno_id")
    private Set<Curso> cursos;
}

public class Curso {
    @Id
    private Long id;
    private String codigo;
    private Long materiaId;
}

public class Materia {
    @Id
    private Long id;
    private String codigo;
}
```

Como podemos observar, hay un @Column con un valor "alumno_id" el cual
es la FK en la tabla curso que se puede ver en el schema.sql

Con estas anotaciones es suficiente para mapear estas entidades. La entidad
materia necesita relacionarse con curso, lo hacemos por medio de su id ya
que Materia no puede tener una referencia a Curso porque ya 
pertenece al Aggregate identificado por el root Alumno. Esta es la forma
de relacionar entidades que pertenecen a Aggregates distintos.

Repository?

Debemos tener un Repository por cada Aggregate, no por cada entidad.
Y como un Aggregate es representado por su root, habrá un repository 
representado por su root.

Nuestros dos repositorios quedarán de la siguiente manera:

```java
public interface AlumnoRepository extends CrudRepository<Alumno, Long> {}

public interface MateriaRepository extends CrudRepository<Materia, Long> {}
```

De esta forma, cuando hacemos un findById de Alumno, nos traemos el 
Aggregate completo (Alumno con todos sus cursos)

Warning: Cuando se quiere agregar un método al repository en Spring Data JDBC,
se debe agregar una anotación @Query que contenga la consulta. Esto debe ser SQL puro.
A menos que CrudRepostiry nos provea de métodos básicos como findById o findBy{UnaAtributo}
