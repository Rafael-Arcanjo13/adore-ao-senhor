package com.adoreaosenhor.adore_ao_senhor.dto.music;

import com.adoreaosenhor.adore_ao_senhor.domain.music.Music;
import com.adoreaosenhor.adore_ao_senhor.domain.music.Tonality;

public record ReadMusicDTO(Long id, String name, String composer, Tonality tone, String linkYoutube, String linkCifraClub) {

    public ReadMusicDTO(Music music) {
        this (
                music.getId(),
                music.getName(),
                music.getComposer(),
                music.getTone(),
                music.getLinkYoutube(),
                music.getLinkCifraClub()
        );

    }
}
