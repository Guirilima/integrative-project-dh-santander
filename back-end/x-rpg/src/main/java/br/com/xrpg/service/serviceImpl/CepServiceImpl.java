package br.com.xrpg.service.serviceImpl;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.xrpg.entity.CepEntity;
import br.com.xrpg.service.CepService;

@Service
public class CepServiceImpl implements CepService {

    @Override
    public CepEntity findByCep(String cepString) {

        RestTemplate template = new RestTemplate();

        //https://ws.apicep.com/cep/06233-030.json
        //https://ws.apicep.com/cep.json?code=06233-030
        //https://ws.apicep.com/cep.json?code=06233030

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("ws.apicep.com")
                .path("cep.json")
                .queryParam("code",cepString)
                .build();

        //ResponseEntity<CepEntity> entity2 = template.getForEntity(uri.toUriString(),CepEntity.class);

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

            ResponseEntity<CepEntity> response = restTemplate.exchange(uri.toUriString(), HttpMethod.GET,entity,CepEntity.class);

            return response.getBody();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
