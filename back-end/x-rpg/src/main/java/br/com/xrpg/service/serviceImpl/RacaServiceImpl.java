package br.com.xrpg.service.serviceImpl;

import java.util.Optional;

import br.com.xrpg.vo.GenericPageRequestResponse;
import br.com.xrpg.vo.HttpGenericPageableResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.xrpg.entity.RacaEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.repository.RacaRepository;
import br.com.xrpg.service.RacaService;

@Service
@AllArgsConstructor
public class RacaServiceImpl implements RacaService {

    private final RacaRepository racaRepository;

    @Override
    public HttpGenericPageableResponse getListaRacas(int pagina,int qtdPagina) {

        PageRequest pageable = PageRequest.of(pagina,qtdPagina, Sort.by("nomeRaca").ascending());

        Page<RacaEntity> racas = racaRepository.findAll(pageable);

        HttpGenericPageableResponse resp = new HttpGenericPageableResponse();
        GenericPageRequestResponse pageRequest = new GenericPageRequestResponse(racas.getNumber(),
                racas.getSize(),racas.getTotalElements(),racas.getTotalPages(),
                racas.getSort().toString());
        resp.setPageRequestResponse(pageRequest);
        resp.setData(racas.getContent());

        return resp;
    }

    @Override
    public RacaEntity inclusaoRaca(RacaEntity newRaca) throws ErrorSalvamento{
        Optional
                .ofNullable(newRaca.getNomeRaca())
                .orElseThrow( () -> new ErrorSalvamento("O nome da raça não pode ser nulo"));

        if (newRaca.getNomeRaca().length() > 45) throw new ErrorSalvamento("O nome da raça, possuir mais que 45 caracteres");

        if (newRaca.getDescricaoRaca() != null) {
            if (newRaca.getDescricaoRaca().length() > 450) throw new ErrorSalvamento("A descriçaõ da raça, possui mais que 450 caracteres");
        }

        racaRepository.save(newRaca);

        return newRaca;
    }
}
