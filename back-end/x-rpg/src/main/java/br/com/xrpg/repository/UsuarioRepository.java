package br.com.xrpg.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.xrpg.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioEntity, BigInteger> {

    @Query(value = "FROM UsuarioEntity u " +
            "WHERE u.idUsuario = :idUser")
    public UsuarioEntity buscarDadosDoUsuario(@Param("idUser") BigInteger idUser);

    public UsuarioEntity findByCpfPessoal(String cpfUser);

    @Query(value = "FROM UsuarioEntity u " +
            "WHERE u.flagAtivo = :flagAtivo ") //0 = INATIVO | 1 = ATIVO
    public List<UsuarioEntity> getAllUsuariosAtivos(@Param("flagAtivo") BigInteger flagAtivo );

    //TODO | TESTE RETORNA MEDIA IDADE
    @Query(value = "SELECT round(avg((year(current_date()) - year(u.data_nascimento)))) FROM UsuarioEntity u " +
            "WHERE u.flag_ativo = :flagAtivo ",nativeQuery = true)
    public BigInteger getIdadeMediaUsuarios(@Param("flagAtivo") BigInteger flagAtivo );

    @Query(value = "SELECT u.estadoPessoal AS ESTADO, count(u) " +
            "FROM UsuarioEntity u GROUP BY u.estadoPessoal ")
    public List<Object> getCountUsuariosPorEstados();

    public UsuarioEntity findByEmailUsuario(String email);

    public UsuarioEntity findByNomePessoalOrEmailUsuario(String nome,String email);

    @Query("FROM UsuarioEntity u WHERE u.flagAtivo <> 0 ")
    Page<List<Object>> getUsuariosAtivos(Pageable pageable);

    Optional<UsuarioEntity> findByUsername(String username);
}
