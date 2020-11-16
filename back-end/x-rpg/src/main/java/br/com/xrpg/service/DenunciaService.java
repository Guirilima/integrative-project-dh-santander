package br.com.xrpg.service;

import br.com.xrpg.entity.DenunciasEntity;
import br.com.xrpg.vo.DenunciaVO;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public interface DenunciaService {

    public List<DenunciasEntity> getDenunciaByFiltro(DenunciaVO dadosFiltro);
    public DenunciasEntity create(DenunciasEntity newDenuncia) ;
    public DenunciasEntity update(DenunciasEntity denuncia) ;
    public DenunciasEntity findById(BigInteger idDenuncia) ;
    public void delete(BigInteger idDenuncia) ;
    public Iterable<DenunciasEntity> findAll();

}
