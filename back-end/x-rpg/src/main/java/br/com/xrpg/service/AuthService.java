package br.com.xrpg.service;

import br.com.xrpg.security.AuthResponse;
import br.com.xrpg.security.Credential;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public interface AuthService {

    public BigInteger validarDadosLogin(Credential credential) throws ObjectNotFoundException;

    public AuthResponse login(Credential credential) ;
}
