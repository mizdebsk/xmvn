/*-
 * Copyright (c) 2013 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fedoraproject.maven.config.impl;

import java.util.List;
import java.util.Properties;

import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.codehaus.plexus.logging.Logger;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.fedoraproject.maven.config.Configurator;
import org.fedoraproject.maven.config.RepositoryConfigurator;
import org.fedoraproject.maven.repository.Repository;

/**
 * @author Mikolaj Izdebski
 */
@Component( role = RepositoryConfigurator.class )
public class DefaultRepositoryConfigurator
    implements RepositoryConfigurator
{
    @Requirement
    private Configurator configurator;

    @Requirement
    private PlexusContainer container;

    @Requirement
    private Logger logger;

    private org.fedoraproject.maven.config.Repository findDescriptor( String repoId )
    {
        for ( org.fedoraproject.maven.config.Repository repository : configurator.getConfiguration().getRepositories() )
            if ( repository.getId() != null && repository.getId().equals( repoId ) )
                return repository;

        return null;
    }

    @Override
    public Repository configureRepository( String repoId )
    {
        Properties properties = null;
        Xpp3Dom configurationXml = null;
        String type = null;
        List<String> artifactTypes = null;

        org.fedoraproject.maven.config.Repository desc = findDescriptor( repoId );
        if ( desc == null )
            throw new RuntimeException( "Repository '" + repoId + "' is not configured." );

        properties = desc.getProperties();
        configurationXml = (Xpp3Dom) desc.getConfiguration();
        type = desc.getType();
        artifactTypes = desc.getArtifactTypes();

        if ( type == null )
            throw new RuntimeException( "Repository '" + repoId + "' has missing type." );
        if ( configurationXml == null )
            configurationXml = new Xpp3Dom( "configuration" );

        try
        {
            Repository repository = container.lookup( Repository.class, type );
            repository.configure( artifactTypes, properties, configurationXml );
            return repository;
        }
        catch ( ComponentLookupException e )
        {
            throw new RuntimeException( "Unable to load implementation for repository type '" + type + "'", e );
        }
    }
}