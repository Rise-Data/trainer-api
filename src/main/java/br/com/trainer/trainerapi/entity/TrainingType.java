package br.com.trainer.trainerapi.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_tipo_exercicio")
@SequenceGenerator(name = "tipo_exercicio", sequenceName = "sq_member", allocationSize = 1)
public class TrainingType {

    @Id
    @Column(name = "cd_tipo_exercicio")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_exercicio")
    private Integer id;

    @Column(name = "nm_tipo_exercicio", length = 2, nullable = false)
    private LocalDate trainingDay;

    @OneToOne(mappedBy = "trainingType", cascade = CascadeType.ALL)
    private Exercise exercise;

    public TrainingType() {
    }

    public TrainingType(Integer id, LocalDate trainingDay) {
        this.id = id;
        this.trainingDay = trainingDay;
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

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
