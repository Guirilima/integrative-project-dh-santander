package br.com.xrpg.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DenunciaVO {

    @ApiModelProperty(name = "idDenuncia", value = "Id Da Denuncia",example = "null",position = 1)
    private BigInteger idDenuncia;

    @ApiModelProperty(name = "idUsuarioDenunciado", value = "Id Usuario que foi Denunciado",example = "null",position = 2)
    private BigInteger idUsuarioDenunciado;

    @ApiModelProperty(name = "idUsuarioDenunciou",hidden = true, value = "Id Usuario que Denunciou",example = "null")
    private BigInteger idUsuarioDenunciou;

    @ApiModelProperty(name = "descDenuncia", value = "Descrição Da denuncia",example = "null",position = 3)
    private String descDenuncia;

    @ApiModelProperty(name = "dataDenuncia",hidden = true, value = "Data Da Denuncia",example = "2020-11-04T00:11:53.788Z",position = 4)
    private Date dataDenuncia;

    @ApiModelProperty(name = "categoriaDenuncia", value = "Categoria Da Denuncia",example = "null",position = 5)
    private String categoriaDenuncia;

    @ApiModelProperty(name = "flagDenunciaStatus", value = "Flag Da Denuncia",example = "null",position = 6)
    private String flagDenunciaStatus;

    //FIXME - PUNIÇÂO ENTIDADE

    @ApiModelProperty(name = "flagPunicao",hidden = true, value = "Flag Da Punição",example = "null",position = 7)
    private String flagPunicao;             //B = Banido | S = Suspenso por 7 dias | N = NãoPunido

    @ApiModelProperty(name = "qtdPunicao",hidden = true, value = "Quantidade de Punições",example = "null")
    private BigInteger qtdPunicao;          // == 3 Banimento

    @ApiModelProperty(name = "dataPunicao",hidden = true, value = "Data Da Punição",example = "null")
    private Date dataPunicao;
}
