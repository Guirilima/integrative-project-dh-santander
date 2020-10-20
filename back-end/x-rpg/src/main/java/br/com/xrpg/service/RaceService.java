package br.com.xrpg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.xrpg.entity.RaceEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;

@Service
public interface RaceService {

    List<RaceEntity> getListaRacas();

    RaceEntity inclusaoRaca(RaceEntity newRaca) throws ErrorSalvamento;
}
