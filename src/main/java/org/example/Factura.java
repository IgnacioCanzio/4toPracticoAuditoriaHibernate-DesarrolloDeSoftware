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
@Table(name = "Factura")

@Audited
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fecha;
    private int numero;
    private int total;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetalleFactura> detalleFacturas = new HashSet<>();

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "cliente_id") //Clave foránea a Cliente
    private Cliente cliente;

    public void addDetalles(DetalleFactura detalleFactura){
        if (detalleFacturas == null) {
            detalleFacturas = new HashSet<>();
        }
        this.detalleFacturas.add(detalleFactura);
    }
}
