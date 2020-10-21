package br.com.xrpg.service.serviceImpl;

import br.com.xrpg.entity.MestreEntity;
import br.com.xrpg.exceptions.ArgumentNotValid;
import br.com.xrpg.exceptions.ObjectNotFound;
import br.com.xrpg.repository.MestreRepository;
import br.com.xrpg.service.MestreService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class MestreServiceImpl implements MestreService {

    private MestreRepository repository;

    public MestreEntity create(MestreEntity master) {

        return this.repository.save(master);
    }

    public MestreEntity update(MestreEntity master) {
        findById(master.getIdMestre());

        return this.repository.save(master);
    }

    public MestreEntity findById(BigInteger id) {

        Optional.ofNullable(id).orElseThrow(() -> new ArgumentNotValid("Id não pode ser nulo"));

        return this.repository.findById(id)
                .orElseThrow(() -> new ObjectNotFound("Não foi possivel encontrar o mestre de id: " + id));
    }

    public List<MestreEntity> findAll() {
        return this.repository.findAll();
    }

    public void delete(BigInteger id) {
        this.findById(id);

        this.repository.deleteById(id);
    }
}
