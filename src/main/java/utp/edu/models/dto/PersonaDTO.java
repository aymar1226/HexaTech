package utp.edu.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {

    String nombres;
    String ap_paterno;
    String ap_materno;
    String codigo;
    String rol;

}
