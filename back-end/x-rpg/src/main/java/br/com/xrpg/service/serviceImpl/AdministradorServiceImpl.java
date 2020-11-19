package br.com.xrpg.service.serviceImpl;

import br.com.xrpg.entity.ClasseEntity;
import br.com.xrpg.entity.RacaEntity;
import br.com.xrpg.entity.UsuarioEntity;
import br.com.xrpg.repository.ClasseRepository;
import br.com.xrpg.repository.RacaRepository;
import br.com.xrpg.repository.UsuarioRepository;
import br.com.xrpg.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RacaRepository racaRepository;

    @Autowired
    ClasseRepository classeRepository;

    //HashMap Temporario de retorno de dados
    public HashMap<String,Object> getResumoDashBoardAdm() {
        HashMap<String,Object> dadosReturn = new HashMap<>();

        List<UsuarioEntity> usuariosAtivos = usuarioRepository.getAllUsuariosAtivos(new BigInteger("1"));//1 = ATIVOS - 0 = INATIVOS
        List<UsuarioEntity> usuariosInativos = usuarioRepository.getAllUsuariosAtivos(new BigInteger("0"));//0 = INATIVOS

        if (usuariosAtivos != null && !usuariosAtivos.isEmpty()) {
            dadosReturn.put("TotalUsuariosAtivos",usuariosAtivos.size()); //FIXME - QUEBRAR POR UMA QUERY ByAllUsers
            dadosReturn.put("TotalUsuariosInativos",usuariosInativos.size());
            dadosReturn.put("UsuariosInativos",usuariosInativos);
            dadosReturn.put("UsuariosAtivos",usuariosAtivos);
            //TRAZER MÈDIA IDADE USUARIOS
            BigInteger mediaIdade = usuarioRepository.getIdadeMediaUsuarios(new BigInteger("1"));
            if (mediaIdade != null && mediaIdade.intValue() > 0 ) {
                dadosReturn.put("MediaIdadeUsuarios",mediaIdade);
            }
        }
        //FIND BY RAÇA
        List<RacaEntity> racasBD = racaRepository.getAllRaca();
        if (racasBD != null && !racasBD.isEmpty()) {
            dadosReturn.put("TotalRacas",racasBD.size());
            dadosReturn.put("RacasExistentes",racasBD);
        }
        //FIND BY CLASSE
        List<ClasseEntity> classesBD = classeRepository.getAllClasses();
        if (classesBD != null && !classesBD.isEmpty()) {
            dadosReturn.put("TotalClasses",classesBD.size());
            dadosReturn.put("ClassesExistentes",classesBD);
        }

        //FIND BY USUARIOS POR ENDEREÇO
        List<Object> usurEstadosBD = usuarioRepository.getCountUsuariosPorEstados();

        if (usurEstadosBD != null && !usurEstadosBD.isEmpty()) {
            dadosReturn.put("UsuariosPorEstado",usurEstadosBD);
        }

        return dadosReturn;
    }



}
