package br.com.xrpg.converter.impl;

import br.com.xrpg.converter.UsuarioConverter;
import br.com.xrpg.entity.UsuarioEntity;
import br.com.xrpg.vo.DadosUsuarioVO;
import br.com.xrpg.vo.UsuarioApresentacaoVO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConverterImpl implements UsuarioConverter {

    @Override
    public UsuarioApresentacaoVO entityToApresentacaoVO(UsuarioEntity usuarioEntity) {

        return UsuarioApresentacaoVO.builder()
                .idUsuario(usuarioEntity.getIdUsuario())
                .idMestre(usuarioEntity.getIdMestre())
                .cidadePessoal(usuarioEntity.getCidadePessoal())
                .dataNascimento(usuarioEntity.getDataNascimento())
                .dataUltimoLogin(usuarioEntity.getDataUltimoLogin())
                .emailUsuAutenticacao(usuarioEntity.getEmailUsuario())
                .estadoPessoal(usuarioEntity.getEstadoPessoal())
                .flagAtivo(usuarioEntity.getFlagAtivo())
                .genero(usuarioEntity.getGenero())
                .nomePessoal(usuarioEntity.getNomePessoal())
                .sobrenomePessoal(usuarioEntity.getSobrenomePessoal())
                .roles(usuarioEntity.getRoles()).build();
    }

    @Override
    public DadosUsuarioVO entityToDadosUsuarioVO(UsuarioEntity usuarioEntity) {

        return DadosUsuarioVO.builder()
                .cidadeUsur(usuarioEntity.getCidadePessoal())
                .cpfUsur(usuarioEntity.getCpfPessoal())
                .emailUsur(usuarioEntity.getEmailUsuario())
                .estadoUsur(usuarioEntity.getEstadoPessoal())
                .flagAtivoUsur(usuarioEntity.getFlagAtivo())
                .generoUsur(usuarioEntity.getGenero())
                .idUsuario(usuarioEntity.getIdUsuario())
                .idUsuarioAuth(usuarioEntity.getIdUsuario())
                .idUsuarioPessoal(usuarioEntity.getIdUsuario())
                .nascimentoUsur(usuarioEntity.getDataNascimento())
                .nomeUsur(usuarioEntity.getNomePessoal())
                .senhaUsur(usuarioEntity.getCidadePessoal())
                .sobrenomeUsur(usuarioEntity.getSobrenomePessoal())
                .telefoneUsur(usuarioEntity.getTelefone())
                .roles(usuarioEntity.getRoles()).build();
    }
}
