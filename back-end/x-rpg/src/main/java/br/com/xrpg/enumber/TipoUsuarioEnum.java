package br.com.xrpg.enumber;

import br.com.xrpg.exceptions.ObjectNotFound;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;
import lombok.Builder;
import java.util.Arrays;

@AllArgsConstructor
public enum TipoUsuarioEnum {

    NORMAL( new BigInteger("0"), "NORMAL"),
    ADMINISTRADOR( new BigInteger("1"), "ADMINISTRADOR");

    private final BigInteger id;
    private final String nome;

    public BigInteger getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static TipoUsuarioEnum getById(BigInteger i) {
        TipoUsuarioEnum result = null;

        for ( TipoUsuarioEnum tp : TipoUsuarioEnum.values() ) {
            if ( tp.id.equals(i) ) {
                result = tp;
                break;
            }
        }
        return result;
    }



//    @Builder
//    private TipoUsuarioEnum(BigInteger id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public String getRoleName() {
//        return "ROLE_"  + this.name;
//    }
//
//    public static TipoUsuarioEnum fromId(BigInteger id) {
//        return Arrays
//                .stream(TipoUsuarioEnum.values())
//                .filter(v -> v.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new ObjectNotFound("Role de id " + id + " não encontrado"));
//    }
}
