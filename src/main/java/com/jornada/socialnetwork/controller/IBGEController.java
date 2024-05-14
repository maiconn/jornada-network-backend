package com.jornada.socialnetwork.controller;

import com.jornada.socialnetwork.client.UfIBGEClient;
import com.jornada.socialnetwork.client.dto.response.CidadeIBGEResponseDTO;
import com.jornada.socialnetwork.client.dto.response.UfIBGEResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ibge")
@RequiredArgsConstructor
public class IBGEController {
    private final UfIBGEClient ufIBGEClient;

    @GetMapping("/uf")
    public List<UfIBGEResponseDTO> recuperarUFs()  {
        return ufIBGEClient.recuperarUFs();
    }

    @GetMapping("/uf/{ufId}")
    public UfIBGEResponseDTO recuperarUF(@PathVariable Integer ufId)  {
        return ufIBGEClient.recuperarUF(ufId);
    }

    @GetMapping("/{ufId}/cidades")
    public List<CidadeIBGEResponseDTO> recuperarCidadesPorEstado(@PathVariable Integer ufId)  {
        return ufIBGEClient.recuperarCidadesPorUf(ufId);
    }

    @GetMapping("/cidade/{cidadeId}")
    public CidadeIBGEResponseDTO recuperarCidade(@PathVariable Integer cidadeId)  {
        return ufIBGEClient.recuperarCidade(cidadeId);
    }
}
