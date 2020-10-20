package br.com.xrpg.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xrpg.entity.RaceEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.repository.RaceRepository;
import br.com.xrpg.service.RaceService;

@Service
public class RaceServiceImpl implements RaceService {

    @Autowired
    RaceRepository raceRepository;

    @Override
    public List<RaceEntity> getListaRacas() {
        return raceRepository.getListaRacaEntitysDistinctName();
    }

    @Override
    public RaceEntity inclusaoRaca(RaceEntity newRaca) throws ErrorSalvamento{
        Optional
                .ofNullable(newRaca.getNameClass())
                .orElseThrow( () -> new ErrorSalvamento("O nome da raça não pode ser nulo"));

        if (newRaca.getNameClass().length() > 45) throw new ErrorSalvamento("O nome da raça, possuir mais que 45 caracteres");

        if (newRaca.getDescriptionClass() != null) {
            if (newRaca.getDescriptionClass().length() > 450) throw new ErrorSalvamento("A descriçaõ da raça, possui mais que 450 caracteres");
        }

        raceRepository.save(newRaca);

        return newRaca;
    }
}
