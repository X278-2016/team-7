package org.jhipster.com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A EquipmentSet.
 */
@Entity
@Table(name = "equipment_set")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "equipmentset")
public class EquipmentSet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "equipmentSet")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Metric> metrics = new HashSet<>();

    @ManyToOne
    private PadSet padSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public EquipmentSet name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Metric> getMetrics() {
        return metrics;
    }

    public EquipmentSet metrics(Set<Metric> metrics) {
        this.metrics = metrics;
        return this;
    }

    public EquipmentSet addMetric(Metric metric) {
        metrics.add(metric);
        metric.setEquipmentSet(this);
        return this;
    }

    public EquipmentSet removeMetric(Metric metric) {
        metrics.remove(metric);
        metric.setEquipmentSet(null);
        return this;
    }

    public void setMetrics(Set<Metric> metrics) {
        this.metrics = metrics;
    }

    public PadSet getPadSet() {
        return padSet;
    }

    public EquipmentSet padSet(PadSet padSet) {
        this.padSet = padSet;
        return this;
    }

    public void setPadSet(PadSet padSet) {
        this.padSet = padSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EquipmentSet equipmentSet = (EquipmentSet) o;
        if(equipmentSet.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, equipmentSet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EquipmentSet{" +
            "id=" + id +
            ", name='" + name + "'" +
            '}';
    }
}
