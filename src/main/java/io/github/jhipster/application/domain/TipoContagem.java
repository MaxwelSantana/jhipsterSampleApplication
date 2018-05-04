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
 * A TipoContagem.
 */
@Entity
@Table(name = "tipo_contagem")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TipoContagem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(mappedBy = "tipoContagem")
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

    public String getDescricao() {
        return descricao;
    }

    public TipoContagem descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Transmissao> getTrasmissoes() {
        return trasmissoes;
    }

    public TipoContagem trasmissoes(Set<Transmissao> transmissaos) {
        this.trasmissoes = transmissaos;
        return this;
    }

    public TipoContagem addTrasmissoes(Transmissao transmissao) {
        this.trasmissoes.add(transmissao);
        transmissao.setTipoContagem(this);
        return this;
    }

    public TipoContagem removeTrasmissoes(Transmissao transmissao) {
        this.trasmissoes.remove(transmissao);
        transmissao.setTipoContagem(null);
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
        TipoContagem tipoContagem = (TipoContagem) o;
        if (tipoContagem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipoContagem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TipoContagem{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            "}";
    }
}
