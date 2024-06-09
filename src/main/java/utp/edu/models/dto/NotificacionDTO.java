package utp.edu.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionDTO {
    String mensaje;
    Long id_grupo;
    //   Date fecha_creacion;


}
