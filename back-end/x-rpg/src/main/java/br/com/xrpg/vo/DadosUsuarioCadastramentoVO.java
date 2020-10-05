package br.com.xrpg.vo;

import br.com.xrpg.entity.UserAuthEntity;
import br.com.xrpg.entity.UserEntity;
import br.com.xrpg.entity.UserPersonalEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigInteger;

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
