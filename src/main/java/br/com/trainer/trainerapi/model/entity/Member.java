package br.com.trainer.trainerapi.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_rdt_aluno")
@SequenceGenerator(name = "member", sequenceName = "sq_rdt_member", allocationSize = 1)
public class Member {

    @Id
    @Column(name = "cd_aluno")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member")
    private Integer id;

    @Column(name = "nm_aluno", length = 100, nullable = false)
    private String name;

    @Column(name = "nr_telefone", length = 15, nullable = false, unique = true)
    private String phone;

    @Column(name = "st_ativo", nullable = false)
    private Boolean active;

    @Column(name = "nr_sequencia_treinos", length = 4)
    private Integer trainingSequence;

    @ManyToOne
    @JoinColumn(name = "cd_trainer")
    private Trainer trainer;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Training> trainings;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TrainerClass> trainerClasses;

    public Member() {
        this.active = false;
        this.trainings = new ArrayList<>();
        this.trainerClasses = new ArrayList<>();
        this.trainingSequence = 0;
    }

    public Member(Integer id, String name, String phone) {
        this();
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
    public Member(String name, String phone, Trainer trainer) {
        this();
        this.name = name;
        this.phone = phone;
        this.trainer = trainer;
    }

    public void addTraining(Training training) {
        training.setMember(this);
        this.trainings.add(training);
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String cellphone) {
        this.phone = cellphone;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getTrainingSequence() {
        return trainingSequence;
    }

    public void setTrainingSequence(Integer trainingSequence) {
        this.trainingSequence = trainingSequence;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public List<TrainerClass> getTrainerClasses() {
        return trainerClasses;
    }

    public void setTrainerClasses(List<TrainerClass> trainerClasses) {
        this.trainerClasses = trainerClasses;
    }
}
