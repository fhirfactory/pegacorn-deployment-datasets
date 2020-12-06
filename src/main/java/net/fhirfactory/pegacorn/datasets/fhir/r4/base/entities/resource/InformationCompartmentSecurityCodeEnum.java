package net.fhirfactory.pegacorn.datasets.fhir.r4.base.entities.resource;

public enum InformationCompartmentSecurityCodeEnum {
    COMPT("compartment"),
    ACOCOMPT("accountable care organization compartment"),
    CTCOMPT("care team compartment"),
    FMCOMPT("financial management compartment"),
    HRCOMPT("human resource compartment"),
    LRCOMPT("legitimate relationship compartment"),
    PACOMPT("patient administration compartment"),
    RESCOMPT("research project compartment"),
    RMGTCOMPT("records management compartment");

    private String displayText;

    private InformationCompartmentSecurityCodeEnum(String displayText){
        this.displayText = displayText;
    }

    public String getDisplayText(){
        return(this.displayText);
    }

}
