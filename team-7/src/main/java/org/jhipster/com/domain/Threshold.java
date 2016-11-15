package org.jhipster.com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Threshold.
 */
@Entity
@Table(name = "threshold")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "threshold")
public class Threshold implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "m_threshold_value", nullable = false)
    private Double mThresholdValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getmThresholdValue() {
        return mThresholdValue;
    }

    public Threshold mThresholdValue(Double mThresholdValue) {
        this.mThresholdValue = mThresholdValue;
        return this;
    }

    public void setmThresholdValue(Double mThresholdValue) {
        this.mThresholdValue = mThresholdValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Threshold threshold = (Threshold) o;
        if(threshold.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, threshold.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Threshold{" +
            "id=" + id +
            ", mThresholdValue='" + mThresholdValue + "'" +
            '}';
    }
}
