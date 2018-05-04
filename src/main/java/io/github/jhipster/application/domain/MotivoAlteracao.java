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
 * A MotivoAlteracao.
 */
@Entity
@Table(name = "motivo_alteracao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MotivoAlteracao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(mappedBy = "motivoAlteracao")
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

    public MotivoAlteracao descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Contagem> getContagens() {
        return contagens;
    }

    public MotivoAlteracao contagens(Set<Contagem> contagems) {
        this.contagens = contagems;
        return this;
    }

    public MotivoAlteracao addContagens(Contagem contagem) {
        this.contagens.add(contagem);
        contagem.setMotivoAlteracao(this);
        return this;
    }

    public MotivoAlteracao removeContagens(Contagem contagem) {
        this.contagens.remove(contagem);
        contagem.setMotivoAlteracao(null);
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
        MotivoAlteracao motivoAlteracao = (MotivoAlteracao) o;
        if (motivoAlteracao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), motivoAlteracao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MotivoAlteracao{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            "}";
    }
}
