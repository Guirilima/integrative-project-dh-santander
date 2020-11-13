package br.com.xrpg.service.serviceImpl;

import br.com.xrpg.entity.DenunciasEntity;
import br.com.xrpg.exceptions.ArgumentNotValid;
import br.com.xrpg.exceptions.ObjectNotFound;
import br.com.xrpg.repository.DenunciaRepository;
import br.com.xrpg.service.DenunciaService;
import br.com.xrpg.vo.DenunciaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DenunciaServiceImpl implements DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;

    public List<DenunciasEntity> getDenunciaByFiltro(DenunciaVO dadosFiltro) {

        List<DenunciasEntity> listaDenuncias = denunciaRepository.getDenunciaByFiltros(dadosFiltro.getIdDenuncia(), dadosFiltro.getIdUsuarioDenunciado(),
                dadosFiltro.getCategoriaDenuncia(), dadosFiltro.getFlagDenunciaStatus(), dadosFiltro.getDescDenuncia());

        return listaDenuncias;
    }

    @Override
    public DenunciasEntity create(DenunciasEntity newDenuncia) {
        String erros = "";
        if (newDenuncia.getIdUsuarioDenunciado() == null)   erros = "idUsuarioDenunciado nulo, ";
        if (newDenuncia.getIdUsuarioDenunciou() == null)    erros = "IdUsuarioDenunciou nulo, ";
        if (newDenuncia.getDescDenuncia() == null)          erros = "DescDenuncia nulo, ";
        if (newDenuncia.getCategoriaDenuncia() == null)     erros = "CategoriaDenuncia nulo, ";

        if (newDenuncia.getDataDenuncia() == null)  newDenuncia.setDataDenuncia(new Date());

        if (!erros.isEmpty()) throw new RuntimeException("Erros nos campos: " + erros);

        return this.denunciaRepository.save(newDenuncia);
    }

    @Override
    public DenunciasEntity update(DenunciasEntity denuncia) {
        DenunciasEntity denunciaBD = findById(denuncia.getIdDenuncia());

        String erros = "";
        if (!denuncia.getIdUsuarioDenunciado().equals(denunciaBD.getIdUsuarioDenunciado()))   erros = "idUsuarioDenunciado não pode ser alterado, ";
        if (!denuncia.getIdUsuarioDenunciou().equals(denunciaBD.getIdUsuarioDenunciou()))    erros = "IdUsuarioDenunciou não pode ser alterado, ";
        if (denuncia.getDescDenuncia() == null)          erros = "DescDenuncia nulo, ";
        if (denuncia.getCategoriaDenuncia() == null)     erros = "CategoriaDenuncia nulo, ";

        if (!erros.isEmpty()) throw new RuntimeException("Erros nos campos: " + erros);

        return this.denunciaRepository.save(denuncia);
    }

    @Override
    public DenunciasEntity findById(BigInteger idDenuncia) {

        Optional.ofNullable(idDenuncia).orElseThrow(() -> new ArgumentNotValid("Id não pode ser nulo"));


        return this.denunciaRepository.findById(idDenuncia)
                .orElseThrow(() -> new ObjectNotFound("Não foi possivel encontrar a denuncia de id: " + idDenuncia));
    }

    @Override
    public void delete(BigInteger idDenuncia) {
    //TODO, não haverá realmente uma esclusão

    }

    @Override
    public Iterable<DenunciasEntity> findAll() {
        return denunciaRepository.findAll();
    }

}
