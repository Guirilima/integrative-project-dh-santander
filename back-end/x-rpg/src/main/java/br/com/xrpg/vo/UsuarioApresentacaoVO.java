package br.com.xrpg.vo;

import br.com.xrpg.entity.Role;
import lombok.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UsuarioApresentacaoVO {

    private BigInteger idUsuario;

    private BigInteger flagAtivo;						//0 = false | 1 = true

    private BigInteger idMestre;

    private Date dataUltimoLogin;

    private Set<Role> roles;

    private String nomePessoal;

    private String sobrenomePessoal;

    private String estadoPessoal;

    private String cidadePessoal;

    //private String cpfPessoal;

    private Date dataNascimento;

    //private String telefone;

    private String genero; //F=Feminino/M=Masculino/O=Outro

    private String emailUsuAutenticacao;

    //private BigInteger idUsuarioAutenticacao;
}
