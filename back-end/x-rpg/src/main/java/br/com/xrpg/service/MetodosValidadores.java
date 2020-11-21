package br.com.xrpg.service;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public interface MetodosValidadores {

    public String filtroCodificacao(String valor,String tipo);

    public BigInteger validarDadosLogin(String email, String senha) throws ObjectNotFoundException;
}
