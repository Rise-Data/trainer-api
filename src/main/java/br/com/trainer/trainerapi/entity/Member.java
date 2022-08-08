package br.com.trainer.trainerapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_aluno")
@SequenceGenerator(name = "member", sequenceName = "sq_member", allocationSize = 1)
public class Member {

    @Id
    @Column(name = "cd_aluno")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member")
    private Integer id;

    @Column(name = "nm_aluno", length = 100, nullable = false)
    private String name;

    @Column(name = "nr_telefone", length = 15, nullable = false)
    private String cellphone;

    @Column(name = "st_ativo", length = 1, nullable = false)
    private Boolean active;

    @Column(name = "nr_sequencia_treinos", length = 4, nullable = true)
    private Integer trainingSequence;

    public Member() {
    }

    public Member(Integer id, String name, String cellphone) {
        this.id = id;
        this.name = name;
        this.cellphone = cellphone;
        this.active = true;
        this.trainingSequence = 0;
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

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
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
}