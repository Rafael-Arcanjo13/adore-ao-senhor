package com.adoreaosenhor.adore_ao_senhor.controller;

import com.adoreaosenhor.adore_ao_senhor.domain.instituicao.Institution;
import com.adoreaosenhor.adore_ao_senhor.domain.music.Music;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.User;
import com.adoreaosenhor.adore_ao_senhor.dto.music.CreateMusicDTO;
import com.adoreaosenhor.adore_ao_senhor.dto.music.ReadMusicDTO;
import com.adoreaosenhor.adore_ao_senhor.services.MusicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @PostMapping("/create")
    public ResponseEntity<ReadMusicDTO> create(@RequestBody @Valid CreateMusicDTO data, @AuthenticationPrincipal User authenticatedUser) {
        Institution institution = authenticatedUser.getInstituicao();

        Music music = musicService.create(data, institution);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ReadMusicDTO(music));

    }

}
