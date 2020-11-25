package br.com.xrpg.service.serviceImpl;

import br.com.xrpg.entity.UsuarioEntity;
import br.com.xrpg.projection.UsuarioRoleProjection;
import br.com.xrpg.repository.RoleRepository;
import br.com.xrpg.repository.UsuarioRepository;
import br.com.xrpg.security.model.AuthResponse;
import br.com.xrpg.security.model.Credential;
import br.com.xrpg.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.xrpg.service.AuthService;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;

    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;

    private final JwtUtil jwtUtil;

    public AuthResponse login(Credential credential) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(credential.getUsername(), credential.getSenha());

        Authentication authenticate = this.authenticationManager.authenticate(authenticationToken );

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        UsuarioEntity usuario = this.usuarioRepository.findByUsername(credential.getUsername()).get();

//        UsuarioRoleProjection usuarioRoleBD = this.roleRepository.findByIdUsuario(usuario.getIdUsuario());

        String jwt = this.jwtUtil.generateToken(usuario);

        return AuthResponse.builder()
                .jwt(jwt)
                .idUsuario(usuario.getIdUsuario())
                .email(usuario.getEmailUsuario())
                .build();

    }
}
