package com.patient.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Insurance {

    private long insuranceCompanyId;
    private String insuranceCompany;
    private long memberId;
    private long planId;
    private String benefitsPlan;

    public Insurance(){}

    @Builder(builderMethodName = "insuranceBuilder")
    public Insurance(long insuranceCompanyId, String insuranceCompany, long memberId, long planId, String benefitsPlan) {
        this.insuranceCompanyId = insuranceCompanyId;
        this.insuranceCompany = insuranceCompany;
        this.memberId = memberId;
        this.planId = planId;
        this.benefitsPlan = benefitsPlan;
    }
}
