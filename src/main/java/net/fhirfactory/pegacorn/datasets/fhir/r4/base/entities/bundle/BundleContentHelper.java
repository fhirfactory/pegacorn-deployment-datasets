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
package net.fhirfactory.pegacorn.datasets.fhir.r4.base.entities.bundle;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BundleContentHelper {
    private static final Logger LOG = LoggerFactory.getLogger(BundleContentHelper.class);

    public Resource extractFirstRepOfType(Bundle bundle, ResourceType resourceType){
        LOG.debug(".extractFirstRepOfType(): Entry, bundle --> {}, resourceType --> {}", bundle, resourceType);
        if(bundle == null || resourceType == null){
            LOG.debug(".extractFirstRepOfType(): Exit, Ether the bundle or the resourceType are null!");
            return(null);
        }
        if(bundle.getTotal() <=0 ){
            LOG.debug(".extractFirstRepOfType(): Exit, Bundle is empty, returning null");
            return(null);
        }
        for(Bundle.BundleEntryComponent currentEntry: bundle.getEntry()){
            Resource currentResource = currentEntry.getResource();
            if(currentResource.getResourceType().equals(resourceType)){
                LOG.debug(".extractFirstRepOfType(): Exit, Found the \"First\" entry, returning it!");
                return(currentResource);
            }
        }
        LOG.debug(".extractFirstRepOfType(): Exit, no Resource found of type specified, returning null");
        return(null);
    }

    public List<Resource> extractAllResourcesOfType(Bundle bundle, ResourceType resourceType){
        LOG.debug(".extractAllResourcesOfType(): Entry, bundle --> {}, resourceType --> {}", bundle, resourceType);
        List<Resource> resourceList = new ArrayList<Resource>();
        if(bundle == null || resourceType == null){
            LOG.debug(".extractAllResourcesOfType(): Exit, Ether the bundle or the resourceType are null, returning empty list!");
            return(resourceList);
        }
        if(bundle.getTotal() <=0 ){
            LOG.debug(".extractAllResourcesOfType(): Exit, Bundle is empty, returning empty list");
            return(resourceList);
        }
        for(Bundle.BundleEntryComponent currentEntry: bundle.getEntry()){
            Resource currentResource = currentEntry.getResource();
            if(currentResource.getResourceType().equals(resourceType)){
                LOG.trace(".extractAllResourcesOfType(): Exit, Found the \"First\" entry, returning it!");
                resourceList.add(currentResource);
            }
        }
        LOG.debug(".extractAllResourcesOfType(): Exit, returning resourceList, number of entries --> {}", resourceList.size());
        return(resourceList);
    }
}
