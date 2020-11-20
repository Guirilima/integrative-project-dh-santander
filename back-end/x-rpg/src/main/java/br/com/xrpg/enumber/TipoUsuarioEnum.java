package br.com.xrpg.enumber;

import br.com.xrpg.exceptions.ObjectNotFound;
import lombok.Getter;

import java.math.BigInteger;
import java.util.Arrays;

@Getter
public enum TipoUsuarioEnum {

    NORMAL( new BigInteger("0"), "NORMAL"),
    ADMINISTRADOR( new BigInteger("1"), "ADMINISTRADOR");



    private TipoUsuarioEnum(BigInteger id, String name) {
        this.id = id;
        this.name = name;
    }

    private BigInteger id;
    private String name;

    public String getRoleName() {
        return "ROLE_"  + this.name;
    }

    public static TipoUsuarioEnum fromId(BigInteger id) {
        return Arrays
                .stream(TipoUsuarioEnum.values())
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFound("Role de id " + id + " não encontrado"));
    }
}
