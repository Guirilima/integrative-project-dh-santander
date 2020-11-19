package br.com.xrpg.vo;

import lombok.*;

import java.math.BigInteger;
import java.util.Date;

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

    private BigInteger tipoUsuarioEnum;

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
