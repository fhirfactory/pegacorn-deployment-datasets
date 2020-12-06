package net.fhirfactory.pegacorn.datasets.fhir.r4.base.entities.resource;

public enum InformationConfidentialitySecurityCodeEnum {
    U("unrestricted"),
    L("low"),
    M("moderate"),
    N("normal"),
    R("restricted"),
    V("very restricted");

    private String displayText;

    private InformationConfidentialitySecurityCodeEnum(String confCode){
        this.displayText = confCode;
    }

    public String getDisplayText(){
        return(this.displayText);
    }
}
