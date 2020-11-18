package br.com.xrpg.enumber;

import br.com.xrpg.exceptions.ObjectNotFound;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TipoUsuarioEnum {

    NORMAL(0, "CLIENTE"),
    ADMINISTRADOR(1, "ADMINISTRADOR");



    private TipoUsuarioEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private int id;
    private String name;

    public String getRoleName() {
        return "ROLE_"  + this.name;
    }

    public static TipoUsuarioEnum fromId(int id) {
        return Arrays
                .stream(TipoUsuarioEnum.values())
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ObjectNotFound("Role de id " + id + " não encontrado"));
    }
}
