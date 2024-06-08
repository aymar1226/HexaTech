package utp.edu.models.entities;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "conversacion_grupal")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversacionGrupal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conversacion_grupal", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_grupo")
    private Grupo grupo;

    @Column(name = "f_inicio")
    private Date fechaInicio;

    @OneToMany(mappedBy = "conversacionGrupal", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MensajeConversacionGrupal> mensajes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public List<MensajeConversacionGrupal> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<MensajeConversacionGrupal> mensajes) {
        this.mensajes = mensajes;
    }

    public ConversacionGrupal(Long id_conversacion_grupal) {
    }
}
