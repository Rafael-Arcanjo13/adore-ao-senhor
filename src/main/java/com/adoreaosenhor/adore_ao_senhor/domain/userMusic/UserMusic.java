package com.adoreaosenhor.adore_ao_senhor.domain.userMusic;

import com.adoreaosenhor.adore_ao_senhor.domain.music.Music;
import com.adoreaosenhor.adore_ao_senhor.domain.music.Tonality;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Table(
        name = "users_musics",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_user_music",
                        columnNames = {"user_id", "music_id"}
                )
        }
)
@Entity
public class UserMusic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_id", nullable = false)
    private Music music;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tonality tone_of_user;

    @Column(nullable = false)
    private Boolean active;

    public UserMusic(User user, Music music, LocalDateTime createdAt, LocalDateTime updatedAt, Tonality tone_of_user, Boolean active) {
        this.user = user;
        this.music = music;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.tone_of_user = tone_of_user;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Tonality getTone_of_user() {
        return tone_of_user;
    }

    public void setTone_of_user(Tonality tone_of_user) {
        this.tone_of_user = tone_of_user;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
