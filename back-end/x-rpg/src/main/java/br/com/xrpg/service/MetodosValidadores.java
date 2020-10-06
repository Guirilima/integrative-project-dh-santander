package br.com.xrpg.service;

import org.springframework.stereotype.Service;

@Service
public interface MetodosValidadores {

    public String filtroCodificacao(String valor,String tipo);
}
