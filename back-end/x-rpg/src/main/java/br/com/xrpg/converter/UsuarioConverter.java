package br.com.xrpg.converter;


import br.com.xrpg.entity.UsuarioEntity;
import br.com.xrpg.vo.DadosUsuarioVO;
import br.com.xrpg.vo.UsuarioApresentacaoVO;

public interface UsuarioConverter {

    public UsuarioApresentacaoVO entityToApresentacaoVO (UsuarioEntity usuarioEntity);

    public DadosUsuarioVO entityToDadosUsuarioVO (UsuarioEntity usuarioEntity);
}
