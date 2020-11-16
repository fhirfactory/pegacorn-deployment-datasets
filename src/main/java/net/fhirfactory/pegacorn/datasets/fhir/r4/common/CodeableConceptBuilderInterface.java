package net.fhirfactory.pegacorn.datasets.fhir.r4.common;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

import java.util.List;

public interface CodeableConceptBuilderInterface {

    public CodeableConcept getCodeableConcept();
    public List<Coding> getCoding();
    public String getText();
}
