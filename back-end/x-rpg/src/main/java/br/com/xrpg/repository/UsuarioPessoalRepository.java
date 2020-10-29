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
//    @Query(value = "SELECT AVG(up.dataNascimento) FROM UsuarioPessoalEntity up")
//    @Query(value = "SELECT trunc((months_between(sysdate, to_date(up.dataNascimento ,'dd/mm/yyyy')))/12) " +
//            "AS IDADE FROM UsuarioPessoalEntity up INNER JOIN UsuarioEntity ue ON ue.idUsuarioPessoal = up.idUsuarioPessoal  " +
//            "WHERE ue.flagAtivo = :flagAtivo ")
    //public BigInteger getIdadeMediaUsuarios(@Param("flagAtivo") BigInteger flagAtivo );

    @Query(value = "SELECT up.estadoPessoal AS ESTADO, count(up) " +
            "FROM UsuarioPessoalEntity up GROUP BY up.estadoPessoal ")
    public List<Object> getCountUsuariosPorEstados();

}
