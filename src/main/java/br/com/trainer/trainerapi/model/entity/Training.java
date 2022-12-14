package br.com.trainer.trainerapi.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_rdt_treino")
@SequenceGenerator(name = "training", sequenceName = "sq_rdt_training", allocationSize = 1)
public class Training {

    @Id
    @Column(name = "cd_treino")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "training")
    private Integer id;

    @Column(name = "dt_treino", length = 2, nullable = false)
    private LocalDate trainingDay;

    @Column(name = "st_treino", nullable = false)
    private Boolean trainingStatus;

    @ManyToOne
    @JoinColumn(name = "cd_aluno")
    private Member member;

    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Exercise> exercises;

    public Training() {
        this.trainingStatus = false;
        this.exercises = new ArrayList<>();
    }

    public Training(Integer id, LocalDate trainingDay) {
        this();
        this.id = id;
        this.trainingDay = trainingDay;
    }

    public Training(LocalDate trainingDay, Boolean trainingStatus, Member member) {
        this();
        this.trainingDay = trainingDay;
        this.trainingStatus = trainingStatus;
        this.member = member;
    }

    public void addExercise(Exercise exercise) {
        exercise.setTraining(this);
        this.exercises.add(exercise);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getTrainingDay() {
        return trainingDay;
    }

    public void setTrainingDay(LocalDate trainingDay) {
        this.trainingDay = trainingDay;
    }

    public Boolean getTrainingStatus() {
        return trainingStatus;
    }

    public void setTrainingStatus(Boolean trainingStatus) {
        this.trainingStatus = trainingStatus;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
