package br.com.xrpg.service;

import org.springframework.stereotype.Service;

import br.com.xrpg.entity.CepEntity;

@Service
public interface CepService {

    public CepEntity findByCep(String cepString);
}
