package com.adoreaosenhor.adore_ao_senhor.controller;

import com.adoreaosenhor.adore_ao_senhor.domain.instituicao.Institution;
import com.adoreaosenhor.adore_ao_senhor.domain.music.Music;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.User;
import com.adoreaosenhor.adore_ao_senhor.dto.music.CreateMusicDTO;
import com.adoreaosenhor.adore_ao_senhor.dto.music.ReadMusicDTO;
import com.adoreaosenhor.adore_ao_senhor.services.MusicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @PostMapping("/create")
    public ResponseEntity<ReadMusicDTO> create(@RequestBody @Valid CreateMusicDTO data, @AuthenticationPrincipal User authenticatedUser) {
        ReadMusicDTO music = musicService.create(data, authenticatedUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(music);

    }

    @PostMapping("/{musicId}/members")
    public ResponseEntity<Void> addMusicPersonalCatalog(@PathVariable Long musicId, @AuthenticationPrincipal User authenticatedUser) {
        musicService.addMusicPersonalCatalog(musicId, authenticatedUser);

        return ResponseEntity.noContent().build();

    }


    @GetMapping
    public ResponseEntity<Page<ReadMusicDTO>> read(@AuthenticationPrincipal User authenticatedUser, Pageable pageable) {
        Institution institution = authenticatedUser.getInstituicao();
        Page<ReadMusicDTO> musics = musicService.read(institution, pageable);

        return ResponseEntity.ok(musics);
    }

    @GetMapping("/users/me/musics")
    public ResponseEntity<Page<ReadMusicDTO>> findMyMusics(@AuthenticationPrincipal User authenticatedUser, Pageable pageable) {
        Page<ReadMusicDTO> musics = musicService.findMyMusics(authenticatedUser, pageable);

        return ResponseEntity.ok(musics);
    }

    @GetMapping("/{userId}/musics")
    public ResponseEntity<Page<ReadMusicDTO>> findMusicsByUser(@PathVariable Long userId, @AuthenticationPrincipal User authenticatedUser, Pageable pageable) {
        Page<ReadMusicDTO> musics = musicService.findMusicsByUser(userId, authenticatedUser, pageable);

        return ResponseEntity.ok(musics);
    }

}
