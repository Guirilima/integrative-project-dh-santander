package br.com.xrpg.vo;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DadosUsuarioVO {

    @ApiModelProperty(name = "nomeUsur", value = "Nome Usuário",example = "Gustavo",position = 1)
    private String nomeUsur;

    @ApiModelProperty(name = "sobrenomeUsur", value = "Xavier Lima",example = "Xavier Lima",position = 2)
    private String sobrenomeUsur;

    @ApiModelProperty(name = "cidadeUsur", value = "Cidade endereço Usuário",example = "São Paulo",position = 10)
    private String cidadeUsur;

    @ApiModelProperty(name = "estadoUsur", value = "Estado endereço Usuário",example = "SP",position = 9)
    private String estadoUsur;

    @ApiModelProperty(position = 3)
    private Date   nascimentoUsur;

    @ApiModelProperty(name = "generoUsur", value = "Genero do Usuário",example = "M/F/O",position = 4)
    private String generoUsur;

    //DADOS SENSIVEIS
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(name = "emailUsur", value = "Email do Usuário",example = "umEmailValido@Gmail.com",position = 5)
    private String emailUsur;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(name = "senhaUsur", value = "Senha Usuário",example = "1234@4321",position = 6)
    private String senhaUsur;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(name = "telefoneUsur", value = "Telefone Usuário",example = "11989786756",position = 8)
    private String telefoneUsur;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(name = "cpfUsur", value = "CPF Usuário",example = "36751751036",position = 7,dataType = "String",allowableValues = "1,2,3")
    private String cpfUsur;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(hidden = true)
    private BigInteger idUsuario;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(hidden = true)
    private BigInteger idUsuarioAuth;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(hidden = true)
    private BigInteger idUsuarioPessoal;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(name = "flagAtivoUsur", value = "1",example = "1",position = 11)
    private BigInteger flagAtivoUsur;

//    private UserEntity newUser;
//    private UserAuthEntity newUserAuth;
//    private UserPersonalEntity newUserPersonal;
}
