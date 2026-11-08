package com.adoreaosenhor.adore_ao_senhor.domain.setlistMusic;

import com.adoreaosenhor.adore_ao_senhor.domain.music.Music;
import com.adoreaosenhor.adore_ao_senhor.domain.setlist.Setlist;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Table(
        name = "setlists_musics",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_setlist_music",
                        columnNames = {"setlist_id", "music_id"}
                )
        }
)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SetlistMusic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "setlist_id", nullable = false)
    private Setlist setlist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_id", nullable = false)
    private Music music;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "execution_order", nullable = false)
    private Integer executionOrder;

    private String observation;

    @Column(nullable = false)
    private Boolean active;

    public SetlistMusic(Setlist setlist, Music music, LocalDateTime createdAt, LocalDateTime updatedAt, Integer executionOrder, String observation, Boolean active) {
        this.setlist = setlist;
        this.music = music;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.executionOrder = executionOrder;
        this.observation = observation;
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Setlist getSetlist() {
        return setlist;
    }

    public void setSetlist(Setlist setlist) {
        this.setlist = setlist;
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

    public Integer getExecutionOrder() {
        return executionOrder;
    }

    public void setExecutionOrder(Integer executionOrder) {
        this.executionOrder = executionOrder;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
