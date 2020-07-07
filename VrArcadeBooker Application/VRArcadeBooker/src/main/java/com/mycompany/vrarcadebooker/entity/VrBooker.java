package com.mycompany.vrarcadebooker.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity class of VR booker
 * 
 * @author OB
 * @Since 20200620
 * 
 */
@Entity
@Table(name = "VrBooker")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VrBooker.findAll", query = "SELECT v FROM VrBooker v"),
    @NamedQuery(name = "VrBooker.findById", query = "SELECT v FROM VrBooker v WHERE v.id = :id"),
    @NamedQuery(name = "VrBooker.findByName1", query = "SELECT v FROM VrBooker v WHERE v.name1 = :name1"),
    @NamedQuery(name = "VrBooker.findByName2", query = "SELECT v FROM VrBooker v WHERE v.name2 = :name2"),
    @NamedQuery(name = "VrBooker.findByName3", query = "SELECT v FROM VrBooker v WHERE v.name3 = :name3"),
    @NamedQuery(name = "VrBooker.findByName4", query = "SELECT v FROM VrBooker v WHERE v.name4 = :name4"),
    @NamedQuery(name = "VrBooker.findByDateOfBooking", query = "SELECT v FROM VrBooker v WHERE v.dateOfBooking = :dateOfBooking"),
    @NamedQuery(name = "VrBooker.findByTimeOfBooking", query = "SELECT v FROM VrBooker v WHERE v.timeOfBooking = :timeOfBooking"),
    @NamedQuery(name = "VrBooker.findByLengthOfBooking", query = "SELECT v FROM VrBooker v WHERE v.lengthOfBooking = :lengthOfBooking")})
public class VrBooker implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name1")
    private String name1;
    @Size(max = 100)
    @Column(name = "name2")
    private String name2;
    @Size(max = 100)
    @Column(name = "name3")
    private String name3;
    @Size(max = 100)
    @Column(name = "name4")
    private String name4;
    @Size(max = 10)
    @Column(name = "dateOfBooking")
    private String dateOfBooking;
    @Size(max = 5)
    @Column(name = "timeOfBooking")
    private String timeOfBooking;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lengthOfBooking")
    private int lengthOfBooking;

    public VrBooker() {
    }

    public VrBooker(Integer id) {
        this.id = id;
    }

    public VrBooker(Integer id, String name1, int lengthOfBooking) {
        this.id = id;
        this.name1 = name1;
        this.lengthOfBooking = lengthOfBooking;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public String getName4() {
        return name4;
    }

    public void setName4(String name4) {
        this.name4 = name4;
    }

    public String getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(String dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public String getTimeOfBooking() {
        return timeOfBooking;
    }

    public void setTimeOfBooking(String timeOfBooking) {
        this.timeOfBooking = timeOfBooking;
    }

    public int getLengthOfBooking() {
        return lengthOfBooking;
    }

    public void setLengthOfBooking(int lengthOfBooking) {
        this.lengthOfBooking = lengthOfBooking;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VrBooker)) {
            return false;
        }
        VrBooker other = (VrBooker) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.vrarcadebooker.entity.Vrbooker[ id=" + id + " ]";
    }
    
}
