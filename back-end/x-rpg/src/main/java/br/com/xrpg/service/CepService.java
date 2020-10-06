package br.com.xrpg.service;

import br.com.xrpg.entity.CepEntity;
import org.springframework.stereotype.Service;

@Service
public interface CepService {

    public CepEntity findByCep(String cepString);
}
