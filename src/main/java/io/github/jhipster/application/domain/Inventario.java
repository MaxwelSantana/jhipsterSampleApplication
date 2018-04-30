package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Inventario.
 */
@Entity
@Table(name = "inventario")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_loja")
    private String numeroLoja;

    @Column(name = "data_inventario")
    private LocalDate dataInventario;

    @Column(name = "ordem_servico")
    private String ordemServico;

    @Column(name = "gerente_loja")
    private String gerenteLoja;

    @Column(name = "lider_inventario")
    private String liderInventario;

    @Column(name = "qtd_pessoas")
    private Integer qtdPessoas;

    @Column(name = "nome_loja")
    private String nomeLoja;

    @OneToOne
    @JoinColumn(unique = true)
    private Funcionario funcionarioLider;

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

    public String getNumeroLoja() {
        return numeroLoja;
    }

    public Inventario numeroLoja(String numeroLoja) {
        this.numeroLoja = numeroLoja;
        return this;
    }

    public void setNumeroLoja(String numeroLoja) {
        this.numeroLoja = numeroLoja;
    }

    public LocalDate getDataInventario() {
        return dataInventario;
    }

    public Inventario dataInventario(LocalDate dataInventario) {
        this.dataInventario = dataInventario;
        return this;
    }

    public void setDataInventario(LocalDate dataInventario) {
        this.dataInventario = dataInventario;
    }

    public String getOrdemServico() {
        return ordemServico;
    }

    public Inventario ordemServico(String ordemServico) {
        this.ordemServico = ordemServico;
        return this;
    }

    public void setOrdemServico(String ordemServico) {
        this.ordemServico = ordemServico;
    }

    public String getGerenteLoja() {
        return gerenteLoja;
    }

    public Inventario gerenteLoja(String gerenteLoja) {
        this.gerenteLoja = gerenteLoja;
        return this;
    }

    public void setGerenteLoja(String gerenteLoja) {
        this.gerenteLoja = gerenteLoja;
    }

    public String getLiderInventario() {
        return liderInventario;
    }

    public Inventario liderInventario(String liderInventario) {
        this.liderInventario = liderInventario;
        return this;
    }

    public void setLiderInventario(String liderInventario) {
        this.liderInventario = liderInventario;
    }

    public Integer getQtdPessoas() {
        return qtdPessoas;
    }

    public Inventario qtdPessoas(Integer qtdPessoas) {
        this.qtdPessoas = qtdPessoas;
        return this;
    }

    public void setQtdPessoas(Integer qtdPessoas) {
        this.qtdPessoas = qtdPessoas;
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public Inventario nomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
        return this;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public Funcionario getFuncionarioLider() {
        return funcionarioLider;
    }

    public Inventario funcionarioLider(Funcionario funcionario) {
        this.funcionarioLider = funcionario;
        return this;
    }

    public void setFuncionarioLider(Funcionario funcionario) {
        this.funcionarioLider = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Inventario cliente(Cliente cliente) {
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
        Inventario inventario = (Inventario) o;
        if (inventario.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), inventario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Inventario{" +
            "id=" + getId() +
            ", numeroLoja='" + getNumeroLoja() + "'" +
            ", dataInventario='" + getDataInventario() + "'" +
            ", ordemServico='" + getOrdemServico() + "'" +
            ", gerenteLoja='" + getGerenteLoja() + "'" +
            ", liderInventario='" + getLiderInventario() + "'" +
            ", qtdPessoas=" + getQtdPessoas() +
            ", nomeLoja='" + getNomeLoja() + "'" +
            "}";
    }
}
