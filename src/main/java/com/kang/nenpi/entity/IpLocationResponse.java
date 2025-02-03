package com.kang.nenpi.entity;

public class IpLocationResponse {
    
    private String regionName;
    private String city;

    public IpLocationResponse(String regionName, String city){
        this.regionName = regionName;
        this.city = city;
    }

    public String getRegionName() {
        return regionName;
    }
    public String getCity() {
        return city;
    }
    
}
