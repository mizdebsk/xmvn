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
package org.fedoraproject.maven;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.fedoraproject.maven.model.Artifact;
import org.fedoraproject.maven.model.ArtifactGlob;

public class Rule
{
    private final ArtifactGlob pattern;

    private final String replacement;

    public Rule( String pattern, String replacement )
    {
        this.pattern = new ArtifactGlob( pattern );
        this.replacement = replacement;
    }

    public String match( String groupId, String artifactId, String version )
    {
        return pattern.match( groupId, artifactId, version, replacement );
    }

    public Artifact createArtifact( Artifact artifact )
    {
        return pattern.createArtifactFromTemplate( artifact, replacement );
    }

    public static Collection<Rule> parseRules( String text )
    {
        try
        {
            List<Rule> list = new LinkedList<>();
            BufferedReader reader = new BufferedReader( new StringReader( text ) );

            for ( ;; )
            {
                String pattern = reader.readLine();
                String replacement = reader.readLine();
                if ( replacement == null )
                    break;

                list.add( new Rule( pattern, replacement ) );
            }

            return Collections.unmodifiableCollection( list );
        }
        catch ( IOException e )
        {
            throw new RuntimeException( e );
        }
    }
}
