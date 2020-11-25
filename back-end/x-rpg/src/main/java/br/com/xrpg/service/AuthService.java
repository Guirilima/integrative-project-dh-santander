package br.com.xrpg.service;

import br.com.xrpg.security.model.AuthResponse;
import br.com.xrpg.security.model.Credential;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    public AuthResponse login(Credential credential) ;
}
