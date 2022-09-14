package br.com.trainer.trainerapi.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_rdt_tipo_exercicio")
@SequenceGenerator(name = "tipo_exercicio", sequenceName = "sq_rdt_member", allocationSize = 1)
public class ExerciseType {

    @Id
    @Column(name = "cd_tipo_exercicio")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_exercicio")
    private Integer id;

    @Column(name = "nm_tipo_exercicio", length = 100, nullable = false)
    private String name;

    @OneToMany(mappedBy = "exerciseType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Exercise> exercises;

    public ExerciseType() {
    }

    public ExerciseType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public ExerciseType(String name) {
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

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
