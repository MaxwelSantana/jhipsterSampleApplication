package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Secao.
 */
@Entity
@Table(name = "secao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Secao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @OneToOne
    @JoinColumn(unique = true)
    private Transmissao transmissao;

    @OneToOne
    @JoinColumn(unique = true)
    private StatusContagem statusContagem;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public Secao codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Transmissao getTransmissao() {
        return transmissao;
    }

    public Secao transmissao(Transmissao transmissao) {
        this.transmissao = transmissao;
        return this;
    }

    public void setTransmissao(Transmissao transmissao) {
        this.transmissao = transmissao;
    }

    public StatusContagem getStatusContagem() {
        return statusContagem;
    }

    public Secao statusContagem(StatusContagem statusContagem) {
        this.statusContagem = statusContagem;
        return this;
    }

    public void setStatusContagem(StatusContagem statusContagem) {
        this.statusContagem = statusContagem;
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
        Secao secao = (Secao) o;
        if (secao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), secao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Secao{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            "}";
    }
}
