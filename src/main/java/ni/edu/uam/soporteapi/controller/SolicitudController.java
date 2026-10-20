package ni.edu.uam.soporteapi.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin(origins = "*")
public class SolicitudController {

    private final List<Map<String, Object>> solicitudes = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @GetMapping
    public List<Map<String, Object>> listar() {
        return solicitudes;
    }

    @PostMapping
    public Map<String, Object> guardar(@RequestBody Map<String, Object> nueva) {
        nueva.put("id", idGenerator.getAndIncrement());
        if (!nueva.containsKey("estado")) {
            nueva.put("estado", "Pendiente");
        }
        solicitudes.add(nueva);
        return nueva;
    }

    @PatchMapping("/{id}")
    public Map<String, Object> actualizarEstado(@PathVariable Long id, @RequestBody Map<String, String> body) {
        for (Map<String, Object> s : solicitudes) {
            if (s.get("id").toString().equals(id.toString())) {
                s.put("estado", body.get("estado"));
                return s;
            }
        }
        return null;
    }
}