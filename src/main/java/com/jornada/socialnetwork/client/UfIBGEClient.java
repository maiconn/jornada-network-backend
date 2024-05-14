package com.jornada.socialnetwork.client;

import com.jornada.socialnetwork.client.dto.response.CidadeIBGEResponseDTO;
import com.jornada.socialnetwork.client.dto.response.UfIBGEResponseDTO;
import com.jornada.socialnetwork.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@FeignClient(value = "UfIBGEService", url = "${client.ibge.url}", configuration = FeignConfig.class)
public interface UfIBGEClient {
    @RequestMapping(method = RequestMethod.GET, value = "/estados?orderBy=nome")
    List<UfIBGEResponseDTO> recuperarUFs();

    @RequestMapping(method = RequestMethod.GET, value = "/estados/{uf}")
    UfIBGEResponseDTO recuperarUF(@PathVariable("uf") Integer uf);

    @RequestMapping(method = RequestMethod.GET, value = "/estados/{uf}/municipios?orderBy=nome")
    List<CidadeIBGEResponseDTO> recuperarCidadesPorUf(@PathVariable("uf") Integer uf);

    @RequestMapping(method = RequestMethod.GET, value = "/municipios/{idMunicipio}")
    CidadeIBGEResponseDTO recuperarCidade(@PathVariable("idMunicipio") Integer idMunicipio);
}
