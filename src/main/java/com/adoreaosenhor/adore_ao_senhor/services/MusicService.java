package com.adoreaosenhor.adore_ao_senhor.services;

import com.adoreaosenhor.adore_ao_senhor.domain.instituicao.Institution;
import com.adoreaosenhor.adore_ao_senhor.domain.music.Music;
import com.adoreaosenhor.adore_ao_senhor.domain.music.MusicRepository;
import com.adoreaosenhor.adore_ao_senhor.dto.music.CreateMusicDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

    public Music create(CreateMusicDTO data, Institution institution) {
        if (musicRepository.findByName(data.name()) != null && musicRepository.findByComposer(data.composer()) != null) {
            throw new RuntimeException("Música já cadastrada!");
        }

        Music music = new Music(data, institution);

        musicRepository.save(music);

        return music;
    }

}
