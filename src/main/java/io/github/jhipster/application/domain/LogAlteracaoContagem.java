package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A LogAlteracaoContagem.
 */
@Entity
@Table(name = "log_alteracao_contagem")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LogAlteracaoContagem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "campo")
    private String campo;

    @Column(name = "valor_antigo")
    private String valorAntigo;

    @Column(name = "valor_novo")
    private String valorNovo;

    @Column(name = "time_stamp")
    private Instant timeStamp;

    @ManyToOne
    private Contagem contagem;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCampo() {
        return campo;
    }

    public LogAlteracaoContagem campo(String campo) {
        this.campo = campo;
        return this;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getValorAntigo() {
        return valorAntigo;
    }

    public LogAlteracaoContagem valorAntigo(String valorAntigo) {
        this.valorAntigo = valorAntigo;
        return this;
    }

    public void setValorAntigo(String valorAntigo) {
        this.valorAntigo = valorAntigo;
    }

    public String getValorNovo() {
        return valorNovo;
    }

    public LogAlteracaoContagem valorNovo(String valorNovo) {
        this.valorNovo = valorNovo;
        return this;
    }

    public void setValorNovo(String valorNovo) {
        this.valorNovo = valorNovo;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public LogAlteracaoContagem timeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Contagem getContagem() {
        return contagem;
    }

    public LogAlteracaoContagem contagem(Contagem contagem) {
        this.contagem = contagem;
        return this;
    }

    public void setContagem(Contagem contagem) {
        this.contagem = contagem;
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
        LogAlteracaoContagem logAlteracaoContagem = (LogAlteracaoContagem) o;
        if (logAlteracaoContagem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), logAlteracaoContagem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LogAlteracaoContagem{" +
            "id=" + getId() +
            ", campo='" + getCampo() + "'" +
            ", valorAntigo='" + getValorAntigo() + "'" +
            ", valorNovo='" + getValorNovo() + "'" +
            ", timeStamp='" + getTimeStamp() + "'" +
            "}";
    }
}
