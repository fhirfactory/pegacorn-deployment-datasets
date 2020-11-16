package net.fhirfactory.pegacorn.datasets.fhir.r4.common;

import org.hl7.fhir.r4.model.CodeableConcept;

public abstract class CodeableConceptBuilderBase implements CodeableConceptBuilderInterface{
    private CodeableConcept concept;

    protected CodeableConcept getConcept() {
        return concept;
    }

    protected void setConcept(CodeableConcept concept) {
        this.concept = concept;
    }
}
