package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ConfiguracaoColetor.
 */
@Entity
@Table(name = "configuracao_coletor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ConfiguracaoColetor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private Cliente cliente;

    @OneToOne
    @JoinColumn(unique = true)
    private CodigoCustomizavel codigoCustomizado;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ConfiguracaoColetor cliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public CodigoCustomizavel getCodigoCustomizado() {
        return codigoCustomizado;
    }

    public ConfiguracaoColetor codigoCustomizado(CodigoCustomizavel codigoCustomizavel) {
        this.codigoCustomizado = codigoCustomizavel;
        return this;
    }

    public void setCodigoCustomizado(CodigoCustomizavel codigoCustomizavel) {
        this.codigoCustomizado = codigoCustomizavel;
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
        ConfiguracaoColetor configuracaoColetor = (ConfiguracaoColetor) o;
        if (configuracaoColetor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), configuracaoColetor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConfiguracaoColetor{" +
            "id=" + getId() +
            "}";
    }
}
