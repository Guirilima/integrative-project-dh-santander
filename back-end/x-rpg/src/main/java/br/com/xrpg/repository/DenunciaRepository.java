package br.com.xrpg.repository;

import br.com.xrpg.entity.DenunciasEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface DenunciaRepository extends CrudRepository<DenunciasEntity, BigInteger> {

    @Query(value = "FROM DenunciasEntity d WHERE (:idDenuncia IS NULL OR d.idDenuncia = :idDenuncia ) " +
            "AND (:idUsuarioDenunciado IS NULL OR d.idUsuarioDenunciado = :idUsuarioDenunciado) " +
            "AND (:categoria IS NULL OR d.categoriaDenuncia = :categoria) " +
            "AND (:flagDenuncia IS NULL OR d.flagDenunciaStatus = :flagDenuncia) " +
            "AND (:descDenuncia IS NULL OR d.descDenuncia LIKE %:descDenuncia% ) "
            //"AND (:data IS NULL OR d.dataDenuncia BETWEEN :data AND :data )"
    )
    public List<DenunciasEntity> getDenunciaByFiltros(@Param("idDenuncia") BigInteger idDenuncia,
                                                      @Param("idUsuarioDenunciado") BigInteger idUsuarioDenunciado,
                                                      @Param("categoria") String categoria,
                                                      @Param("flagDenuncia") String flagDenuncia,
                                                      @Param("descDenuncia") String descDenuncia);

}
