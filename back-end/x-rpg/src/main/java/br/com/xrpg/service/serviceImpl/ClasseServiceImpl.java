package br.com.xrpg.service.serviceImpl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import br.com.xrpg.entity.RacaEntity;
import br.com.xrpg.exceptions.ArgumentNotValid;
import br.com.xrpg.exceptions.ObjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xrpg.entity.ClasseEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.repository.ClasseRepository;
import br.com.xrpg.service.ClasseService;

@Service
public class ClasseServiceImpl implements ClasseService {

    @Autowired
    ClasseRepository classeRepository;

    @Override
    public Iterable<ClasseEntity> getListaClasses() {

        return classeRepository.findAll();
    }

    @Override
    public ClasseEntity inclusaoClasse(ClasseEntity newClass) throws ErrorSalvamento {
        Optional
                .ofNullable(newClass.getNomeClasse())
                .orElseThrow( () -> new ErrorSalvamento("O nome da Classe não pode ser nulo"));

        if (newClass.getNomeClasse().length() > 45) throw new ErrorSalvamento("O nome da Classe, possuir mais que 45 caracteres");

        if (newClass.getDescricaoClasse() != null) {
            if (newClass.getDescricaoClasse().length() > 450) throw new ErrorSalvamento("A descriçaõ da class, possui mais que 450 caracteres");
        }

        classeRepository.save(newClass);

        return newClass;
    }

    @Override
    public ClasseEntity encontrarPorId(BigInteger idClasse) throws ObjectNotFound {
        Optional
                .ofNullable(idClasse)
                .orElseThrow( () -> new ArgumentNotValid("O id informado não é valido"));

        return this.classeRepository.findById(idClasse)
                .orElseThrow(() -> new ObjectNotFound("Classe não encontrada"));
    }

    @Override
    public List<ClasseEntity> getListaClassesSemPaginacao() {
        return this.classeRepository.getAllClasses();
    }

    @Override
    public void deletar(BigInteger idClasse) {

        Optional
                .ofNullable( this.encontrarPorId(idClasse) )
                .orElseThrow( () -> new ArgumentNotValid("O id informado não é valido"));

        this.classeRepository.deleteById(idClasse);

    }
}
