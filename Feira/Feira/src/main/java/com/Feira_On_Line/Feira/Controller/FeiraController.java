package com.Feira_On_Line.Feira.Controller;

import com.Feira_On_Line.Feira.Model.Feira;
import com.Feira_On_Line.Feira.Service.FeiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Marca como um controlador REST (que retorna JSON)
@RequestMapping("/api/feiras") // URL base para este controlador
@CrossOrigin(origins = "*") // Permite que qualquer frontend (localhost) acesse esta API
public class FeiraController {

    @Autowired
    private FeiraService feiraServico;

    // Endpoint para LISTAR TODAS as feiras
    // URL: GET http://localhost:8080/api/feiras
    @GetMapping
    public List<Feira> listarTodasFeiras() {
        return feiraServico.listarTodas();
    }

    // Endpoint para BUSCAR UMA feira por ID
    // URL: GET http://localhost:8080/api/feiras/1
    @GetMapping("/{id}")
    public ResponseEntity<Feira> buscarFeiraPorId(@PathVariable Long id) {
        Optional<Feira> feira = feiraServico.buscarPorId(id);
        return feira.map(ResponseEntity::ok) // Se achar, retorna 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); // Se não, 404 Not Found
    }

    // Endpoint para BUSCAR por DIA DA SEMANA
    // URL: GET http://localhost:8080/api/feiras/buscar/dia?nome=domingo
    @GetMapping("/buscar/dia")
    public List<Feira> buscarFeirasPorDia(@RequestParam("nome") String dia) {
        return feiraServico.buscarPorDia(dia);
    }

    // Endpoint para BUSCAR por BAIRRO
    // URL: GET http://localhost:8080/api/feiras/buscar/bairro?nome=zona
    @GetMapping("/buscar/bairro")
    public List<Feira> buscarFeirasPorBairro(@RequestParam("nome") String bairro) {
        return feiraServico.buscarPorBairro(bairro);
    }

    // Endpoint para CRIAR uma nova feira
    // URL: POST http://localhost:8080/api/feiras
    // (Envia os dados da feira no corpo da requisição em JSON)
    @PostMapping
    public ResponseEntity<Feira> criarFeira(@RequestBody Feira feira) {
        Feira novaFeira = feiraServico.criarFeira(feira);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaFeira);
    }

    // Endpoint para ATUALIZAR uma feira
    // URL: PUT http://localhost:8080/api/feiras/1
    @PutMapping("/{id}")
    public ResponseEntity<Feira> atualizarFeira(@PathVariable Long id, @RequestBody Feira dadosFeira) {
        return feiraServico.atualizarFeira(id, dadosFeira)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para DELETAR uma feira
    // URL: DELETE http://localhost:8080/api/feiras/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFeira(@PathVariable Long id) {
        if (feiraServico.deletarFeira(id)) {
            return ResponseEntity.noContent().build(); // 204 No Content (sucesso)
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}