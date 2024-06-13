package utp.edu.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearGrupoDTO {

    private String codigoUsuario; // Código del líder
    private Long idCurso;
    private String nombregrupo;
    private List<String> codigosMiembros;
    private String codigoDocente;

}
