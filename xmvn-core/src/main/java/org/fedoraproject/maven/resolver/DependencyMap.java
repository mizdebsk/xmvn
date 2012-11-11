/*-
 * Copyright (c) 2012 Red Hat, Inc.
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
package org.fedoraproject.maven.resolver;

import static org.fedoraproject.maven.utils.Logger.debug;
import static org.fedoraproject.maven.utils.Logger.error;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.fedoraproject.maven.model.Artifact;

public class DependencyMap
{
    private final Map<Artifact, Artifact> mapping = new TreeMap<>();

    public boolean isEmpty()
    {
        return mapping.isEmpty();
    }

    public void addMapping( String groupId, String artifactId, String version, String jppGroupId, String jppArtifactId )
    {
        Artifact mavenArtifact = new Artifact( groupId, artifactId, version );
        Artifact jppArtifact = new Artifact( jppGroupId, jppArtifactId, version );

        addMapping( mavenArtifact, jppArtifact );
    }

    public void addMapping( Artifact from, Artifact to )
    {
        Artifact old = mapping.put( from, to );
        if ( old != null && !old.equals( to ) )
            debug( "Mapping ", from, " => ", old, " was overridden" );

        debug( "Added mapping ", from, " => ", to );
    }

    public Artifact translate( Artifact artifact )
    {
        Set<Artifact> visitedSet = new TreeSet<>();

        Artifact current = artifact.clearVersionAndExtension();
        debug( "Trying to translate artifact ", current );

        while ( !visitedSet.contains( current ) )
        {
            visitedSet.add( current );

            Artifact next = mapping.get( current );
            if ( next == null )
            {
                Artifact result = current.copyVersionAndExtension( artifact );
                debug( "Returning ", result );
                return result;
            }

            debug( "Artifact ", current, " was mapped to ", next );
            current = next;
        }

        error( "Depmap for ", current, " contains a cycle" );
        throw new RuntimeException( "Ddepmap for " + current + " is cyclic" );
    }

    public void writeToFile( File file )
        throws IOException
    {
        PrintStream ps = new PrintStream( file );

        ps.println( "<dependencyMap>" );

        for ( Artifact mavenArtifact : mapping.keySet() )
        {
            Artifact jppArtifact = mapping.get( mavenArtifact );

            ps.println( "  <dependency>" );
            ps.println( "    <maven>" );
            ps.println( "      <groupId>" + mavenArtifact.getGroupId() + "</groupId>" );
            ps.println( "      <artifactId>" + mavenArtifact.getArtifactId() + "</artifactId>" );
            ps.println( "      <version>" + mavenArtifact.getVersion() + "</version>" );
            ps.println( "    </maven>" );
            ps.println( "    <jpp>" );
            ps.println( "      <groupId>" + jppArtifact.getGroupId() + "</groupId>" );
            ps.println( "      <artifactId>" + jppArtifact.getArtifactId() + "</artifactId>" );
            ps.println( "      <version>" + jppArtifact.getVersion() + "</version>" );
            ps.println( "    </jpp>" );
            ps.println( "  </dependency>" );
        }

        ps.println( "</dependencyMap>" );

        ps.close();
    }
}
