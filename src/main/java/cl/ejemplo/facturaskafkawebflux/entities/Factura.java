package cl.ejemplo.facturaskafkawebflux.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("facturas")
public class Factura {

    @Id
    @Column("id")
    private Integer id;

    @Column("rut_empresa")
    private String rutEmpresa;

    @Column("numero_factura")
    private String numeroFactura;

    @Column("tipo")
    private String tipo;

    @Column("monto")
    private double montoTotal;

    @Column("fecha")
    private String fecha;
}
