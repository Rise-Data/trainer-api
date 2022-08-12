package br.com.trainer.trainerapi.model.entity;

import javax.persistence.*;


@Entity
@Table(name = "tb_exercicio")
@SequenceGenerator(name = "exercise", sequenceName = "sq_exercise", allocationSize = 1)

public class Exercise {

    @Id
    @Column(name = "cd_exercicio")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exercise")
    private Integer Id;

    @Column(name = "nr_repeticoes", length = 3)
    private Integer repetitions;

    @Column(name = "ds_exercicio", nullable = false)
    private String description;

    @Column(name = "ds_link_video_demonstrativo")
    private String linkVideo;

    @Column(name = "nm_exercicio", length = 100, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "cd_treino")
    private Training training;

    @OneToOne
    @JoinColumn(name = "cd_tipo_exercicio")
    private TrainingType trainingType;

    public Exercise() {
    }

    public Exercise(Integer id, Integer repetitions, String description, String linkVideo, String name) {
        Id = id;
        this.repetitions = repetitions;
        this.description = description;
        this.linkVideo = linkVideo;
        this.name = name;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(Integer repetitions) {
        this.repetitions = repetitions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public TrainingType getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }
}
