package com.adoreaosenhor.adore_ao_senhor.services;

import com.adoreaosenhor.adore_ao_senhor.domain.instituicao.Institution;
import com.adoreaosenhor.adore_ao_senhor.domain.music.Music;
import com.adoreaosenhor.adore_ao_senhor.domain.music.MusicRepository;
import com.adoreaosenhor.adore_ao_senhor.domain.userMusic.UserMusic;
import com.adoreaosenhor.adore_ao_senhor.domain.userMusic.UserMusicRepository;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.User;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.UserRepository;
import com.adoreaosenhor.adore_ao_senhor.dto.music.CreateMusicDTO;
import com.adoreaosenhor.adore_ao_senhor.dto.music.ReadMusicDTO;
import com.adoreaosenhor.adore_ao_senhor.infra.exceptions.DuplicateMusicException;
import com.adoreaosenhor.adore_ao_senhor.infra.exceptions.InstitutionMemberNotFoundException;
import com.adoreaosenhor.adore_ao_senhor.infra.exceptions.NonExistentMusicException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private UserMusicRepository userMusicRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ReadMusicDTO create(CreateMusicDTO data, User authenticatedUser) {

        Long institutionId = authenticatedUser.getInstituicao().getId();

        Music music = musicRepository.findByInstitutionIdAndNameIgnoreCaseAndComposerIgnoreCase(
                institutionId,
                data.name(),
                data.composer()
        ).orElse(null);

        if (music == null) {
            Music newMusic = new Music(data, authenticatedUser.getInstituicao());
            music = musicRepository.save(newMusic);
        }

        boolean alreadyAssociated = userMusicRepository.existsByUserIdAndMusicId(authenticatedUser.getId(), music.getId());

        if (!alreadyAssociated) {
            UserMusic userMusic = new UserMusic(authenticatedUser, music);
            userMusicRepository.save(userMusic);
        }

        return new ReadMusicDTO(music);
    }

    @Transactional
    public void addMusicPersonalCatalog(Long musicId, User authenticatedUser) {

        Long institutionId = authenticatedUser.getInstituicao().getId();

        Music music = musicRepository
                .findByIdAndInstitutionId(musicId, institutionId)
                .orElseThrow(() -> new NonExistentMusicException(
                        "Música não encontrada ou não pertence à sua instituição."
                ));

        boolean alreadyAssociated = userMusicRepository.existsByUserIdAndMusicId(authenticatedUser.getId(), musicId);

        if (alreadyAssociated) {
            throw new DuplicateMusicException(
                    "Essa música já está no seu catálogo pessoal."
            );
        }

        UserMusic userMusic = new UserMusic(authenticatedUser, music);

        userMusicRepository.save(userMusic);
    }

    @Transactional
    public Page<ReadMusicDTO> read(Institution institution, Pageable pageable) {

        return musicRepository.findByInstitutionId(institution.getId(), pageable)
                .map(ReadMusicDTO::new);

    }

    @Transactional
    public Page<ReadMusicDTO> findMyMusics(User authenticatedUser, Pageable pageable) {
        // Criar Get para o proprio usuario consultar suas proprias músicas

        Page<UserMusic> userMusics =
                userMusicRepository.findByUserIdAndActiveTrue(
                        authenticatedUser.getId(),
                        pageable
                );

        return userMusics.map(userMusic ->
                new ReadMusicDTO(userMusic.getMusic())
        );

    }

    @Transactional
    public Page<ReadMusicDTO> findMusicsByUser(Long userId, User authenticatedUser, Pageable pageable) {
        // Criar Get para o Adm/Lider pode consultar as músicas dos integrandes da equipe

        Long institutionId = authenticatedUser.getInstituicao().getId();

        User user = userRepository
                .findByIdAndInstitutionId(userId, institutionId)
                .orElseThrow(() -> new InstitutionMemberNotFoundException(
                        "Integrante não encontrado na sua instituição."
                ));

        Page<UserMusic> userMusics =
                userMusicRepository.findActiveMusicsByUserAndInstitution(
                        user.getId(),
                        institutionId,
                        pageable
                );

        return userMusics.map(userMusic ->
                new ReadMusicDTO(userMusic.getMusic())
        );

        // garantir também que as músicas retornadas pertençam à mesma instituição.
        // Você pode fazer isso na própria consulta JPQL, relacionando UserMusic, User e Music.

    }

}
