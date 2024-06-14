package utp.edu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.models.dao.*;
import utp.edu.models.dto.CrearGrupoDTO;
import utp.edu.models.dto.MiembroDTO;
import utp.edu.models.dto.UpdateMiembroDTO;
import utp.edu.models.entities.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GrupoServiceImpl implements IGrupoService {

    @Autowired
    private GrupoDao grupoDao;

    @Autowired
    private PersonaDao personaDao;

    @Autowired
    private MiembroGrupoDao miembroGrupoDao;

    @Autowired
    private CursoDao cursoDao;

    @Autowired
    private IConversacionGrupalService conversacionGrupalService;

    @Autowired
    private DocenteCursoDao docenteCursoDao;

    @Override
    public List<Grupo> getGruposByCodPersona(String codigoPersona) {
        return grupoDao.getGruposByCodPersona(codigoPersona);
    }

    @Override
    public Grupo crearGrupo(CrearGrupoDTO grupoDTO) {
        Optional<Persona> personaLider = personaDao.findPersonaByCod(grupoDTO.getCodigoUsuario());

        if (personaLider.isPresent()) {
            Grupo nuevoGrupo = new Grupo();

            // Guardar grupo
            LocalDateTime now = LocalDateTime.now();
            nuevoGrupo.setFecha_creacion(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));
            nuevoGrupo.setCurso(cursoDao.findById(grupoDTO.getIdCurso()).get());
            nuevoGrupo.setNombre(grupoDTO.getNombregrupo());
            Grupo grupoGuardado = grupoDao.save(nuevoGrupo);

            // Guardar líder del grupo
            MiembroGrupo lider = new MiembroGrupo();
            lider.setEs_lider(true);
            lider.setGrupo(grupoGuardado);
            lider.setPersona(personaLider.get());
            lider.setRol("Lider");
            miembroGrupoDao.save(lider);

            // Guardar miembros del grupo
            if (grupoDTO.getCodigosMiembros() != null) {
                for (String codigoMiembro : grupoDTO.getCodigosMiembros()) {
                    Optional<Persona> personaMiembro = personaDao.findPersonaByCod(codigoMiembro);

                    if (personaMiembro.isPresent() && !personaMiembro.get().equals(personaLider.get())) {
                        MiembroGrupo nuevoMiembro = new MiembroGrupo();
                        nuevoMiembro.setEs_lider(false);
                        nuevoMiembro.setGrupo(grupoGuardado);
                        nuevoMiembro.setPersona(personaMiembro.get());
                        nuevoMiembro.setRol("Estudiante");

                        miembroGrupoDao.save(nuevoMiembro);
                    } else {
                        System.err.println("El miembro con código " + codigoMiembro + " no se pudo encontrar. Se omitirá.");
                    }
                }
            }

           /* // Asignar docente al grupo
            Optional<DocenteCurso> docenteCurso = docenteCursoDao.findByCursoId(grupoDTO.getIdCurso());
            if (docenteCurso.isPresent()) {
                Docente docente = docenteCurso.get().getDocente();
                MiembroGrupo docenteGrupo = new MiembroGrupo();
                docenteGrupo.setEs_lider(false);
                docenteGrupo.setGrupo(grupoGuardado);
                docenteGrupo.setPersona(docente.getPersona());
                docenteGrupo.setRol("Docente");
                miembroGrupoDao.save(docenteGrupo);
            }*/

            // Crear conversación grupal asociada
            conversacionGrupalService.iniciarConversacion(grupoGuardado.getId());

            return grupoGuardado;
        }
        throw new RuntimeException("El usuario líder no se pudo encontrar");
    }

    @Override
    public void deleteMiembro(MiembroDTO miembroDTO) {
        Optional<Persona> miembroEncontrado = personaDao.findPersonaByCod(miembroDTO.getCodMiembro());
        if (miembroEncontrado.isPresent()) {
            String codigo = miembroDTO.getCodMiembro();
            Long idGrupo = miembroDTO.getIdGrupo();
            grupoDao.deleteMiembro(codigo, idGrupo);
        } else {
            throw new RuntimeException("El miembro a eliminar no se pudo encontrar");
        }
    }

    @Override
    public MiembroGrupo agregarMiembroAlGrupo(MiembroDTO miembroDTO) {
        Optional<Persona> personaMiembro = personaDao.findPersonaByCod(miembroDTO.getCodMiembro());
        if (personaMiembro.isPresent()) {
            MiembroGrupo nuevoMiembro = new MiembroGrupo();
            nuevoMiembro.setEs_lider(false);
            nuevoMiembro.setGrupo(grupoDao.findById(miembroDTO.getIdGrupo()).get());
            nuevoMiembro.setPersona(personaMiembro.get());
            nuevoMiembro.setRol("Estudiante");

            return miembroGrupoDao.save(nuevoMiembro);
        }
        throw new RuntimeException("El miembro con código " + miembroDTO.getCodMiembro() + " no se pudo encontrar");
    }

    @Override
    public MiembroGrupo updateMiembro(UpdateMiembroDTO updateMiembroDTO) {
        String codigo = updateMiembroDTO.getCodigoUsuario();
        Long idGrupo = updateMiembroDTO.getIdGrupo();
        Optional<MiembroGrupo> miembroEncontrado = grupoDao.findMiembro(codigo, idGrupo);
        if (miembroEncontrado.isPresent()) {
            MiembroGrupo miembro = miembroEncontrado.get();
            miembro.setRol(updateMiembroDTO.getRolGrupo());
            return miembroGrupoDao.save(miembro);
        } else {
            throw new RuntimeException("El miembro a modificar no se pudo encontrar");
        }
    }

    @Override
    public String getRolInGroup(Long grupoId, String codigoPersona) {
        MiembroGrupo miembro = grupoDao.findMiembroByCodigoPersonaAndGrupoId(codigoPersona, grupoId);
        return miembro != null ? miembro.getRol() : "No Asignado";
    }
}
