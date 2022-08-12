package br.com.trainer.trainerapi.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_trainer")
@SequenceGenerator(name = "trainer", sequenceName = "sq_trainer", allocationSize = 1)
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trainer")
    @Column(name = "cd_trainer")
    private Integer id;

    @Column(name = "nm_usuario", length = 100)
    private String user;

    @Column(name = "ds_senha", length = 8)
    private String password;

    @Column(name = "ds_email", length = 100, unique = true)
    private String email;

    @Column(name = "nr_cpf", length = 11, unique = true)
    private String cpf;

    @Column(name = "nr_telefone", length = 15, unique = true)
    private String phone;

    @OneToOne(mappedBy = "trainer")
    private Chatbot chatbot;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Member> members;

    public Trainer() {
        this.members = new ArrayList<>();
    }

    public Trainer(Integer id, String user, String password, String email, String cpf, String phone) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.members = new ArrayList<>();
    }

    public void addMember(Member member) {
        member.setTrainer(this);
        this.members.add(member);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Chatbot getChatbot() {
        return chatbot;
    }

    public void setChatbot(Chatbot chatbot) {
        this.chatbot = chatbot;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
