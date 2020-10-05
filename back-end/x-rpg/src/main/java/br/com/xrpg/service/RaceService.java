package br.com.xrpg.service;

import br.com.xrpg.entity.RaceEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RaceService {

    List<RaceEntity> getListaRacas();

    RaceEntity inclusaoRaca(RaceEntity newRaca) throws ErrorSalvamento;
}
