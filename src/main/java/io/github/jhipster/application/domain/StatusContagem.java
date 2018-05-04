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
 * A StatusContagem.
 */
@Entity
@Table(name = "status_contagem")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StatusContagem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(mappedBy = "statusContagem")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Secao> secoes = new HashSet<>();

    @OneToMany(mappedBy = "statusContagem")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Contagem> contagens = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public StatusContagem descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Secao> getSecoes() {
        return secoes;
    }

    public StatusContagem secoes(Set<Secao> secaos) {
        this.secoes = secaos;
        return this;
    }

    public StatusContagem addSecoes(Secao secao) {
        this.secoes.add(secao);
        secao.setStatusContagem(this);
        return this;
    }

    public StatusContagem removeSecoes(Secao secao) {
        this.secoes.remove(secao);
        secao.setStatusContagem(null);
        return this;
    }

    public void setSecoes(Set<Secao> secaos) {
        this.secoes = secaos;
    }

    public Set<Contagem> getContagens() {
        return contagens;
    }

    public StatusContagem contagens(Set<Contagem> contagems) {
        this.contagens = contagems;
        return this;
    }

    public StatusContagem addContagens(Contagem contagem) {
        this.contagens.add(contagem);
        contagem.setStatusContagem(this);
        return this;
    }

    public StatusContagem removeContagens(Contagem contagem) {
        this.contagens.remove(contagem);
        contagem.setStatusContagem(null);
        return this;
    }

    public void setContagens(Set<Contagem> contagems) {
        this.contagens = contagems;
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
        StatusContagem statusContagem = (StatusContagem) o;
        if (statusContagem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), statusContagem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StatusContagem{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            "}";
    }
}
