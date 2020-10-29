package br.com.xrpg.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xrpg.entity.RacaEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.repository.RacaRepository;
import br.com.xrpg.service.RacaService;

@Service
public class RacaServiceImpl implements RacaService {

    @Autowired
    RacaRepository racaRepository;

    @Override
    public Iterable<RacaEntity> getListaRacas() {
        return racaRepository.findAll();
    }

    @Override
    public RacaEntity inclusaoRaca(RacaEntity newRaca) throws ErrorSalvamento{
        Optional
                .ofNullable(newRaca.getNomeRaca())
                .orElseThrow( () -> new ErrorSalvamento("O nome da raça não pode ser nulo"));

        if (newRaca.getNomeRaca().length() > 45) throw new ErrorSalvamento("O nome da raça, possuir mais que 45 caracteres");

        if (newRaca.getDescricaoRaca() != null) {
            if (newRaca.getDescricaoRaca().length() > 450) throw new ErrorSalvamento("A descriçaõ da raça, possui mais que 450 caracteres");
        }

        racaRepository.save(newRaca);

        return newRaca;
    }
}
