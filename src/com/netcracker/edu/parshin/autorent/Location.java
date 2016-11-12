package com.netcracker.edu.parshin.autorent;

/**
 *
 * @author Dmitry Parshin, 2016
 */
public class Location {
    private int locationId;
    private int companyId;
    private String address;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
