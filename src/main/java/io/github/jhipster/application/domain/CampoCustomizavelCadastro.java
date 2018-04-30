package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A CampoCustomizavelCadastro.
 */
@Entity
@Table(name = "campo_customizavel_cadastro")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CampoCustomizavelCadastro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToOne
    @JoinColumn(unique = true)
    private Cliente cliente;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStringCustomizado1() {
        return stringCustomizado1;
    }

    public CampoCustomizavelCadastro stringCustomizado1(String stringCustomizado1) {
        this.stringCustomizado1 = stringCustomizado1;
        return this;
    }

    public void setStringCustomizado1(String stringCustomizado1) {
        this.stringCustomizado1 = stringCustomizado1;
    }

    public String getStringCustomizado2() {
        return stringCustomizado2;
    }

    public CampoCustomizavelCadastro stringCustomizado2(String stringCustomizado2) {
        this.stringCustomizado2 = stringCustomizado2;
        return this;
    }

    public void setStringCustomizado2(String stringCustomizado2) {
        this.stringCustomizado2 = stringCustomizado2;
    }

    public String getStringCustomizado3() {
        return stringCustomizado3;
    }

    public CampoCustomizavelCadastro stringCustomizado3(String stringCustomizado3) {
        this.stringCustomizado3 = stringCustomizado3;
        return this;
    }

    public void setStringCustomizado3(String stringCustomizado3) {
        this.stringCustomizado3 = stringCustomizado3;
    }

    public String getStringCustomizado4() {
        return stringCustomizado4;
    }

    public CampoCustomizavelCadastro stringCustomizado4(String stringCustomizado4) {
        this.stringCustomizado4 = stringCustomizado4;
        return this;
    }

    public void setStringCustomizado4(String stringCustomizado4) {
        this.stringCustomizado4 = stringCustomizado4;
    }

    public String getStringCustomizado5() {
        return stringCustomizado5;
    }

    public CampoCustomizavelCadastro stringCustomizado5(String stringCustomizado5) {
        this.stringCustomizado5 = stringCustomizado5;
        return this;
    }

    public void setStringCustomizado5(String stringCustomizado5) {
        this.stringCustomizado5 = stringCustomizado5;
    }

    public String getStringCustomizado6() {
        return stringCustomizado6;
    }

    public CampoCustomizavelCadastro stringCustomizado6(String stringCustomizado6) {
        this.stringCustomizado6 = stringCustomizado6;
        return this;
    }

    public void setStringCustomizado6(String stringCustomizado6) {
        this.stringCustomizado6 = stringCustomizado6;
    }

    public String getStringCustomizado7() {
        return stringCustomizado7;
    }

    public CampoCustomizavelCadastro stringCustomizado7(String stringCustomizado7) {
        this.stringCustomizado7 = stringCustomizado7;
        return this;
    }

    public void setStringCustomizado7(String stringCustomizado7) {
        this.stringCustomizado7 = stringCustomizado7;
    }

    public String getStringCustomizado8() {
        return stringCustomizado8;
    }

    public CampoCustomizavelCadastro stringCustomizado8(String stringCustomizado8) {
        this.stringCustomizado8 = stringCustomizado8;
        return this;
    }

    public void setStringCustomizado8(String stringCustomizado8) {
        this.stringCustomizado8 = stringCustomizado8;
    }

    public String getStringCustomizado9() {
        return stringCustomizado9;
    }

    public CampoCustomizavelCadastro stringCustomizado9(String stringCustomizado9) {
        this.stringCustomizado9 = stringCustomizado9;
        return this;
    }

    public void setStringCustomizado9(String stringCustomizado9) {
        this.stringCustomizado9 = stringCustomizado9;
    }

    public String getStringCustomizado10() {
        return stringCustomizado10;
    }

    public CampoCustomizavelCadastro stringCustomizado10(String stringCustomizado10) {
        this.stringCustomizado10 = stringCustomizado10;
        return this;
    }

    public void setStringCustomizado10(String stringCustomizado10) {
        this.stringCustomizado10 = stringCustomizado10;
    }

    public BigDecimal getIntCustomizado1() {
        return intCustomizado1;
    }

    public CampoCustomizavelCadastro intCustomizado1(BigDecimal intCustomizado1) {
        this.intCustomizado1 = intCustomizado1;
        return this;
    }

    public void setIntCustomizado1(BigDecimal intCustomizado1) {
        this.intCustomizado1 = intCustomizado1;
    }

    public BigDecimal getIntCustomizado2() {
        return intCustomizado2;
    }

    public CampoCustomizavelCadastro intCustomizado2(BigDecimal intCustomizado2) {
        this.intCustomizado2 = intCustomizado2;
        return this;
    }

    public void setIntCustomizado2(BigDecimal intCustomizado2) {
        this.intCustomizado2 = intCustomizado2;
    }

    public BigDecimal getIntCustomizado3() {
        return intCustomizado3;
    }

    public CampoCustomizavelCadastro intCustomizado3(BigDecimal intCustomizado3) {
        this.intCustomizado3 = intCustomizado3;
        return this;
    }

    public void setIntCustomizado3(BigDecimal intCustomizado3) {
        this.intCustomizado3 = intCustomizado3;
    }

    public BigDecimal getIntCustomizado4() {
        return intCustomizado4;
    }

    public CampoCustomizavelCadastro intCustomizado4(BigDecimal intCustomizado4) {
        this.intCustomizado4 = intCustomizado4;
        return this;
    }

    public void setIntCustomizado4(BigDecimal intCustomizado4) {
        this.intCustomizado4 = intCustomizado4;
    }

    public BigDecimal getIntCustomizado5() {
        return intCustomizado5;
    }

    public CampoCustomizavelCadastro intCustomizado5(BigDecimal intCustomizado5) {
        this.intCustomizado5 = intCustomizado5;
        return this;
    }

    public void setIntCustomizado5(BigDecimal intCustomizado5) {
        this.intCustomizado5 = intCustomizado5;
    }

    public BigDecimal getIntCustomizado6() {
        return intCustomizado6;
    }

    public CampoCustomizavelCadastro intCustomizado6(BigDecimal intCustomizado6) {
        this.intCustomizado6 = intCustomizado6;
        return this;
    }

    public void setIntCustomizado6(BigDecimal intCustomizado6) {
        this.intCustomizado6 = intCustomizado6;
    }

    public BigDecimal getIntCustomizado7() {
        return intCustomizado7;
    }

    public CampoCustomizavelCadastro intCustomizado7(BigDecimal intCustomizado7) {
        this.intCustomizado7 = intCustomizado7;
        return this;
    }

    public void setIntCustomizado7(BigDecimal intCustomizado7) {
        this.intCustomizado7 = intCustomizado7;
    }

    public BigDecimal getIntCustomizado8() {
        return intCustomizado8;
    }

    public CampoCustomizavelCadastro intCustomizado8(BigDecimal intCustomizado8) {
        this.intCustomizado8 = intCustomizado8;
        return this;
    }

    public void setIntCustomizado8(BigDecimal intCustomizado8) {
        this.intCustomizado8 = intCustomizado8;
    }

    public BigDecimal getIntCustomizado9() {
        return intCustomizado9;
    }

    public CampoCustomizavelCadastro intCustomizado9(BigDecimal intCustomizado9) {
        this.intCustomizado9 = intCustomizado9;
        return this;
    }

    public void setIntCustomizado9(BigDecimal intCustomizado9) {
        this.intCustomizado9 = intCustomizado9;
    }

    public BigDecimal getIntCustomizado10() {
        return intCustomizado10;
    }

    public CampoCustomizavelCadastro intCustomizado10(BigDecimal intCustomizado10) {
        this.intCustomizado10 = intCustomizado10;
        return this;
    }

    public void setIntCustomizado10(BigDecimal intCustomizado10) {
        this.intCustomizado10 = intCustomizado10;
    }

    public Boolean isBinCustomizado1() {
        return binCustomizado1;
    }

    public CampoCustomizavelCadastro binCustomizado1(Boolean binCustomizado1) {
        this.binCustomizado1 = binCustomizado1;
        return this;
    }

    public void setBinCustomizado1(Boolean binCustomizado1) {
        this.binCustomizado1 = binCustomizado1;
    }

    public Boolean isBinCustomizado2() {
        return binCustomizado2;
    }

    public CampoCustomizavelCadastro binCustomizado2(Boolean binCustomizado2) {
        this.binCustomizado2 = binCustomizado2;
        return this;
    }

    public void setBinCustomizado2(Boolean binCustomizado2) {
        this.binCustomizado2 = binCustomizado2;
    }

    public Boolean isBinCustomizado3() {
        return binCustomizado3;
    }

    public CampoCustomizavelCadastro binCustomizado3(Boolean binCustomizado3) {
        this.binCustomizado3 = binCustomizado3;
        return this;
    }

    public void setBinCustomizado3(Boolean binCustomizado3) {
        this.binCustomizado3 = binCustomizado3;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public CampoCustomizavelCadastro cliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        CampoCustomizavelCadastro campoCustomizavelCadastro = (CampoCustomizavelCadastro) o;
        if (campoCustomizavelCadastro.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), campoCustomizavelCadastro.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CampoCustomizavelCadastro{" +
            "id=" + getId() +
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
