package br.com.xrpg.service;

import org.springframework.stereotype.Service;

@Service
public interface MetodosValidadores {

    public String criptografarString(String value);

    public String descriptografarString(String value);
}
