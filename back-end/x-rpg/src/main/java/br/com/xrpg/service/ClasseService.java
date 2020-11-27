package br.com.xrpg.service;

import java.math.BigInteger;
import java.util.List;

import br.com.xrpg.entity.RacaEntity;
import org.springframework.stereotype.Service;

import br.com.xrpg.entity.ClasseEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;

@Service
public interface ClasseService {

    Iterable<ClasseEntity> getListaClasses();

    ClasseEntity inclusaoClasse(ClasseEntity newClass) throws ErrorSalvamento;

    ClasseEntity encontrarPorId(BigInteger idClasse);

    List<ClasseEntity> getListaClassesSemPaginacao();

    void deletar(BigInteger idClasse);
}
