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
@Table(name = "Articulo")
@Audited
public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int cantidad;
    private String denominacion;
    private int precio;

    @ToString.Exclude
    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetalleFactura> detalleFacturas = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "articulo_categoria", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "articulo_id"), // Clave foránea a Articulo
            inverseJoinColumns = @JoinColumn(name = "categoria_id") // Clave foránea a Categoria
    )
    private Set<Categoria> categorias = new HashSet<>();

    public void AddDetalle(DetalleFactura detalleFactura) {
        if (detalleFacturas == null) {
            detalleFacturas = new HashSet<>();
        }
        this.detalleFacturas.add(detalleFactura);
    }

    public void AddCategoria(Categoria categoria){
        if (categorias == null) {
            categorias = new HashSet<>();
        }
        this.categorias.add(categoria);
    }
}
