package br.com.xrpg.service.serviceImpl;

import br.com.xrpg.service.MetodosValidadores;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class MetodosValidadoresImpl implements MetodosValidadores {
    @Override
    public String criptografarString(String value) {

        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encoded = encoder.encode(value.getBytes());

        return new String(encoded);
    }

    @Override
    public String descriptografarString(String value) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decoded = decoder.decode(value);

        return new String(decoded);
    }
}
