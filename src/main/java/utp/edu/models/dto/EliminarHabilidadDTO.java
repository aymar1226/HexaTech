package utp.edu.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EliminarHabilidadDTO {
    private String codigoPersona;
    private Long id_habilidad;
}
