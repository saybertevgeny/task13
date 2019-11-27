package ru.lanit.dto;

public class Statistic {

    private long personCount;

    private long carCount;

    private long uniqueVendorCount;

    public Statistic(long personCount, long carCount, long uniqueVendorCount) {
        this.personCount = personCount;
        this.carCount = carCount;
        this.uniqueVendorCount = uniqueVendorCount;
    }

    public long getPersonCount() {
        return personCount;
    }

    public void setPersonCount(long personCount) {
        this.personCount = personCount;
    }

    public long getCarCount() {
        return carCount;
    }

    public void setCarCount(long carCount) {
        this.carCount = carCount;
    }

    public long getUniqueVendorCount() {
        return uniqueVendorCount;
    }

    public void setUniqueVendorCount(long uniqueVendorCount) {
        this.uniqueVendorCount = uniqueVendorCount;
    }
}
