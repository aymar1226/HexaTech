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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConversacionGrupal getConversacionGrupal() {
        return conversacionGrupal;
    }

    public void setConversacionGrupal(ConversacionGrupal conversacionGrupal) {
        this.conversacionGrupal = conversacionGrupal;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
}
