package cl.ejemplo.facturaskafkawebflux.repository;

import cl.ejemplo.facturaskafkawebflux.entities.Factura;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface FacturaRepository extends ReactiveCrudRepository<Factura, Integer> {
}
