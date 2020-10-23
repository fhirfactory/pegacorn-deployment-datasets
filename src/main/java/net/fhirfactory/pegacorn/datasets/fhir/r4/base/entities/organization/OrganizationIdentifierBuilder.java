package net.fhirfactory.pegacorn.datasets.fhir.r4.base.entities.organization;

import net.fhirfactory.pegacorn.datasets.fhir.r4.base.entities.endpoint.EndpointIdentifierBuilder;
import org.hl7.fhir.r4.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.time.Instant;
import java.util.Date;

@ApplicationScoped
public class OrganizationIdentifierBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(EndpointIdentifierBuilder.class);

    public Identifier constructOrganizationIdentifier(String identifierValue){
        LOG.debug(".constructOrganizationIdentifier(): Entry");
        Identifier systemSystemEndpointIDentifier = new Identifier();
        systemSystemEndpointIDentifier.setUse(Identifier.IdentifierUse.SECONDARY);
        CodeableConcept idType = new CodeableConcept();
        Coding idTypeCoding = new Coding();
        idTypeCoding.setCode("RI");
        idTypeCoding.setSystem("http://terminology.hl7.org/ValueSet/v2-0203");
        idType.getCoding().add(idTypeCoding);
        idType.setText("Generalized Resource Identifier");
        systemSystemEndpointIDentifier.setType(idType);
        systemSystemEndpointIDentifier.setSystem("Pegacorn");
        systemSystemEndpointIDentifier.setValue(identifierValue);
        Period validPeriod = new Period();
        validPeriod.setStart(Date.from(Instant.now()));
        systemSystemEndpointIDentifier.setPeriod(validPeriod);
        systemSystemEndpointIDentifier.setAssigner(new Reference("Organization/FHIRFactory"));
        LOG.debug(".constructOrganizationIdentifier(): Exit, created Identifier --> {}", systemSystemEndpointIDentifier);
        return systemSystemEndpointIDentifier;
    }
}
