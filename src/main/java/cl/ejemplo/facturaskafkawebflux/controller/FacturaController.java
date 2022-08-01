package cl.ejemplo.facturaskafkawebflux.controller;

import cl.ejemplo.facturaskafkawebflux.entities.Factura;
import cl.ejemplo.facturaskafkawebflux.services.FacturaService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/facturas")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService){
        this.facturaService = facturaService;
    }

    @PostMapping("/")
    public Mono<Factura> createFactura(@RequestBody Factura factura){
        return facturaService.createFactura(factura);
    }

    @GetMapping("/{id}")
    public Mono<Factura> getFactura(@PathVariable Integer id){
        return facturaService.getFactura(id);
    }

    @GetMapping("/")
    public Flux<Factura> getFacturas(){
        return facturaService.getFacturas();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteFactura(@PathVariable Integer id){
        return facturaService.deleteFactura(id);
    }

    @PutMapping("/")
    public Mono<Factura> updateFactura(@RequestBody Factura factura){

        return facturaService.updateFactura(factura);
    }

}
