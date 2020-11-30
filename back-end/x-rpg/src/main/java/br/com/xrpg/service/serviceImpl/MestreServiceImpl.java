package br.com.xrpg.service.serviceImpl;

import br.com.xrpg.entity.MestreEntity;
import br.com.xrpg.exceptions.ArgumentNotValid;
import br.com.xrpg.exceptions.ObjectNotFound;
import br.com.xrpg.repository.MestreRepository;
import br.com.xrpg.service.MestreService;
import br.com.xrpg.vo.GenericPageRequestResponse;
import br.com.xrpg.vo.HttpGenericPageableResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MestreServiceImpl implements MestreService {

    private final MestreRepository repository;

    public MestreEntity create(MestreEntity master) {

        if (repository.findByIdUsuario(master.getIdUsuario()) != null) {
            throw new RuntimeException("Esse Usuário já possui um mestre cadastrado.");
        }

        try {
            return this.repository.save(master);
        }catch (Exception e) {
            throw new RuntimeException("Erro durante o salvamento do novo mestre");
        }
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

    public HttpGenericPageableResponse findAll(int pagina,int qtdPagina) {

        PageRequest pageable = PageRequest.of(pagina,qtdPagina, Sort.by("idMestre").ascending());

        Page<MestreEntity> mestres = repository.findAll(pageable);

        HttpGenericPageableResponse resp = new HttpGenericPageableResponse();
        GenericPageRequestResponse pageRequest = new GenericPageRequestResponse(mestres.getNumber(),
                mestres.getSize(),mestres.getTotalElements(),mestres.getTotalPages(),
                mestres.getSort().toString());
        resp.setPageRequestResponse(pageRequest);
        resp.setData(mestres.getContent());

        return resp;
    }

    public void delete(BigInteger id) {
        this.findById(id);

        this.repository.deleteById(id);
    }

    public MestreEntity findByIdUsuario(BigInteger idUsuario) {

        MestreEntity mestreEntity = repository.findByIdUsuario(idUsuario);
        if (mestreEntity == null) {
            throw new RuntimeException("Não há mestre para esse Usuário.");
        }
        return mestreEntity;
    }
}
