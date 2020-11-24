package br.com.xrpg.service;

import java.util.List;

import br.com.xrpg.vo.HttpGenericPageableResponse;
import org.springframework.stereotype.Service;

import br.com.xrpg.entity.RacaEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;

@Service
public interface RacaService {

    HttpGenericPageableResponse getListaRacas(int pagina, int qtdPagina);

    RacaEntity inclusaoRaca(RacaEntity newRaca) throws ErrorSalvamento;
}
