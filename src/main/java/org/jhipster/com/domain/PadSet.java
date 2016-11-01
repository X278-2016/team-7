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
 * A PadSet.
 */
@Entity
@Table(name = "pad_set")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "padset")
public class PadSet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @NotNull
    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @OneToMany(mappedBy = "padSet")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EquipmentSet> equipmentSets = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public PadSet name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public PadSet latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public PadSet longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Set<EquipmentSet> getEquipmentSets() {
        return equipmentSets;
    }

    public PadSet equipmentSets(Set<EquipmentSet> equipmentSets) {
        this.equipmentSets = equipmentSets;
        return this;
    }

    public PadSet addEquipmentSet(EquipmentSet equipmentSet) {
        equipmentSets.add(equipmentSet);
        equipmentSet.setPadSet(this);
        return this;
    }

    public PadSet removeEquipmentSet(EquipmentSet equipmentSet) {
        equipmentSets.remove(equipmentSet);
        equipmentSet.setPadSet(null);
        return this;
    }

    public void setEquipmentSets(Set<EquipmentSet> equipmentSets) {
        this.equipmentSets = equipmentSets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PadSet padSet = (PadSet) o;
        if(padSet.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, padSet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PadSet{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", latitude='" + latitude + "'" +
            ", longitude='" + longitude + "'" +
            '}';
    }
}
