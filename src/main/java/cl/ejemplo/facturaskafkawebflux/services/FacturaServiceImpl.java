package cl.ejemplo.facturaskafkawebflux.services;

import cl.ejemplo.facturaskafkawebflux.entities.Factura;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FacturaServiceImpl {

    Flux<Factura> getFacturas();
    Mono<Factura> getFactura(Integer id);
    Mono<Factura> createFactura(Factura factura);
    Mono<Factura> updateFactura(Factura factura);
    Mono<Void> deleteFactura(Integer id);

}
