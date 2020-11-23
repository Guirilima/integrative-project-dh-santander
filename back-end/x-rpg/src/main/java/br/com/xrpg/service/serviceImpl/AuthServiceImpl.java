package br.com.xrpg.service.serviceImpl;

import br.com.xrpg.entity.UsuarioEntity;
import br.com.xrpg.repository.UsuarioRepository;
import br.com.xrpg.security.model.AuthResponse;
import br.com.xrpg.security.model.Credential;
import br.com.xrpg.security.JwtUtil;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.xrpg.service.AuthService;

import java.math.BigInteger;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final BCryptPasswordEncoder pEnconder;

    private final UsuarioRepository usuarioRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

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

        return usuarioDados.getIdUsuario();
    }


    public AuthResponse login(Credential credential) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(credential.getUsername(), credential.getSenha());

        Authentication authenticate = this.authenticationManager.authenticate(authenticationToken );

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        UsuarioEntity usuario = this.usuarioRepository.findByUsername(credential.getUsername()).get();

        String jwt = this.jwtUtil.generateToken(usuario);

        return AuthResponse.builder()
                .jwt(jwt)
                .idUsuario(usuario.getIdUsuario())
                .email(usuario.getEmailUsuario())
                .build();

    }
}
