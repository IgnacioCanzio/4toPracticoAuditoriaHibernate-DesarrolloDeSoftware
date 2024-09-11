package org.example;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder


@Entity
@Table(name = "Cliente")
@Audited
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String apellido;
    private int dni;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Domicilio domicilio;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Factura> facturas = new HashSet<>();


    public void addFactura(Factura factura) {
        if (facturas == null) {
            facturas = new HashSet<>();
        }
        this.facturas.add(factura); // Añadir la factura al Set

    }
}
