package utp.edu.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "notificacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion", nullable = false)
    private Long id;
    private String mensaje;
    private LocalDate fecha;
    private LocalTime hora;
    @ManyToOne
    @JoinColumn(name = "id_grupo")
    private Grupo grupo;
}
