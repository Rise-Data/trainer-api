package br.com.trainer.trainerapi.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_rdt_aula")
@SequenceGenerator(name = "aula", sequenceName = "sq_rdt_aula")
public class TrainerClass {
    @Id
    @Column(name = "cd_aula")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aula")
    private Integer id;

    @Column(name = "dt_aula", nullable = false)
    private LocalDateTime date;

    @Column(name = "st_aula", nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "cd_trainer")
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "cd_member")
    private Member member;

    public TrainerClass() {
    }

    public TrainerClass(LocalDateTime date, Boolean status, Trainer trainer, Member member) {
        this.date = date;
        this.status = status;
        this.trainer = trainer;
        this.member = member;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
