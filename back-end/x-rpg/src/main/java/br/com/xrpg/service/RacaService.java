package br.com.xrpg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.xrpg.entity.RacaEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;

@Service
public interface RacaService {

    List<RacaEntity> getListaRacas();

    RacaEntity inclusaoRaca(RacaEntity newRaca) throws ErrorSalvamento;
}
