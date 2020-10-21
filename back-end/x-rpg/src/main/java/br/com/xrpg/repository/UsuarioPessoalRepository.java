package br.com.xrpg.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.xrpg.entity.UsuarioPessoalEntity;

@Repository
public interface UsuarioPessoalRepository extends CrudRepository<UsuarioPessoalEntity, BigInteger> {

    @Query(value = "SELECT u.idUsuario,u.idUsuarioAutenticacao,ua.emailUsuAutenticacao,ua.senhaUsuAutenticacao," +
            "up.cpfPessoal,up.nomePessoal,up.sobrenomePessoal FROM UsuarioEntity u INNER JOIN UsuarioAutenticacaoEntity ua ON ua.idUsuarioAutenticacao = u.idUsuarioAutenticacao " +
            "INNER JOIN UsuarioPessoalEntity up ON up.idUsuarioPessoal = u.idUsuarioPessoal WHERE u.idUsuario = :idUser")
    public Object buscarPorDadosDoUsuario(@Param("idUser") BigInteger idUser);

    public UsuarioPessoalEntity findByCpfPessoal(String cpfUser);
}
