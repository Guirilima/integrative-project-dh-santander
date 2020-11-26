package br.com.xrpg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "NOTIFICACAO")
public class NotificacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idNotificacao",nullable = false)
    private BigInteger idNotificacao;

    @Column(name = "idUsuarioConvidou",nullable = false)
    private BigInteger idUsuarioConvidou;

    @Column(name = "idUsuarioConvidado",nullable = false)
    private BigInteger idUsuarioConvidado;

    @Column(name = "descricaoConvite",nullable = false)
    private String descricaoConvite;

    @Column(name = "flagMeioConvite",nullable = false)
    private String flagMeioConvite;//E = EMAIL.W = WHATSAPP

    @Column(name = "flagSituacaoConvite")
    private String flagSituacaoConvite; //A = ACEITO.R = RECUSADO.E = EXPIRADO.N = NÂO VISUALIZADO

    @Column(name = "dataConvite",nullable = false)
    private Date dataConvite;


}
