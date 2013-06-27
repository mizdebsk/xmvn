/*-
 * Copyright (c) 2012-2013 Red Hat, Inc.
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
package org.fedoraproject.maven.repository;

import java.io.File;

import org.codehaus.plexus.component.annotations.Component;
import org.fedoraproject.maven.model.Artifact;

/**
 * Maven repository layout, as used by upstream Maven.
 * <p>
 * Example: {@code g/r/o/u/p/artifact/ver/artifact-ver.ext}
 * 
 * @author Mikolaj Izdebski
 */
@Component( role = RepositoryType.class, hint = MavenRepository.ROLE_HINT )
public class MavenRepository
    implements Repository
{
    static final String ROLE_HINT = "maven";

    @Override
    public File getArtifactPath( Artifact artifact )
    {
        if ( artifact.isVersionless() )
            return null;

        StringBuilder path = new StringBuilder();

        String groupId = artifact.getGroupId();
        String artifactId = artifact.getArtifactId();
        String version = artifact.getVersion();
        String extension = artifact.getExtension();

        if ( groupId != null )
        {
            path.append( groupId.replace( '.', '/' ) );
            path.append( '/' );
        }

        path.append( artifactId );

        path.append( '/' );
        path.append( version );
        path.append( '/' );
        path.append( artifactId );

        path.append( '-' );
        path.append( version );

        path.append( '.' );
        path.append( extension );

        return new File( path.toString() );
    }
}
