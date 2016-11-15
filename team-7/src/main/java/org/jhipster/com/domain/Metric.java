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
    @Column(name = "m_metric_name", nullable = false)
    private String mMetricName;

    @NotNull
    @Column(name = "m_metric_value", nullable = false)
    private Double mMetricValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getmMetricName() {
        return mMetricName;
    }

    public Metric mMetricName(String mMetricName) {
        this.mMetricName = mMetricName;
        return this;
    }

    public void setmMetricName(String mMetricName) {
        this.mMetricName = mMetricName;
    }

    public Double getmMetricValue() {
        return mMetricValue;
    }

    public Metric mMetricValue(Double mMetricValue) {
        this.mMetricValue = mMetricValue;
        return this;
    }

    public void setmMetricValue(Double mMetricValue) {
        this.mMetricValue = mMetricValue;
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
            ", mMetricName='" + mMetricName + "'" +
            ", mMetricValue='" + mMetricValue + "'" +
            '}';
    }
}
