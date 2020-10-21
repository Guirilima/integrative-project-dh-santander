package br.com.xrpg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.xrpg.entity.ClasseEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;

@Service
public interface ClasseService {

    List<ClasseEntity> getListaClasses();

    ClasseEntity inclusaoClasse(ClasseEntity newClass) throws ErrorSalvamento;
}
