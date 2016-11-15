package org.jhipster.com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Equipmentset.
 */
@Entity
@Table(name = "equipmentset")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "equipmentset")
public class Equipmentset implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "m_equipment_set_name", nullable = false)
    private String mEquipmentSetName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getmEquipmentSetName() {
        return mEquipmentSetName;
    }

    public Equipmentset mEquipmentSetName(String mEquipmentSetName) {
        this.mEquipmentSetName = mEquipmentSetName;
        return this;
    }

    public void setmEquipmentSetName(String mEquipmentSetName) {
        this.mEquipmentSetName = mEquipmentSetName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Equipmentset equipmentset = (Equipmentset) o;
        if(equipmentset.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, equipmentset.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Equipmentset{" +
            "id=" + id +
            ", mEquipmentSetName='" + mEquipmentSetName + "'" +
            '}';
    }
}
