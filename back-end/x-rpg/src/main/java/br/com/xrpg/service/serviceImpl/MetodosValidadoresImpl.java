package br.com.xrpg.service.serviceImpl;

import org.springframework.stereotype.Service;

import br.com.xrpg.service.MetodosValidadores;
import br.com.xrpg.utils.Utils;

@Service
public class MetodosValidadoresImpl implements MetodosValidadores {

    @Override
    public String filtroCodificacao(String valor, String tipo) {

        if (tipo.equals("1")) {
            return Utils.criptografarString(valor);
        }else if (tipo.equals("0")){
            return Utils.descriptografarString(valor);
        }else {
            return "Tipo não é compativel ! 1 = criptografarString | 0 = descriptografarString";
        }
    }
}
