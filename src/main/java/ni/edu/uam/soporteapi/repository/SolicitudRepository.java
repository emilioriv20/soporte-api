package ni.edu.uam.soporteapi.repository;

import ni.edu.uam.soporteapi.entity.Solicitud;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class SolicitudRepository {

    private final List<Solicitud> solicitudes = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Solicitud> findAll() {
        return solicitudes;
    }

    public Solicitud save(Solicitud solicitud) {
        if (solicitud.getId() == null) {
            solicitud.setId(idGenerator.getAndIncrement());
            solicitudes.add(solicitud);
        } else {
            findById(solicitud.getId()).ifPresent(s -> {
                s.setTitulo(solicitud.getTitulo());
                s.setPrioridad(solicitud.getPrioridad());
                s.setEstado(solicitud.getEstado());
            });
        }
        return solicitud;
    }

    public Optional<Solicitud> findById(Long id) {
        return solicitudes.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }
}