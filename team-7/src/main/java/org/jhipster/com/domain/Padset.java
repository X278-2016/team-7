package org.jhipster.com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Padset.
 */
@Entity
@Table(name = "padset")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "padset")
public class Padset implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "m_pad_set_name", nullable = false)
    private String mPadSetName;

    @NotNull
    @Column(name = "m_latitude", nullable = false)
    private Double mLatitude;

    @NotNull
    @Column(name = "m_longitude", nullable = false)
    private Double mLongitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getmPadSetName() {
        return mPadSetName;
    }

    public Padset mPadSetName(String mPadSetName) {
        this.mPadSetName = mPadSetName;
        return this;
    }

    public void setmPadSetName(String mPadSetName) {
        this.mPadSetName = mPadSetName;
    }

    public Double getmLatitude() {
        return mLatitude;
    }

    public Padset mLatitude(Double mLatitude) {
        this.mLatitude = mLatitude;
        return this;
    }

    public void setmLatitude(Double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public Double getmLongitude() {
        return mLongitude;
    }

    public Padset mLongitude(Double mLongitude) {
        this.mLongitude = mLongitude;
        return this;
    }

    public void setmLongitude(Double mLongitude) {
        this.mLongitude = mLongitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Padset padset = (Padset) o;
        if(padset.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, padset.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Padset{" +
            "id=" + id +
            ", mPadSetName='" + mPadSetName + "'" +
            ", mLatitude='" + mLatitude + "'" +
            ", mLongitude='" + mLongitude + "'" +
            '}';
    }
}
