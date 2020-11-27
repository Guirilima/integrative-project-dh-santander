package br.com.xrpg.service;

import java.math.BigInteger;
import java.util.List;

import br.com.xrpg.entity.CampanhaEntity;
import br.com.xrpg.vo.HttpGenericPageableResponse;
import org.springframework.stereotype.Service;

import br.com.xrpg.entity.RacaEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;

@Service
public interface RacaService {

    HttpGenericPageableResponse getListaRacas(int pagina, int qtdPagina);

    RacaEntity inclusaoRaca(RacaEntity newRaca) throws ErrorSalvamento;

    RacaEntity encontrarPorId(BigInteger idRaca);

    List<RacaEntity> getListaRacasSemPaginacao();

    void deletar(BigInteger idRaca);
}
