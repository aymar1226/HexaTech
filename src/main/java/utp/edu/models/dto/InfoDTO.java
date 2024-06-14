package utp.edu.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoDTO {
    private String nombres;
    private String ap_paterno;
    private String ap_materno;
    private String infoAdicional;  // Ensure the field name matches your entity
    private String descripcion;
}
