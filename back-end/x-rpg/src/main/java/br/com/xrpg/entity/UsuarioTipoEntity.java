package br.com.xrpg.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "USUARIO_TIPO")
public class UsuarioTipoEntity {

    @Id
    @Column(name = "ID_TIPO_USU",nullable = false)                      //ID do tipo de Usuario
    private BigInteger idTipoUsuario;

    @Column(name = "DESC_TIPO_USU",length = 50,nullable = false)        //1 = Usuário Normal / 0 = Super Usuário(Adm)
    private String descricaoTipoUsuario;

}
