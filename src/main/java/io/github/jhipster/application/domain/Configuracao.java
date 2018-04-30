package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Configuracao.
 */
@Entity
@Table(name = "configuracao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Configuracao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_interno")
    private String codigoInterno;

    @Column(name = "ean")
    private String ean;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "quantidade")
    private String quantidade;

    @Column(name = "preco")
    private String preco;

    @Column(name = "setor")
    private String setor;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "preco_custo")
    private String precoCusto;

    @Column(name = "posicional")
    private Boolean posicional;

    @Column(name = "delimitador")
    private String delimitador;

    @Column(name = "desconsiderar_linha_inicial")
    private Integer desconsiderarLinhaInicial;

    @Column(name = "desconsiderar_linha_final")
    private Integer desconsiderarLinhaFinal;

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

    @Column(name = "int_customizado_1")
    private String intCustomizado1;

    @Column(name = "int_customizado_2")
    private String intCustomizado2;

    @Column(name = "int_customizado_3")
    private String intCustomizado3;

    @Column(name = "int_customizado_4")
    private String intCustomizado4;

    @Column(name = "int_customizado_5")
    private String intCustomizado5;

    @Column(name = "int_customizado_6")
    private String intCustomizado6;

    @Column(name = "int_customizado_7")
    private String intCustomizado7;

    @Column(name = "int_customizado_8")
    private String intCustomizado8;

    @Column(name = "int_customizado_9")
    private String intCustomizado9;

    @Column(name = "int_customizado_10")
    private String intCustomizado10;

    @Column(name = "bin_customizado_1")
    private String binCustomizado1;

    @Column(name = "bin_customizado_2")
    private String binCustomizado2;

    @Column(name = "bin_customizado_3")
    private String binCustomizado3;

    @OneToOne
    @JoinColumn(unique = true)
    private Cliente cliente;

    @OneToOne
    @JoinColumn(unique = true)
    private TipoConfiguracao tipoConfiguracao;

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

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public Configuracao codigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
        return this;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public String getEan() {
        return ean;
    }

    public Configuracao ean(String ean) {
        this.ean = ean;
        return this;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getDescricao() {
        return descricao;
    }

    public Configuracao descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public Configuracao quantidade(String quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getPreco() {
        return preco;
    }

    public Configuracao preco(String preco) {
        this.preco = preco;
        return this;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getSetor() {
        return setor;
    }

    public Configuracao setor(String setor) {
        this.setor = setor;
        return this;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getDepartamento() {
        return departamento;
    }

    public Configuracao departamento(String departamento) {
        this.departamento = departamento;
        return this;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPrecoCusto() {
        return precoCusto;
    }

    public Configuracao precoCusto(String precoCusto) {
        this.precoCusto = precoCusto;
        return this;
    }

    public void setPrecoCusto(String precoCusto) {
        this.precoCusto = precoCusto;
    }

    public Boolean isPosicional() {
        return posicional;
    }

    public Configuracao posicional(Boolean posicional) {
        this.posicional = posicional;
        return this;
    }

    public void setPosicional(Boolean posicional) {
        this.posicional = posicional;
    }

    public String getDelimitador() {
        return delimitador;
    }

    public Configuracao delimitador(String delimitador) {
        this.delimitador = delimitador;
        return this;
    }

    public void setDelimitador(String delimitador) {
        this.delimitador = delimitador;
    }

    public Integer getDesconsiderarLinhaInicial() {
        return desconsiderarLinhaInicial;
    }

    public Configuracao desconsiderarLinhaInicial(Integer desconsiderarLinhaInicial) {
        this.desconsiderarLinhaInicial = desconsiderarLinhaInicial;
        return this;
    }

    public void setDesconsiderarLinhaInicial(Integer desconsiderarLinhaInicial) {
        this.desconsiderarLinhaInicial = desconsiderarLinhaInicial;
    }

    public Integer getDesconsiderarLinhaFinal() {
        return desconsiderarLinhaFinal;
    }

    public Configuracao desconsiderarLinhaFinal(Integer desconsiderarLinhaFinal) {
        this.desconsiderarLinhaFinal = desconsiderarLinhaFinal;
        return this;
    }

    public void setDesconsiderarLinhaFinal(Integer desconsiderarLinhaFinal) {
        this.desconsiderarLinhaFinal = desconsiderarLinhaFinal;
    }

    public String getStringCustomizado1() {
        return stringCustomizado1;
    }

    public Configuracao stringCustomizado1(String stringCustomizado1) {
        this.stringCustomizado1 = stringCustomizado1;
        return this;
    }

    public void setStringCustomizado1(String stringCustomizado1) {
        this.stringCustomizado1 = stringCustomizado1;
    }

    public String getStringCustomizado2() {
        return stringCustomizado2;
    }

    public Configuracao stringCustomizado2(String stringCustomizado2) {
        this.stringCustomizado2 = stringCustomizado2;
        return this;
    }

    public void setStringCustomizado2(String stringCustomizado2) {
        this.stringCustomizado2 = stringCustomizado2;
    }

    public String getStringCustomizado3() {
        return stringCustomizado3;
    }

    public Configuracao stringCustomizado3(String stringCustomizado3) {
        this.stringCustomizado3 = stringCustomizado3;
        return this;
    }

    public void setStringCustomizado3(String stringCustomizado3) {
        this.stringCustomizado3 = stringCustomizado3;
    }

    public String getStringCustomizado4() {
        return stringCustomizado4;
    }

    public Configuracao stringCustomizado4(String stringCustomizado4) {
        this.stringCustomizado4 = stringCustomizado4;
        return this;
    }

    public void setStringCustomizado4(String stringCustomizado4) {
        this.stringCustomizado4 = stringCustomizado4;
    }

    public String getStringCustomizado5() {
        return stringCustomizado5;
    }

    public Configuracao stringCustomizado5(String stringCustomizado5) {
        this.stringCustomizado5 = stringCustomizado5;
        return this;
    }

    public void setStringCustomizado5(String stringCustomizado5) {
        this.stringCustomizado5 = stringCustomizado5;
    }

    public String getStringCustomizado6() {
        return stringCustomizado6;
    }

    public Configuracao stringCustomizado6(String stringCustomizado6) {
        this.stringCustomizado6 = stringCustomizado6;
        return this;
    }

    public void setStringCustomizado6(String stringCustomizado6) {
        this.stringCustomizado6 = stringCustomizado6;
    }

    public String getStringCustomizado7() {
        return stringCustomizado7;
    }

    public Configuracao stringCustomizado7(String stringCustomizado7) {
        this.stringCustomizado7 = stringCustomizado7;
        return this;
    }

    public void setStringCustomizado7(String stringCustomizado7) {
        this.stringCustomizado7 = stringCustomizado7;
    }

    public String getStringCustomizado8() {
        return stringCustomizado8;
    }

    public Configuracao stringCustomizado8(String stringCustomizado8) {
        this.stringCustomizado8 = stringCustomizado8;
        return this;
    }

    public void setStringCustomizado8(String stringCustomizado8) {
        this.stringCustomizado8 = stringCustomizado8;
    }

    public String getStringCustomizado9() {
        return stringCustomizado9;
    }

    public Configuracao stringCustomizado9(String stringCustomizado9) {
        this.stringCustomizado9 = stringCustomizado9;
        return this;
    }

    public void setStringCustomizado9(String stringCustomizado9) {
        this.stringCustomizado9 = stringCustomizado9;
    }

    public String getStringCustomizado10() {
        return stringCustomizado10;
    }

    public Configuracao stringCustomizado10(String stringCustomizado10) {
        this.stringCustomizado10 = stringCustomizado10;
        return this;
    }

    public void setStringCustomizado10(String stringCustomizado10) {
        this.stringCustomizado10 = stringCustomizado10;
    }

    public String getIntCustomizado1() {
        return intCustomizado1;
    }

    public Configuracao intCustomizado1(String intCustomizado1) {
        this.intCustomizado1 = intCustomizado1;
        return this;
    }

    public void setIntCustomizado1(String intCustomizado1) {
        this.intCustomizado1 = intCustomizado1;
    }

    public String getIntCustomizado2() {
        return intCustomizado2;
    }

    public Configuracao intCustomizado2(String intCustomizado2) {
        this.intCustomizado2 = intCustomizado2;
        return this;
    }

    public void setIntCustomizado2(String intCustomizado2) {
        this.intCustomizado2 = intCustomizado2;
    }

    public String getIntCustomizado3() {
        return intCustomizado3;
    }

    public Configuracao intCustomizado3(String intCustomizado3) {
        this.intCustomizado3 = intCustomizado3;
        return this;
    }

    public void setIntCustomizado3(String intCustomizado3) {
        this.intCustomizado3 = intCustomizado3;
    }

    public String getIntCustomizado4() {
        return intCustomizado4;
    }

    public Configuracao intCustomizado4(String intCustomizado4) {
        this.intCustomizado4 = intCustomizado4;
        return this;
    }

    public void setIntCustomizado4(String intCustomizado4) {
        this.intCustomizado4 = intCustomizado4;
    }

    public String getIntCustomizado5() {
        return intCustomizado5;
    }

    public Configuracao intCustomizado5(String intCustomizado5) {
        this.intCustomizado5 = intCustomizado5;
        return this;
    }

    public void setIntCustomizado5(String intCustomizado5) {
        this.intCustomizado5 = intCustomizado5;
    }

    public String getIntCustomizado6() {
        return intCustomizado6;
    }

    public Configuracao intCustomizado6(String intCustomizado6) {
        this.intCustomizado6 = intCustomizado6;
        return this;
    }

    public void setIntCustomizado6(String intCustomizado6) {
        this.intCustomizado6 = intCustomizado6;
    }

    public String getIntCustomizado7() {
        return intCustomizado7;
    }

    public Configuracao intCustomizado7(String intCustomizado7) {
        this.intCustomizado7 = intCustomizado7;
        return this;
    }

    public void setIntCustomizado7(String intCustomizado7) {
        this.intCustomizado7 = intCustomizado7;
    }

    public String getIntCustomizado8() {
        return intCustomizado8;
    }

    public Configuracao intCustomizado8(String intCustomizado8) {
        this.intCustomizado8 = intCustomizado8;
        return this;
    }

    public void setIntCustomizado8(String intCustomizado8) {
        this.intCustomizado8 = intCustomizado8;
    }

    public String getIntCustomizado9() {
        return intCustomizado9;
    }

    public Configuracao intCustomizado9(String intCustomizado9) {
        this.intCustomizado9 = intCustomizado9;
        return this;
    }

    public void setIntCustomizado9(String intCustomizado9) {
        this.intCustomizado9 = intCustomizado9;
    }

    public String getIntCustomizado10() {
        return intCustomizado10;
    }

    public Configuracao intCustomizado10(String intCustomizado10) {
        this.intCustomizado10 = intCustomizado10;
        return this;
    }

    public void setIntCustomizado10(String intCustomizado10) {
        this.intCustomizado10 = intCustomizado10;
    }

    public String getBinCustomizado1() {
        return binCustomizado1;
    }

    public Configuracao binCustomizado1(String binCustomizado1) {
        this.binCustomizado1 = binCustomizado1;
        return this;
    }

    public void setBinCustomizado1(String binCustomizado1) {
        this.binCustomizado1 = binCustomizado1;
    }

    public String getBinCustomizado2() {
        return binCustomizado2;
    }

    public Configuracao binCustomizado2(String binCustomizado2) {
        this.binCustomizado2 = binCustomizado2;
        return this;
    }

    public void setBinCustomizado2(String binCustomizado2) {
        this.binCustomizado2 = binCustomizado2;
    }

    public String getBinCustomizado3() {
        return binCustomizado3;
    }

    public Configuracao binCustomizado3(String binCustomizado3) {
        this.binCustomizado3 = binCustomizado3;
        return this;
    }

    public void setBinCustomizado3(String binCustomizado3) {
        this.binCustomizado3 = binCustomizado3;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Configuracao cliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoConfiguracao getTipoConfiguracao() {
        return tipoConfiguracao;
    }

    public Configuracao tipoConfiguracao(TipoConfiguracao tipoConfiguracao) {
        this.tipoConfiguracao = tipoConfiguracao;
        return this;
    }

    public void setTipoConfiguracao(TipoConfiguracao tipoConfiguracao) {
        this.tipoConfiguracao = tipoConfiguracao;
    }

    public CodigoCustomizavel getCodigoCustomizado() {
        return codigoCustomizado;
    }

    public Configuracao codigoCustomizado(CodigoCustomizavel codigoCustomizavel) {
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
        Configuracao configuracao = (Configuracao) o;
        if (configuracao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), configuracao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Configuracao{" +
            "id=" + getId() +
            ", codigoInterno='" + getCodigoInterno() + "'" +
            ", ean='" + getEan() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", quantidade='" + getQuantidade() + "'" +
            ", preco='" + getPreco() + "'" +
            ", setor='" + getSetor() + "'" +
            ", departamento='" + getDepartamento() + "'" +
            ", precoCusto='" + getPrecoCusto() + "'" +
            ", posicional='" + isPosicional() + "'" +
            ", delimitador='" + getDelimitador() + "'" +
            ", desconsiderarLinhaInicial=" + getDesconsiderarLinhaInicial() +
            ", desconsiderarLinhaFinal=" + getDesconsiderarLinhaFinal() +
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
            ", intCustomizado1='" + getIntCustomizado1() + "'" +
            ", intCustomizado2='" + getIntCustomizado2() + "'" +
            ", intCustomizado3='" + getIntCustomizado3() + "'" +
            ", intCustomizado4='" + getIntCustomizado4() + "'" +
            ", intCustomizado5='" + getIntCustomizado5() + "'" +
            ", intCustomizado6='" + getIntCustomizado6() + "'" +
            ", intCustomizado7='" + getIntCustomizado7() + "'" +
            ", intCustomizado8='" + getIntCustomizado8() + "'" +
            ", intCustomizado9='" + getIntCustomizado9() + "'" +
            ", intCustomizado10='" + getIntCustomizado10() + "'" +
            ", binCustomizado1='" + getBinCustomizado1() + "'" +
            ", binCustomizado2='" + getBinCustomizado2() + "'" +
            ", binCustomizado3='" + getBinCustomizado3() + "'" +
            "}";
    }
}
