package com.adoreaosenhor.adore_ao_senhor.domain.schedule;

import com.adoreaosenhor.adore_ao_senhor.domain.scheduleUser.ScheduleUser;
import com.adoreaosenhor.adore_ao_senhor.domain.setlist.Setlist;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Table(name = "schedules")
@Entity(name = "schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date_event", nullable = false)
    private LocalDateTime dateEvent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "setlist_id")
    private Setlist setlist;

    @OneToMany(mappedBy = "schedule")
    private Set<ScheduleUser> members = new HashSet<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Boolean active;


}
