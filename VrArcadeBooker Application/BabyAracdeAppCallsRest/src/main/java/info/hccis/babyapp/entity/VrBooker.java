package info.hccis.babyapp.entity;

import java.io.Serializable;

/**
 * Baby java entity class to hold object attributes
 *
 * @author OB
 * @Since 20200623
 *
 */
public class VrBooker implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name1;
    private String dateOfBooking;
    private String timeOfBooking;
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
        return "VrBooker{" + "id=" + id + ", Booker Name: " + name1 + ", Date Of Booking: " + dateOfBooking + ", Time of Booking: " + timeOfBooking + ", Length of Booking: " + lengthOfBooking + '}';
    }


}
