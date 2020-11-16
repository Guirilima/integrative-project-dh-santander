package br.com.xrpg.repository;

import java.math.BigInteger;
import java.util.List;

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
    public Object buscarDadosDoUsuario(@Param("idUser") BigInteger idUser);

    public UsuarioPessoalEntity findByCpfPessoal(String cpfUser);

    @Query(value = "FROM UsuarioPessoalEntity up INNER JOIN UsuarioEntity ue ON ue.idUsuarioPessoal = up.idUsuarioPessoal " +
            "WHERE ue.flagAtivo = :flagAtivo ") //0 = INATIVO | 1 = ATIVO
    public List<UsuarioPessoalEntity> getAllUsuariosAtivos(@Param("flagAtivo") BigInteger flagAtivo );

    //TODO | TESTE RETORNA MEDIA IDADE
    @Query(value = "SELECT round(avg((year(current_date()) - year(up.data_nascimento)))) FROM usuario_pessoal up " +
            "INNER JOIN usuario u ON u.id_usuario_pessoal = up.id_usuario_pessoal WHERE u.flag_ativo = :flagAtivo ",nativeQuery = true)
    public BigInteger getIdadeMediaUsuarios(@Param("flagAtivo") BigInteger flagAtivo );

    @Query(value = "SELECT up.estadoPessoal AS ESTADO, count(up) " +
            "FROM UsuarioPessoalEntity up GROUP BY up.estadoPessoal ")
    public List<Object> getCountUsuariosPorEstados();

}
