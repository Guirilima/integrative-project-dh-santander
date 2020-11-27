package br.com.xrpg.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

    private UsuarioDetalheServiceImpl usuarioDetalheService;

    private JwtUtil jwtUtil;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/csrf", "/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
                        "/configuration/**", "/swagger-ui.html", "/webjars/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/usuario").permitAll()
                .antMatchers(HttpMethod.GET, "/api/usuario").hasRole("ADMINISTRADOR")
                .antMatchers(HttpMethod.DELETE, "/api/usuario").hasRole("ADMINISTRADOR")
                .antMatchers(HttpMethod.PUT, "/api/usuario").hasRole("ADMINISTRADOR")

                .antMatchers(HttpMethod.POST, "/api/seguranca/**").permitAll()

                .antMatchers(HttpMethod.POST, "/api/classe/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/classe/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/classe/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/classe/**").hasRole("ADMINISTRADOR")

                .antMatchers(HttpMethod.POST, "/api/mestre/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/mestre/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/mestre/**").permitAll()

                .antMatchers(HttpMethod.POST, "/api/personagem/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/personagem/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/personagem/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/personagem/**").hasRole("ADMINISTRADOR")

                .antMatchers(HttpMethod.POST, "/api/endereco/**").permitAll()

                .antMatchers(HttpMethod.POST, "/api/noticias/**").hasRole("ADMINISTRADOR")
                .antMatchers(HttpMethod.GET, "/api/noticias/**").permitAll()

                .antMatchers(HttpMethod.POST, "/api/raca/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/raca/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/raca/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/raca/**").hasRole("ADMINISTRADOR")

                .antMatchers(HttpMethod.GET, "/api/notificacao/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/notificacao/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/notificacao/**").hasRole("ADMINISTRADOR")
                .antMatchers(HttpMethod.POST, "/api/notificacao").permitAll()

                .antMatchers(HttpMethod.POST, "/api/denuncias/**").hasRole("ADMINISTRADOR")
                .antMatchers(HttpMethod.GET, "/api/denuncias/**").hasRole("ADMINISTRADOR")
                .antMatchers(HttpMethod.PUT, "/api/denuncias/**").hasRole("ADMINISTRADOR")
                .antMatchers(HttpMethod.DELETE, "/api/denuncias/**").hasRole("ADMINISTRADOR")

                //.antMatchers(HttpMethod.GET, "/seguranca-controller/criarNovoUsuarioCadastroUsingPOST").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors().and().csrf().disable()
                //.httpBasic() SEM REGRA DO FILTER
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtUtil));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(usuarioDetalheService).passwordEncoder(bCryptPasswordEncoder());

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
