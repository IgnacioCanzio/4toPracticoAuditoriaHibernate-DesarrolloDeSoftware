package org.example;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "Categoria")
@Audited
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String denominacion;

    @ToString.Exclude
    @ManyToMany(mappedBy = "categorias") // Relación inversa definida por "categorias" en Articulo
    private Set<Articulo> articulos = new HashSet<>();

    public void AddArticulo(Articulo articulo) {
        if (articulos == null) {
            articulos = new HashSet<>();
        }
        this.articulos.add(articulo);
    }
}
