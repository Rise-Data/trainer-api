package br.com.trainer.trainerapi.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_tipo_exercicio")
@SequenceGenerator(name = "tipo_exercicio", sequenceName = "sq_member", allocationSize = 1)
public class TrainingType {

    @Id
    @Column(name = "cd_tipo_exercicio")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_exercicio")
    private Integer id;

    @Column(name = "nm_tipo_exercicio", length = 2, nullable = false)
    private String name;

    @OneToOne(mappedBy = "trainingType", cascade = CascadeType.ALL)
    private Exercise exercise;

    public TrainingType() {
    }

    public TrainingType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public TrainingType(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
