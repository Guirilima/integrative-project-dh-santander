package br.com.xrpg.service;

import br.com.xrpg.entity.ClassEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassService {

    List<ClassEntity> getListaClasses();

    ClassEntity inclusaoClasse(ClassEntity newClass) throws ErrorSalvamento;
}
