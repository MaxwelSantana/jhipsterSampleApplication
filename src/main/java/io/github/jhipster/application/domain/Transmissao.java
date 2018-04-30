package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Transmissao.
 */
@Entity
@Table(name = "transmissao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Transmissao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_stamp")
    private Instant timeStamp;

    @Column(name = "versao_coletor")
    private String versaoColetor;

    @OneToOne
    @JoinColumn(unique = true)
    private Inventario inventario;

    @OneToOne
    @JoinColumn(unique = true)
    private Funcionario funcionario;

    @OneToOne
    @JoinColumn(unique = true)
    private Coletor coletor;

    @OneToOne
    @JoinColumn(unique = true)
    private TipoContagem tipoContagem;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public Transmissao timeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getVersaoColetor() {
        return versaoColetor;
    }

    public Transmissao versaoColetor(String versaoColetor) {
        this.versaoColetor = versaoColetor;
        return this;
    }

    public void setVersaoColetor(String versaoColetor) {
        this.versaoColetor = versaoColetor;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public Transmissao inventario(Inventario inventario) {
        this.inventario = inventario;
        return this;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Transmissao funcionario(Funcionario funcionario) {
        this.funcionario = funcionario;
        return this;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Coletor getColetor() {
        return coletor;
    }

    public Transmissao coletor(Coletor coletor) {
        this.coletor = coletor;
        return this;
    }

    public void setColetor(Coletor coletor) {
        this.coletor = coletor;
    }

    public TipoContagem getTipoContagem() {
        return tipoContagem;
    }

    public Transmissao tipoContagem(TipoContagem tipoContagem) {
        this.tipoContagem = tipoContagem;
        return this;
    }

    public void setTipoContagem(TipoContagem tipoContagem) {
        this.tipoContagem = tipoContagem;
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
        Transmissao transmissao = (Transmissao) o;
        if (transmissao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transmissao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Transmissao{" +
            "id=" + getId() +
            ", timeStamp='" + getTimeStamp() + "'" +
            ", versaoColetor='" + getVersaoColetor() + "'" +
            "}";
    }
}
