package org.jhipster.com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Metric.
 */
@Entity
@Table(name = "metric")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "metric")
public class Metric implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "value", nullable = false)
    private Double value;

    @ManyToOne
    private EquipmentSet equipmentSet;

    @OneToOne
    @JoinColumn(unique = true)
    private Threshold threshold;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Metric name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public Metric value(Double value) {
        this.value = value;
        return this;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public EquipmentSet getEquipmentSet() {
        return equipmentSet;
    }

    public Metric equipmentSet(EquipmentSet equipmentSet) {
        this.equipmentSet = equipmentSet;
        return this;
    }

    public void setEquipmentSet(EquipmentSet equipmentSet) {
        this.equipmentSet = equipmentSet;
    }

    public Threshold getThreshold() {
        return threshold;
    }

    public Metric threshold(Threshold threshold) {
        this.threshold = threshold;
        return this;
    }

    public void setThreshold(Threshold threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Metric metric = (Metric) o;
        if(metric.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, metric.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Metric{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", value='" + value + "'" +
            '}';
    }
}
