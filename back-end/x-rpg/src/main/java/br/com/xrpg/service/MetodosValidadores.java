package br.com.xrpg.service;

import br.com.xrpg.vo.Credential;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public interface MetodosValidadores {

    public BigInteger validarDadosLogin(Credential credential) throws ObjectNotFoundException;
}
