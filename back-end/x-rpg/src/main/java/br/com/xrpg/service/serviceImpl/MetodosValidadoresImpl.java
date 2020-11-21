package br.com.xrpg.service.serviceImpl;

import br.com.xrpg.entity.UsuarioEntity;
import br.com.xrpg.repository.UsuarioRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.xrpg.service.MetodosValidadores;
import br.com.xrpg.utils.Utils;

import java.math.BigInteger;

@Service
@AllArgsConstructor
public class MetodosValidadoresImpl implements MetodosValidadores {

    private final BCryptPasswordEncoder pEnconder;

    private final UsuarioRepository usuarioRepository;

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

    @Override
    public BigInteger validarDadosLogin(String email, String senha) throws ObjectNotFoundException {

        UsuarioEntity usuarioEntity = usuarioRepository.findByEmailUsuAutenticacao(email);

        if (usuarioEntity != null) {

            //pEnconder.upgradeEncoding(usuarioEntity.g)
        }else {
            throw new ObjectNotFoundException("Esse endereço de email ainda não foi cadastrado em nosso sistema.");
        }

        return null;
    }
}
