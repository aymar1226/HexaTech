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
    private String info_adicional;
    private String descripcion;
}
