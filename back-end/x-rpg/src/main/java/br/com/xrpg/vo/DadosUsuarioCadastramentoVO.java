package br.com.xrpg.vo;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

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
public class DadosUsuarioCadastramentoVO {

//SERÀ NECESSARIO RESOLVER O BANCO SQL- NÂO TEM TODOS OS DADOS CONTIDOS NO FRONT/UX

    private String emailUser;
    private String passwordUser;
    private String nameUser;
    private String lastNameUser;
    private String cpfUser;
    private String cityUser;
    private String stateUser;
    private Date DateOfBirth;
    private String phoneNumber;
    private String genero;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigInteger idUser;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigInteger idUserAuth;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigInteger idUserPersonal;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigInteger flagActiveUser;

//    private UserEntity newUser;
//    private UserAuthEntity newUserAuth;
//    private UserPersonalEntity newUserPersonal;
}
