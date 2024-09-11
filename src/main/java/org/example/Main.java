package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("en marcha Ignacio");

        try {
            // Persistir una nueva entidad Factura
            entityManager.getTransaction().begin();


            Cliente clie1 = Cliente.builder()
                    .nombre("Carlitos")
                    .apellido("Java")
                    .dni(46756234)
                            .build();

            Factura fact1 = Factura.builder()
                            .fecha("27/10/2001")
                            .numero(122)
                            .total(1582)
                                    .build();

            Domicilio domc1 = Domicilio.builder()
                            .nombrecalle("Francisco de la reta")
                            .numero(1188)
                            .cliente(clie1)
                                            .build();
            clie1.setDomicilio(domc1);
            fact1.setCliente(clie1);

            clie1.addFactura(fact1); //Agrego una factura a ese cliente

            DetalleFactura det1 = DetalleFactura.builder()
                            .cantidad(2)
                            .subtotal(200)
                            .factura(fact1)
                                            .build();

            fact1.addDetalles(det1);

            Articulo art1 = Articulo.builder()
                            .cantidad(5)
                            .denominacion("Remera Blanca Talle M")
                            .precio(600)
                                    .build();

            art1.AddDetalle(det1);
            det1.setArticulo(art1);

            Categoria cat1 = Categoria.builder()
                            .denominacion("Ropa")
                                    .build();

            cat1.AddArticulo(art1);
            art1.AddCategoria(cat1);



            entityManager.persist(fact1);

            entityManager.getTransaction().commit();


            // Consultar y mostrar la entidad persistida
            Factura retrievedFactura = entityManager.find(Factura.class, fact1.getId());
            System.out.println("Retrieved Factura: " + retrievedFactura.getClass().getName());

        }catch (Exception e){

            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar la clase Factura");}

        // Cerrar el EntityManager y el EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
