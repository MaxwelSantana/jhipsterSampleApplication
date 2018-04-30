package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Mapeamento.
 */
@Entity
@Table(name = "mapeamento")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Mapeamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "secao_inicial")
    private String secaoInicial;

    @Column(name = "secao_final")
    private String secaoFinal;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "deposito")
    private Boolean deposito;

    @Column(name = "loja")
    private Boolean loja;

    @Column(name = "inicial_int")
    private Integer inicialInt;

    @Column(name = "final_int")
    private Integer finalInt;

    @OneToOne
    @JoinColumn(unique = true)
    private Inventario inventario;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSecaoInicial() {
        return secaoInicial;
    }

    public Mapeamento secaoInicial(String secaoInicial) {
        this.secaoInicial = secaoInicial;
        return this;
    }

    public void setSecaoInicial(String secaoInicial) {
        this.secaoInicial = secaoInicial;
    }

    public String getSecaoFinal() {
        return secaoFinal;
    }

    public Mapeamento secaoFinal(String secaoFinal) {
        this.secaoFinal = secaoFinal;
        return this;
    }

    public void setSecaoFinal(String secaoFinal) {
        this.secaoFinal = secaoFinal;
    }

    public String getDescricao() {
        return descricao;
    }

    public Mapeamento descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean isDeposito() {
        return deposito;
    }

    public Mapeamento deposito(Boolean deposito) {
        this.deposito = deposito;
        return this;
    }

    public void setDeposito(Boolean deposito) {
        this.deposito = deposito;
    }

    public Boolean isLoja() {
        return loja;
    }

    public Mapeamento loja(Boolean loja) {
        this.loja = loja;
        return this;
    }

    public void setLoja(Boolean loja) {
        this.loja = loja;
    }

    public Integer getInicialInt() {
        return inicialInt;
    }

    public Mapeamento inicialInt(Integer inicialInt) {
        this.inicialInt = inicialInt;
        return this;
    }

    public void setInicialInt(Integer inicialInt) {
        this.inicialInt = inicialInt;
    }

    public Integer getFinalInt() {
        return finalInt;
    }

    public Mapeamento finalInt(Integer finalInt) {
        this.finalInt = finalInt;
        return this;
    }

    public void setFinalInt(Integer finalInt) {
        this.finalInt = finalInt;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public Mapeamento inventario(Inventario inventario) {
        this.inventario = inventario;
        return this;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
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
        Mapeamento mapeamento = (Mapeamento) o;
        if (mapeamento.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mapeamento.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Mapeamento{" +
            "id=" + getId() +
            ", secaoInicial='" + getSecaoInicial() + "'" +
            ", secaoFinal='" + getSecaoFinal() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", deposito='" + isDeposito() + "'" +
            ", loja='" + isLoja() + "'" +
            ", inicialInt=" + getInicialInt() +
            ", finalInt=" + getFinalInt() +
            "}";
    }
}
