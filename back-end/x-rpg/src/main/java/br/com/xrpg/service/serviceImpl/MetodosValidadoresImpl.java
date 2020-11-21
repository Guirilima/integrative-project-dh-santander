package br.com.xrpg.service.serviceImpl;

import br.com.xrpg.entity.UsuarioAutenticacao;
import br.com.xrpg.entity.UsuarioEntity;
import br.com.xrpg.repository.UsuarioAutenticacaoRepository;
import br.com.xrpg.repository.UsuarioRepository;
import br.com.xrpg.vo.Credential;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.xrpg.service.MetodosValidadores;
import br.com.xrpg.utils.Utils;

import java.math.BigInteger;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MetodosValidadoresImpl implements MetodosValidadores {

    private final BCryptPasswordEncoder pEnconder;

    private final UsuarioRepository usuarioRepository;

    private final AuthenticationManager authenticationManager;

    private final UsuarioAutenticacaoRepository usuarioAutenticacaoRepository;

    @Override
    public BigInteger validarDadosLogin(Credential credential) throws ObjectNotFoundException {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(credential.getUsername(), credential.getSenha());

        try {
            Authentication authenticate = this.authenticationManager.authenticate(authenticationToken);
        }catch (Exception ee){
            throw new ObjectNotFoundException("Combinação incorreta.");
        }

        //EMAIL OR NOME
        UsuarioEntity usuarioDados = usuarioRepository.findByNomePessoalOrEmailUsuario(credential.getUsername(),credential.getUsername());

//        SecurityContextHolder.getContext().setAuthentication(authenticate);
//
//        Usuario usuario = this.usuarioService.findByUsername(credential.getUsername());
//
//
//        String jwt = this.jwtUtil.generateToken(usuario);
//
//        return AuthResponse.builder()
//                .jwt(jwt)
//                .build();
        return usuarioDados.getIdUsuario();
    }
}
