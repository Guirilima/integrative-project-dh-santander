package br.com.xrpg.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xrpg.entity.ClassEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.repository.ClassRepository;
import br.com.xrpg.service.ClassService;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    ClassRepository classRepository;

    @Override
    public List<ClassEntity> getListaClasses() {

        return classRepository.getListaClassEntitysDistinctName();
    }

    @Override
    public ClassEntity inclusaoClasse(ClassEntity newClass) throws ErrorSalvamento {
        Optional
                .ofNullable(newClass.getNameClass())
                .orElseThrow( () -> new ErrorSalvamento("O nome da Classe não pode ser nulo"));

        if (newClass.getNameClass().length() > 45) throw new ErrorSalvamento("O nome da Classe, possuir mais que 45 caracteres");

        if (newClass.getDescriptionClass() != null) {
            if (newClass.getDescriptionClass().length() > 450) throw new ErrorSalvamento("A descriçaõ da class, possui mais que 450 caracteres");
        }

        classRepository.save(newClass);

        return newClass;
    }
}
