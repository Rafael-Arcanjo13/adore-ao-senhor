package com.adoreaosenhor.adore_ao_senhor.dto.music;

import com.adoreaosenhor.adore_ao_senhor.domain.music.Tonality;

public record CreateMusicDTO(String name, String composer, Tonality tone, String linkYoutube, String linkCifraClub) {

}
