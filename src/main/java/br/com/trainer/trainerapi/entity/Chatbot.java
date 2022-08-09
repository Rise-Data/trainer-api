package br.com.trainer.trainerapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_chatbot")
@SequenceGenerator(name = "chatbot", sequenceName = "sq_chatbot", allocationSize = 1)
public class Chatbot {

    @Id
    @Column(name = "cd_chatbot")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chatbot")
    private Integer id;

    @Column(name = "nm_chatbot", length = 100, nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "cd_trainer")
    private Trainer trainer;

    public Chatbot() {

    }

    public Chatbot(Integer id, String name) {
        this.id = id;
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

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public String toString() {
        return "Chatbot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
