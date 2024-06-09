package utp.edu.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "mensaje_conversacion_grupal")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeConversacionGrupal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje_conversacion_grupal", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_conversacion_grupal")
    @JsonBackReference
    private ConversacionGrupal conversacionGrupal;

    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @Column(name = "mensaje")
    private String mensaje;

    @Column(name = "f_envio")
    private Date fechaEnvio;

    public MensajeConversacionGrupal(ConversacionGrupal conversacionGrupal, Persona persona, String mensaje) {
        this.conversacionGrupal = conversacionGrupal;
        this.persona = persona;
        this.mensaje = mensaje;
        this.fechaEnvio = new Date();
    }
}
