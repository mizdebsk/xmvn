/*-
 * Copyright (c) 2014 Red Hat, Inc.
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
package org.fedoraproject.xmvn.tools.install.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;

/**
 * @author Mikolaj Izdebski
 */
public abstract class AbstractFileTest
{
    private final List<File> files = new ArrayList<>();

    private Path installRoot;

    private final List<String> descriptors = new ArrayList<>();

    protected void add( File file )
        throws Exception
    {
        files.add( file );
    }

    protected Path performInstallation()
        throws Exception
    {
        try
        {
            String testName = getClass().getName();
            Path workPath = Paths.get( "target" ).resolve( "test-work" );
            Files.createDirectories( workPath );
            installRoot = Files.createTempDirectory( workPath, testName );

            for ( File file : files )
                file.install( installRoot );

            for ( File file : files )
                descriptors.add( file.getDescriptor() );

            return installRoot;
        }
        finally
        {
            files.clear();
        }
    }

    protected void assertDirectoryStructure( String... expected )
        throws Exception
    {
        assertDirectoryStructure( installRoot, expected );
    }

    protected void assertDirectoryStructure( Path root, String... expected )
        throws Exception
    {
        List<String> actualList = new ArrayList<>();
        Files.walkFileTree( root, new FileSystemWalker( root, actualList ) );
        assertEqualsImpl( actualList, "directory structure", expected );
    }

    protected void assertDescriptorEquals( String... expected )
    {
        assertEqualsImpl( descriptors, "file descriptor", expected );
    }

    private void assertEqualsImpl( List<String> actualList, String what, String... expected )
    {
        List<String> expectedList = new ArrayList<>();
        for ( String string : expected )
            expectedList.add( string );

        Collections.sort( expectedList );
        Collections.sort( actualList );

        try
        {
            Iterator<String> expectedIterator = expectedList.iterator();
            Iterator<String> actualIterator = actualList.iterator();

            while ( expectedIterator.hasNext() && actualIterator.hasNext() )
                assertEquals( expectedIterator.next(), actualIterator.next() );

            assertFalse( expectedIterator.hasNext() );
            assertFalse( actualIterator.hasNext() );
        }
        catch ( AssertionError e )
        {
            System.err.println( "EXPECTED " + what + ":" );
            for ( String string : expectedList )
                System.err.println( "  " + string );

            System.err.println( "ACTUAL " + what + ":" );
            for ( String string : actualList )
                System.err.println( "  " + string );

            throw e;
        }
    }

    Path getResource(String name) {
        return Paths.get( "src/test/resources/", name  ).toAbsolutePath();
    }

    void assertFilesEqual(Path expected, Path actual) throws IOException {
        byte expectedContent[] = Files.readAllBytes( expected );
        byte actualContent[] = Files.readAllBytes( actual );
        assertTrue( Arrays.equals( expectedContent, actualContent ));
    }
}
