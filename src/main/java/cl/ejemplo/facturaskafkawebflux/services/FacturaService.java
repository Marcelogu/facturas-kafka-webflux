package cl.ejemplo.facturaskafkawebflux.services;

import cl.ejemplo.facturaskafkawebflux.entities.Factura;
import cl.ejemplo.facturaskafkawebflux.repository.FacturaRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FacturaService implements FacturaServiceImpl{

    private final FacturaRepository facturaRepository;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public FacturaService(FacturaRepository facturaRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.facturaRepository = facturaRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Flux<Factura> getFacturas() {
        return facturaRepository.findAll();
    }

    @Override
    public Mono<Factura> getFactura(Integer id) {
        return facturaRepository.findById(id);
    }

    @Override
    public Mono<Factura> createFactura(Factura factura) {

        return facturaRepository.save(factura).map(f -> {
            kafkaTemplate.send("facturas", "{'rut': " + f.getRutEmpresa() + " , 'factura': " + f.getNumeroFactura() +"}");
            return f;
        });
    }

    @Override
    public Mono<Factura> updateFactura(Factura factura) {
        return facturaRepository.findById(factura.getId())
                .flatMap(f -> {
                    f.setRutEmpresa(factura.getRutEmpresa());
                    f.setNumeroFactura(factura.getNumeroFactura());
                    f.setTipo(factura.getTipo());
                    f.setMontoTotal(factura.getMontoTotal());
                    f.setFecha(factura.getFecha());

                    return facturaRepository.save(f);

                });
    }

    @Override
    public Mono<Void> deleteFactura(Integer id) {
        return facturaRepository.deleteById(id);
    }
}
