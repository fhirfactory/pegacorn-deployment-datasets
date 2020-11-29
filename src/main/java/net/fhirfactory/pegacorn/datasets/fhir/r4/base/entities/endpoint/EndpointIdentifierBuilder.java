/*
 * Copyright (c) 2020 Mark A. Hunter
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.fhirfactory.pegacorn.datasets.fhir.r4.base.entities.endpoint;

import net.fhirfactory.pegacorn.datasets.fhir.r4.codesystems.PegacornIdentifierCodeEnum;
import net.fhirfactory.pegacorn.datasets.fhir.r4.codesystems.PegacornIdentifierCodeSystemFactory;
import net.fhirfactory.pegacorn.datasets.fhir.r4.internal.systems.DeploymentInstanceDetailInterface;
import net.fhirfactory.pegacorn.deployment.properties.SystemWideProperties;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Period;
import org.hl7.fhir.r4.model.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Instant;
import java.util.Date;

@ApplicationScoped
public class EndpointIdentifierBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(EndpointIdentifierBuilder.class);

    @Inject
    private PegacornIdentifierCodeSystemFactory pegacornIdentifierCodeSystemFactory;

    @Inject
    private SystemWideProperties systemWideProperties;

    @Inject
    private DeploymentInstanceDetailInterface deploymentInstanceDetailInterface;

    public Identifier constructEndpointIdentifier(String identifierValue){
        LOG.debug(".constructEndpointIdentifier(): Entry");
        String endpointSystem = deploymentInstanceDetailInterface.getDeploymentInstanceSystemEndpointSystem();
        Identifier systemSystemEndpointIdentifier = constructEndpointIdentifier(endpointSystem, identifierValue);
        LOG.debug(".constructEndpointIdentifier(): Exit, created Identifier --> {}", systemSystemEndpointIdentifier);
        return systemSystemEndpointIdentifier;
    }

    public Identifier constructEndpointIdentifier(String identifierSystem, String identifierValue){
        LOG.debug(".constructEndpointIdentifier(): Entry");
        Reference endpointOwner = deploymentInstanceDetailInterface.getDeploymentInstanceSystemOwnerOrganization();
        Identifier systemSystemEndpointIdentifier = constructEndpointIdentifier(endpointOwner , identifierSystem, identifierValue);
        LOG.debug(".constructEndpointIdentifier(): Exit, created Identifier --> {}", systemSystemEndpointIdentifier);
        return systemSystemEndpointIdentifier;
    }

    public Identifier constructEndpointIdentifier(Reference identifierAssigner, String identifierSystem, String identifierValue){
        LOG.debug(".constructEndpointIdentifier(): Entry");
        Identifier systemSystemEndpointIdentifier = new Identifier();
        systemSystemEndpointIdentifier.setUse(Identifier.IdentifierUse.SECONDARY);
        CodeableConcept idType = pegacornIdentifierCodeSystemFactory.buildIdentifierType(PegacornIdentifierCodeEnum.IDENTIFIER_CODE_FHIR_ENDPOINT_SYSTEM);
        systemSystemEndpointIdentifier.setType(idType);
        systemSystemEndpointIdentifier.setSystem(identifierSystem);
        systemSystemEndpointIdentifier.setValue(identifierValue);
        Period validPeriod = new Period();
        validPeriod.setStart(Date.from(Instant.now()));
        systemSystemEndpointIdentifier.setPeriod(validPeriod);
        systemSystemEndpointIdentifier.setAssigner(identifierAssigner);
        LOG.debug(".constructEndpointIdentifier(): Exit, created Identifier --> {}", systemSystemEndpointIdentifier);
        return systemSystemEndpointIdentifier;
    }

    public Reference buildDefaultEndpointReference(){
        Identifier systemEndpointIdentifier = constructEndpointIdentifier(new Reference("Endpoint/"+systemWideProperties.getSystemDeploymentID()),pegacornIdentifierCodeSystemFactory.getPegacornIdentifierCodeSystem(), systemWideProperties.getSystemDeploymentID());
        Reference systemEndpointReference = new Reference();
        systemEndpointReference.setIdentifier(systemEndpointIdentifier);
        systemEndpointReference.setType("Endpoint");
        systemEndpointReference.setDisplay(systemWideProperties.getSystemDeploymentID());
        return(systemEndpointReference);
    }
}
