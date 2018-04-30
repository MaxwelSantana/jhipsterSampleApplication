package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A LogStatusInventario.
 */
@Entity
@Table(name = "log_status_inventario")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LogStatusInventario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_stamp")
    private Instant timeStamp;

    @OneToOne
    @JoinColumn(unique = true)
    private StatusInventario statusInventario;

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

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public LogStatusInventario timeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    public StatusInventario getStatusInventario() {
        return statusInventario;
    }

    public LogStatusInventario statusInventario(StatusInventario statusInventario) {
        this.statusInventario = statusInventario;
        return this;
    }

    public void setStatusInventario(StatusInventario statusInventario) {
        this.statusInventario = statusInventario;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public LogStatusInventario inventario(Inventario inventario) {
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
        LogStatusInventario logStatusInventario = (LogStatusInventario) o;
        if (logStatusInventario.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), logStatusInventario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LogStatusInventario{" +
            "id=" + getId() +
            ", timeStamp='" + getTimeStamp() + "'" +
            "}";
    }
}
