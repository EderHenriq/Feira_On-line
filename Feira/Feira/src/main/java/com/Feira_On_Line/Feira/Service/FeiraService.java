package com.Feira_On_Line.Feira.Service;

import com.Feira_On_Line.Feira.Model.Feira;
import com.Feira_On_Line.Feira.Repository.FeiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Indica ao Spring que esta é uma classe de serviço
public class FeiraService {

    @Autowired // Injeção de dependência: O Spring nos dá o repositório pronto
    private FeiraRepository feiraRepositorio;

    // --- MÉTODOS PÚBLICOS (PARA O FRONTEND) ---

    // Listar todas as feiras
    public List<Feira> listarTodas() {
        return feiraRepositorio.findAll();
    }

    // Buscar feiras por dia da semana
    public List<Feira> buscarPorDia(String dia) {
        return feiraRepositorio.findByDiaDaSemanaContainingIgnoreCase(dia);
    }

    // Buscar feiras por bairro
    public List<Feira> buscarPorBairro(String bairro) {
        return feiraRepositorio.findByBairroContainingIgnoreCase(bairro);
    }

    // Buscar uma única feira pelo ID
    public Optional<Feira> buscarPorId(Long id) {
        return feiraRepositorio.findById(id);
    }

    // --- MÉTODOS DE ADMINISTRAÇÃO (PARA O FUTURO) ---

    // Criar uma nova feira
    public Feira criarFeira(Feira feira) {
        // (Aqui poderiam entrar validações de negócio)
        return feiraRepositorio.save(feira);
    }

    // Atualizar uma feira
    public Optional<Feira> atualizarFeira(Long id, Feira dadosFeira) {
        return feiraRepositorio.findById(id)
                .map(feiraExistente -> {
                    feiraExistente.setNome(dadosFeira.getNome());
                    feiraExistente.setDiaDaSemana(dadosFeira.getDiaDaSemana());
                    feiraExistente.setHorarioInicio(dadosFeira.getHorarioInicio());
                    feiraExistente.setHorarioFim(dadosFeira.getHorarioFim());
                    feiraExistente.setLocalizacao(dadosFeira.getLocalizacao());
                    feiraExistente.setEndereco(dadosFeira.getEndereco());
                    feiraExistente.setBairro(dadosFeira.getBairro());
                    return feiraRepositorio.save(feiraExistente);
                });
    }

    // Deletar uma feira
    public boolean deletarFeira(Long id) {
        if (feiraRepositorio.existsById(id)) {
            feiraRepositorio.deleteById(id);
            return true;
        }
        return false;
    }
}