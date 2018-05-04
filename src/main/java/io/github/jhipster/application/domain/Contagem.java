package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Contagem.
 */
@Entity
@Table(name = "contagem")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Contagem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_secao")
    private String codigoSecao;

    @Column(name = "codigo_interno")
    private String codigoInterno;

    @Column(name = "ean")
    private String ean;

    @Column(name = "quantidade", precision=10, scale=2)
    private BigDecimal quantidade;

    @Column(name = "quantidade_original", precision=10, scale=2)
    private BigDecimal quantidadeOriginal;

    @Column(name = "time_stamp")
    private Instant timeStamp;

    @Column(name = "string_customizado_1")
    private String stringCustomizado1;

    @Column(name = "string_customizado_2")
    private String stringCustomizado2;

    @Column(name = "string_customizado_3")
    private String stringCustomizado3;

    @Column(name = "string_customizado_4")
    private String stringCustomizado4;

    @Column(name = "string_customizado_5")
    private String stringCustomizado5;

    @Column(name = "string_customizado_6")
    private String stringCustomizado6;

    @Column(name = "string_customizado_7")
    private String stringCustomizado7;

    @Column(name = "string_customizado_8")
    private String stringCustomizado8;

    @Column(name = "string_customizado_9")
    private String stringCustomizado9;

    @Column(name = "string_customizado_10")
    private String stringCustomizado10;

    @Column(name = "int_customizado_1", precision=10, scale=2)
    private BigDecimal intCustomizado1;

    @Column(name = "int_customizado_2", precision=10, scale=2)
    private BigDecimal intCustomizado2;

    @Column(name = "int_customizado_3", precision=10, scale=2)
    private BigDecimal intCustomizado3;

    @Column(name = "int_customizado_4", precision=10, scale=2)
    private BigDecimal intCustomizado4;

    @Column(name = "int_customizado_5", precision=10, scale=2)
    private BigDecimal intCustomizado5;

    @Column(name = "int_customizado_6", precision=10, scale=2)
    private BigDecimal intCustomizado6;

    @Column(name = "int_customizado_7", precision=10, scale=2)
    private BigDecimal intCustomizado7;

    @Column(name = "int_customizado_8", precision=10, scale=2)
    private BigDecimal intCustomizado8;

    @Column(name = "int_customizado_9", precision=10, scale=2)
    private BigDecimal intCustomizado9;

    @Column(name = "int_customizado_10", precision=10, scale=2)
    private BigDecimal intCustomizado10;

    @Column(name = "bin_customizado_1")
    private Boolean binCustomizado1;

    @Column(name = "bin_customizado_2")
    private Boolean binCustomizado2;

    @Column(name = "bin_customizado_3")
    private Boolean binCustomizado3;

    @ManyToOne
    private StatusContagem statusContagem;

    @ManyToOne
    private MotivoAlteracao motivoAlteracao;

    @ManyToOne
    private Secao secao;

    @OneToMany(mappedBy = "contagem")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<LogAlteracaoContagem> logAlteracoes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoSecao() {
        return codigoSecao;
    }

    public Contagem codigoSecao(String codigoSecao) {
        this.codigoSecao = codigoSecao;
        return this;
    }

    public void setCodigoSecao(String codigoSecao) {
        this.codigoSecao = codigoSecao;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public Contagem codigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
        return this;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public String getEan() {
        return ean;
    }

    public Contagem ean(String ean) {
        this.ean = ean;
        return this;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public Contagem quantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getQuantidadeOriginal() {
        return quantidadeOriginal;
    }

    public Contagem quantidadeOriginal(BigDecimal quantidadeOriginal) {
        this.quantidadeOriginal = quantidadeOriginal;
        return this;
    }

    public void setQuantidadeOriginal(BigDecimal quantidadeOriginal) {
        this.quantidadeOriginal = quantidadeOriginal;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public Contagem timeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getStringCustomizado1() {
        return stringCustomizado1;
    }

    public Contagem stringCustomizado1(String stringCustomizado1) {
        this.stringCustomizado1 = stringCustomizado1;
        return this;
    }

    public void setStringCustomizado1(String stringCustomizado1) {
        this.stringCustomizado1 = stringCustomizado1;
    }

    public String getStringCustomizado2() {
        return stringCustomizado2;
    }

    public Contagem stringCustomizado2(String stringCustomizado2) {
        this.stringCustomizado2 = stringCustomizado2;
        return this;
    }

    public void setStringCustomizado2(String stringCustomizado2) {
        this.stringCustomizado2 = stringCustomizado2;
    }

    public String getStringCustomizado3() {
        return stringCustomizado3;
    }

    public Contagem stringCustomizado3(String stringCustomizado3) {
        this.stringCustomizado3 = stringCustomizado3;
        return this;
    }

    public void setStringCustomizado3(String stringCustomizado3) {
        this.stringCustomizado3 = stringCustomizado3;
    }

    public String getStringCustomizado4() {
        return stringCustomizado4;
    }

    public Contagem stringCustomizado4(String stringCustomizado4) {
        this.stringCustomizado4 = stringCustomizado4;
        return this;
    }

    public void setStringCustomizado4(String stringCustomizado4) {
        this.stringCustomizado4 = stringCustomizado4;
    }

    public String getStringCustomizado5() {
        return stringCustomizado5;
    }

    public Contagem stringCustomizado5(String stringCustomizado5) {
        this.stringCustomizado5 = stringCustomizado5;
        return this;
    }

    public void setStringCustomizado5(String stringCustomizado5) {
        this.stringCustomizado5 = stringCustomizado5;
    }

    public String getStringCustomizado6() {
        return stringCustomizado6;
    }

    public Contagem stringCustomizado6(String stringCustomizado6) {
        this.stringCustomizado6 = stringCustomizado6;
        return this;
    }

    public void setStringCustomizado6(String stringCustomizado6) {
        this.stringCustomizado6 = stringCustomizado6;
    }

    public String getStringCustomizado7() {
        return stringCustomizado7;
    }

    public Contagem stringCustomizado7(String stringCustomizado7) {
        this.stringCustomizado7 = stringCustomizado7;
        return this;
    }

    public void setStringCustomizado7(String stringCustomizado7) {
        this.stringCustomizado7 = stringCustomizado7;
    }

    public String getStringCustomizado8() {
        return stringCustomizado8;
    }

    public Contagem stringCustomizado8(String stringCustomizado8) {
        this.stringCustomizado8 = stringCustomizado8;
        return this;
    }

    public void setStringCustomizado8(String stringCustomizado8) {
        this.stringCustomizado8 = stringCustomizado8;
    }

    public String getStringCustomizado9() {
        return stringCustomizado9;
    }

    public Contagem stringCustomizado9(String stringCustomizado9) {
        this.stringCustomizado9 = stringCustomizado9;
        return this;
    }

    public void setStringCustomizado9(String stringCustomizado9) {
        this.stringCustomizado9 = stringCustomizado9;
    }

    public String getStringCustomizado10() {
        return stringCustomizado10;
    }

    public Contagem stringCustomizado10(String stringCustomizado10) {
        this.stringCustomizado10 = stringCustomizado10;
        return this;
    }

    public void setStringCustomizado10(String stringCustomizado10) {
        this.stringCustomizado10 = stringCustomizado10;
    }

    public BigDecimal getIntCustomizado1() {
        return intCustomizado1;
    }

    public Contagem intCustomizado1(BigDecimal intCustomizado1) {
        this.intCustomizado1 = intCustomizado1;
        return this;
    }

    public void setIntCustomizado1(BigDecimal intCustomizado1) {
        this.intCustomizado1 = intCustomizado1;
    }

    public BigDecimal getIntCustomizado2() {
        return intCustomizado2;
    }

    public Contagem intCustomizado2(BigDecimal intCustomizado2) {
        this.intCustomizado2 = intCustomizado2;
        return this;
    }

    public void setIntCustomizado2(BigDecimal intCustomizado2) {
        this.intCustomizado2 = intCustomizado2;
    }

    public BigDecimal getIntCustomizado3() {
        return intCustomizado3;
    }

    public Contagem intCustomizado3(BigDecimal intCustomizado3) {
        this.intCustomizado3 = intCustomizado3;
        return this;
    }

    public void setIntCustomizado3(BigDecimal intCustomizado3) {
        this.intCustomizado3 = intCustomizado3;
    }

    public BigDecimal getIntCustomizado4() {
        return intCustomizado4;
    }

    public Contagem intCustomizado4(BigDecimal intCustomizado4) {
        this.intCustomizado4 = intCustomizado4;
        return this;
    }

    public void setIntCustomizado4(BigDecimal intCustomizado4) {
        this.intCustomizado4 = intCustomizado4;
    }

    public BigDecimal getIntCustomizado5() {
        return intCustomizado5;
    }

    public Contagem intCustomizado5(BigDecimal intCustomizado5) {
        this.intCustomizado5 = intCustomizado5;
        return this;
    }

    public void setIntCustomizado5(BigDecimal intCustomizado5) {
        this.intCustomizado5 = intCustomizado5;
    }

    public BigDecimal getIntCustomizado6() {
        return intCustomizado6;
    }

    public Contagem intCustomizado6(BigDecimal intCustomizado6) {
        this.intCustomizado6 = intCustomizado6;
        return this;
    }

    public void setIntCustomizado6(BigDecimal intCustomizado6) {
        this.intCustomizado6 = intCustomizado6;
    }

    public BigDecimal getIntCustomizado7() {
        return intCustomizado7;
    }

    public Contagem intCustomizado7(BigDecimal intCustomizado7) {
        this.intCustomizado7 = intCustomizado7;
        return this;
    }

    public void setIntCustomizado7(BigDecimal intCustomizado7) {
        this.intCustomizado7 = intCustomizado7;
    }

    public BigDecimal getIntCustomizado8() {
        return intCustomizado8;
    }

    public Contagem intCustomizado8(BigDecimal intCustomizado8) {
        this.intCustomizado8 = intCustomizado8;
        return this;
    }

    public void setIntCustomizado8(BigDecimal intCustomizado8) {
        this.intCustomizado8 = intCustomizado8;
    }

    public BigDecimal getIntCustomizado9() {
        return intCustomizado9;
    }

    public Contagem intCustomizado9(BigDecimal intCustomizado9) {
        this.intCustomizado9 = intCustomizado9;
        return this;
    }

    public void setIntCustomizado9(BigDecimal intCustomizado9) {
        this.intCustomizado9 = intCustomizado9;
    }

    public BigDecimal getIntCustomizado10() {
        return intCustomizado10;
    }

    public Contagem intCustomizado10(BigDecimal intCustomizado10) {
        this.intCustomizado10 = intCustomizado10;
        return this;
    }

    public void setIntCustomizado10(BigDecimal intCustomizado10) {
        this.intCustomizado10 = intCustomizado10;
    }

    public Boolean isBinCustomizado1() {
        return binCustomizado1;
    }

    public Contagem binCustomizado1(Boolean binCustomizado1) {
        this.binCustomizado1 = binCustomizado1;
        return this;
    }

    public void setBinCustomizado1(Boolean binCustomizado1) {
        this.binCustomizado1 = binCustomizado1;
    }

    public Boolean isBinCustomizado2() {
        return binCustomizado2;
    }

    public Contagem binCustomizado2(Boolean binCustomizado2) {
        this.binCustomizado2 = binCustomizado2;
        return this;
    }

    public void setBinCustomizado2(Boolean binCustomizado2) {
        this.binCustomizado2 = binCustomizado2;
    }

    public Boolean isBinCustomizado3() {
        return binCustomizado3;
    }

    public Contagem binCustomizado3(Boolean binCustomizado3) {
        this.binCustomizado3 = binCustomizado3;
        return this;
    }

    public void setBinCustomizado3(Boolean binCustomizado3) {
        this.binCustomizado3 = binCustomizado3;
    }

    public StatusContagem getStatusContagem() {
        return statusContagem;
    }

    public Contagem statusContagem(StatusContagem statusContagem) {
        this.statusContagem = statusContagem;
        return this;
    }

    public void setStatusContagem(StatusContagem statusContagem) {
        this.statusContagem = statusContagem;
    }

    public MotivoAlteracao getMotivoAlteracao() {
        return motivoAlteracao;
    }

    public Contagem motivoAlteracao(MotivoAlteracao motivoAlteracao) {
        this.motivoAlteracao = motivoAlteracao;
        return this;
    }

    public void setMotivoAlteracao(MotivoAlteracao motivoAlteracao) {
        this.motivoAlteracao = motivoAlteracao;
    }

    public Secao getSecao() {
        return secao;
    }

    public Contagem secao(Secao secao) {
        this.secao = secao;
        return this;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }

    public Set<LogAlteracaoContagem> getLogAlteracoes() {
        return logAlteracoes;
    }

    public Contagem logAlteracoes(Set<LogAlteracaoContagem> logAlteracaoContagems) {
        this.logAlteracoes = logAlteracaoContagems;
        return this;
    }

    public Contagem addLogAlteracoes(LogAlteracaoContagem logAlteracaoContagem) {
        this.logAlteracoes.add(logAlteracaoContagem);
        logAlteracaoContagem.setContagem(this);
        return this;
    }

    public Contagem removeLogAlteracoes(LogAlteracaoContagem logAlteracaoContagem) {
        this.logAlteracoes.remove(logAlteracaoContagem);
        logAlteracaoContagem.setContagem(null);
        return this;
    }

    public void setLogAlteracoes(Set<LogAlteracaoContagem> logAlteracaoContagems) {
        this.logAlteracoes = logAlteracaoContagems;
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
        Contagem contagem = (Contagem) o;
        if (contagem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contagem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Contagem{" +
            "id=" + getId() +
            ", codigoSecao='" + getCodigoSecao() + "'" +
            ", codigoInterno='" + getCodigoInterno() + "'" +
            ", ean='" + getEan() + "'" +
            ", quantidade=" + getQuantidade() +
            ", quantidadeOriginal=" + getQuantidadeOriginal() +
            ", timeStamp='" + getTimeStamp() + "'" +
            ", stringCustomizado1='" + getStringCustomizado1() + "'" +
            ", stringCustomizado2='" + getStringCustomizado2() + "'" +
            ", stringCustomizado3='" + getStringCustomizado3() + "'" +
            ", stringCustomizado4='" + getStringCustomizado4() + "'" +
            ", stringCustomizado5='" + getStringCustomizado5() + "'" +
            ", stringCustomizado6='" + getStringCustomizado6() + "'" +
            ", stringCustomizado7='" + getStringCustomizado7() + "'" +
            ", stringCustomizado8='" + getStringCustomizado8() + "'" +
            ", stringCustomizado9='" + getStringCustomizado9() + "'" +
            ", stringCustomizado10='" + getStringCustomizado10() + "'" +
            ", intCustomizado1=" + getIntCustomizado1() +
            ", intCustomizado2=" + getIntCustomizado2() +
            ", intCustomizado3=" + getIntCustomizado3() +
            ", intCustomizado4=" + getIntCustomizado4() +
            ", intCustomizado5=" + getIntCustomizado5() +
            ", intCustomizado6=" + getIntCustomizado6() +
            ", intCustomizado7=" + getIntCustomizado7() +
            ", intCustomizado8=" + getIntCustomizado8() +
            ", intCustomizado9=" + getIntCustomizado9() +
            ", intCustomizado10=" + getIntCustomizado10() +
            ", binCustomizado1='" + isBinCustomizado1() + "'" +
            ", binCustomizado2='" + isBinCustomizado2() + "'" +
            ", binCustomizado3='" + isBinCustomizado3() + "'" +
            "}";
    }
}
