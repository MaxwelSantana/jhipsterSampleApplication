package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Funcionario.
 */
@Entity
@Table(name = "funcionario")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @OneToMany(mappedBy = "funcionario")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Inventario> inventarios = new HashSet<>();

    @OneToMany(mappedBy = "funcionario")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Transmissao> trasmissoes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Funcionario nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Funcionario cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Set<Inventario> getInventarios() {
        return inventarios;
    }

    public Funcionario inventarios(Set<Inventario> inventarios) {
        this.inventarios = inventarios;
        return this;
    }

    public Funcionario addInventarios(Inventario inventario) {
        this.inventarios.add(inventario);
        inventario.setFuncionario(this);
        return this;
    }

    public Funcionario removeInventarios(Inventario inventario) {
        this.inventarios.remove(inventario);
        inventario.setFuncionario(null);
        return this;
    }

    public void setInventarios(Set<Inventario> inventarios) {
        this.inventarios = inventarios;
    }

    public Set<Transmissao> getTrasmissoes() {
        return trasmissoes;
    }

    public Funcionario trasmissoes(Set<Transmissao> transmissaos) {
        this.trasmissoes = transmissaos;
        return this;
    }

    public Funcionario addTrasmissoes(Transmissao transmissao) {
        this.trasmissoes.add(transmissao);
        transmissao.setFuncionario(this);
        return this;
    }

    public Funcionario removeTrasmissoes(Transmissao transmissao) {
        this.trasmissoes.remove(transmissao);
        transmissao.setFuncionario(null);
        return this;
    }

    public void setTrasmissoes(Set<Transmissao> transmissaos) {
        this.trasmissoes = transmissaos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Funcionario funcionario = (Funcionario) o;
        if (funcionario.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), funcionario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Funcionario{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", cpf='" + getCpf() + "'" +
            "}";
    }
}
