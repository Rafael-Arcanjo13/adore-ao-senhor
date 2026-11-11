package com.adoreaosenhor.adore_ao_senhor.domain.music;

import com.adoreaosenhor.adore_ao_senhor.domain.instituicao.Institution;
import com.adoreaosenhor.adore_ao_senhor.domain.setlistMusic.SetlistMusic;
import com.adoreaosenhor.adore_ao_senhor.domain.userMusic.UserMusic;
import com.adoreaosenhor.adore_ao_senhor.dto.music.CreateMusicDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Table(name = "musics")
@Entity(name = "music")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String composer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tonality tone;

    @Column(name = "link_youtube")
    private String linkYoutube;

    @Column(name = "link_cifra_club")
    private String linkCifraClub;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "music")
    private Set<UserMusic> users = new HashSet<>();

    @OneToMany(mappedBy = "music")
    private Set<SetlistMusic> setlists = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id", nullable = false)
    private Institution institution;

    public Music(CreateMusicDTO data, Institution institution) {
        this.name = data.name();
        this.composer = data.composer();
        this.tone = data.tone();
        this.linkYoutube = data.linkYoutube();
        this.linkCifraClub = data.linkCifraClub();
        this.institution = institution;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public Tonality getTone() {
        return tone;
    }

    public void setTone(Tonality tone) {
        this.tone = tone;
    }

    public String getLinkYoutube() {
        return linkYoutube;
    }

    public void setLinkYoutube(String linkYoutube) {
        this.linkYoutube = linkYoutube;
    }

    public String getLinkCifraClub() {
        return linkCifraClub;
    }

    public void setLinkCifraClub(String linkCifraClub) {
        this.linkCifraClub = linkCifraClub;
    }
}
