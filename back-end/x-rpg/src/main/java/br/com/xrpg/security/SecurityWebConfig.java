package br.com.xrpg.security;

import br.com.xrpg.enumber.TipoUsuarioEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/csrf", "/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
                        "/configuration/**", "/swagger-ui.html", "/webjars/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/usuario").hasRole(TipoUsuarioEnum.ADMINISTRADOR.getName())
                .antMatchers(HttpMethod.GET, "/api/usuario").hasRole(TipoUsuarioEnum.ADMINISTRADOR.getName())
                .antMatchers(HttpMethod.DELETE, "/api/usuario").hasRole(TipoUsuarioEnum.ADMINISTRADOR.getName())
                .antMatchers(HttpMethod.PUT, "/api/usuario").hasRole(TipoUsuarioEnum.ADMINISTRADOR.getName())

                .antMatchers(HttpMethod.POST, "/api/securanca").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors().disable().csrf().disable()
                .httpBasic();
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