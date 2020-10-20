package br.com.xrpg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.xrpg.entity.ClassEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;

@Service
public interface ClassService {

    List<ClassEntity> getListaClasses();

    ClassEntity inclusaoClasse(ClassEntity newClass) throws ErrorSalvamento;
}
