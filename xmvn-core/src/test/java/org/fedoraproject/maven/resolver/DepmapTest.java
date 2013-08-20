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
package org.fedoraproject.maven.resolver;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.plexus.PlexusTestCase;
import org.fedoraproject.maven.config.Configurator;
import org.fedoraproject.maven.config.ResolverSettings;
import org.fedoraproject.maven.model.ArtifactImpl;

/**
 * @author Mikolaj Izdebski
 */
public class DepmapTest
    extends PlexusTestCase
{
    private DependencyMap readDepmap( Path fragment )
        throws Exception
    {
        Configurator configurator = lookup( Configurator.class );
        ResolverSettings settings = configurator.getConfiguration().getResolverSettings();
        settings.getPrefixes().clear();
        settings.addPrefix( new File( "." ).getAbsolutePath() );
        settings.getMetadataRepositories().clear();
        settings.addMetadataRepository( fragment.toString() );
        return lookup( DependencyMap.class );
    }

    private DependencyMap readDepmap( String xml )
        throws Exception
    {
        Path fragment = Files.createTempFile( "depmap-", ".xml" );
        fragment.toFile().deleteOnExit();
        try (FileWriter writer = new FileWriter( fragment.toFile() ))
        {
            writer.write( xml );
        }
        return readDepmap( fragment );
    }

    /**
     * Test if empty fragment files are handled correctly.
     * 
     * @throws Exception
     */
    public void testEmptyFragment()
        throws Exception
    {
        DependencyMap depmap = readDepmap( "" );
        assertTrue( depmap.isEmpty() );
        ArtifactImpl fooBar = new ArtifactImpl( "foo", "bar" );
        List<ArtifactImpl> translationResult = depmap.translate( fooBar );
        assertEquals( translationResult.size(), 1 );
        assertTrue( translationResult.contains( fooBar ) );
    }

    /**
     * Test if invalid fragment files are handled correctly.
     * 
     * @throws Exception
     */
    public void testMalformedFragment()
        throws Exception
    {
        DependencyMap depmap = readDepmap( "Lorem ipsum dolor sit amet" );
        assertTrue( depmap.isEmpty() );
    }

    /**
     * Test if nonexistent fragment files don't cause problems.
     * 
     * @throws Exception
     */
    public void testNonexistentFragmentFile()
        throws Exception
    {
        DependencyMap depmap = readDepmap( Paths.get( "/this/should/not/exist" ) );
        assertTrue( depmap.isEmpty() );
        release( depmap );
    }

    /**
     * Test parsing of some fragment files.
     * 
     * @throws Exception
     */
    public void testSampleDepmaps()
        throws Exception
    {
        List<String> depmaps = new ArrayList<>();
        depmaps.add( "old-style-depmap" );
        depmaps.add( "new-style-depmap.xml" );
        depmaps.add( "indirect-depmap.xml" );
        depmaps.add( "cyclic-depmap.xml" );
        depmaps.add( "xml-depmap.xml" );

        for ( String file : depmaps )
        {
            Path path = Paths.get( "src/test/resources" ).resolve( file );
            DependencyMap depmap = readDepmap( path );
            assertFalse( depmap.isEmpty() );
            ArtifactImpl commonsIo = new ArtifactImpl( "commons-io", "commons-io" );
            ArtifactImpl apacheCommonsIo = new ArtifactImpl( "org.apache.commons", "commons-io" );
            ArtifactImpl jppCommonsIo = new ArtifactImpl( "JPP", "commons-io" );
            assertTrue( depmap.translate( commonsIo ).contains( jppCommonsIo ) );
            assertTrue( depmap.translate( apacheCommonsIo ).contains( jppCommonsIo ) );
            release( depmap );
        }
    }

    /**
     * Test if namespaces work and if artifacts in different namespaces are matched.
     * 
     * @throws Exception
     */
    public void testNamespaceMatching()
        throws Exception
    {
        Path path = Paths.get( "src/test/resources/namespaced-depmap.xml" );
        DependencyMap depmap = readDepmap( path );
        assertFalse( depmap.isEmpty() );
        ArtifactImpl commonsIo = new ArtifactImpl( "commons-io", "commons-io" );
        ArtifactImpl apacheCommonsIo = new ArtifactImpl( "org.apache.commons", "commons-io" );
        ArtifactImpl jppCommonsIo = new ArtifactImpl( "JPP", "commons-io" );
        assertTrue( depmap.translate( commonsIo ).contains( jppCommonsIo ) );
        assertTrue( depmap.translate( apacheCommonsIo ).contains( jppCommonsIo ) );
        release( depmap );
    }
}
