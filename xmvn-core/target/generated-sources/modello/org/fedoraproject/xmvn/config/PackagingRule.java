// =================== DO NOT EDIT THIS FILE ====================
// Generated by Modello 1.8.2,
// any modifications will be overwritten.
// ==============================================================

package org.fedoraproject.xmvn.config;

/**
 * Identification of Maven Artifact.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings( "all" )
public class PackagingRule
    implements java.io.Serializable, java.lang.Cloneable
{

      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Pattern specifying one or more Maven artifacts.
     */
    private Artifact artifactGlob;

    /**
     * Name of binary package into which artifacts are assigned.
     */
    private String targetPackage;

    /**
     * 
     *             ID of repository into which artifacts are
     * installed.
     *           
     */
    private String targetRepository;

    /**
     * Field files.
     */
    private java.util.List<String> files;

    /**
     * Field versions.
     */
    private java.util.List<String> versions;

    /**
     * Field aliases.
     */
    private java.util.List<Artifact> aliases;

    /**
     * 
     *             Whether this rule is optional.  Non-optional
     * rules cause
     *             build failure if they are not matched.
     *           
     */
    private Boolean optional = Boolean.valueOf( false );

    /**
     * 
     *             Whether any reactor artifact matches artifact
     * glob pattern
     *             for this rule.  Non-optional rules cause build
     * failure if
     *             they are not matched.
     *           
     */
    private Boolean matched = Boolean.valueOf( false );


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addFile.
     * 
     * @param string
     */
    public void addFile( String string )
    {
        getFiles().add( string );
    } //-- void addFile( String )

    /**
     * Method addVersion.
     * 
     * @param string
     */
    public void addVersion( String string )
    {
        getVersions().add( string );
    } //-- void addVersion( String )

    /**
     * Method clone.
     * 
     * @return PackagingRule
     */
    public PackagingRule clone()
    {
        try
        {
            PackagingRule copy = (PackagingRule) super.clone();

            if ( this.artifactGlob != null )
            {
                copy.artifactGlob = (Artifact) this.artifactGlob.clone();
            }

            if ( this.files != null )
            {
                copy.files = new java.util.ArrayList<String>();
                copy.files.addAll( this.files );
            }

            if ( this.versions != null )
            {
                copy.versions = new java.util.ArrayList<String>();
                copy.versions.addAll( this.versions );
            }

            if ( this.aliases != null )
            {
                copy.aliases = new java.util.ArrayList<Artifact>();
                for ( Artifact item : this.aliases )
                {
                    copy.aliases.add( ( (Artifact) item).clone() );
                }
            }

            return copy;
        }
        catch ( java.lang.Exception ex )
        {
            throw (java.lang.RuntimeException) new java.lang.UnsupportedOperationException( getClass().getName()
                + " does not support clone()" ).initCause( ex );
        }
    } //-- PackagingRule clone()

    /**
     * Method getAliases.
     * 
     * @return List
     */
    public java.util.List<Artifact> getAliases()
    {
        if ( this.aliases == null )
        {
            this.aliases = new java.util.ArrayList<Artifact>();
        }

        return this.aliases;
    } //-- java.util.List<Artifact> getAliases()

    /**
     * Get pattern specifying one or more Maven artifacts.
     * 
     * @return Artifact
     */
    public Artifact getArtifactGlob()
    {
        return this.artifactGlob;
    } //-- Artifact getArtifactGlob()

    /**
     * Method getFiles.
     * 
     * @return List
     */
    public java.util.List<String> getFiles()
    {
        if ( this.files == null )
        {
            this.files = new java.util.ArrayList<String>();
        }

        return this.files;
    } //-- java.util.List<String> getFiles()

    /**
     * Get name of binary package into which artifacts are
     * assigned.
     * 
     * @return String
     */
    public String getTargetPackage()
    {
        return this.targetPackage;
    } //-- String getTargetPackage()

    /**
     * Get iD of repository into which artifacts are installed.
     * 
     * @return String
     */
    public String getTargetRepository()
    {
        return this.targetRepository;
    } //-- String getTargetRepository()

    /**
     * Method getVersions.
     * 
     * @return List
     */
    public java.util.List<String> getVersions()
    {
        if ( this.versions == null )
        {
            this.versions = new java.util.ArrayList<String>();
        }

        return this.versions;
    } //-- java.util.List<String> getVersions()

    /**
     * Get whether any reactor artifact matches artifact glob
     * pattern
     *             for this rule.  Non-optional rules cause build
     * failure if
     *             they are not matched.
     * 
     * @return Boolean
     */
    public Boolean isMatched()
    {
        return this.matched;
    } //-- Boolean isMatched()

    /**
     * Get whether this rule is optional.  Non-optional rules cause
     *             build failure if they are not matched.
     * 
     * @return Boolean
     */
    public Boolean isOptional()
    {
        return this.optional;
    } //-- Boolean isOptional()

    /**
     * Method removeFile.
     * 
     * @param string
     */
    public void removeFile( String string )
    {
        getFiles().remove( string );
    } //-- void removeFile( String )

    /**
     * Method removeVersion.
     * 
     * @param string
     */
    public void removeVersion( String string )
    {
        getVersions().remove( string );
    } //-- void removeVersion( String )

    /**
     * Set alternative identifiers of artifacts.
     * 
     * @param aliases
     */
    public void setAliases( java.util.List<Artifact> aliases )
    {
        this.aliases = aliases;
    } //-- void setAliases( java.util.List )

    /**
     * Set pattern specifying one or more Maven artifacts.
     * 
     * @param artifactGlob
     */
    public void setArtifactGlob( Artifact artifactGlob )
    {
        this.artifactGlob = artifactGlob;
    } //-- void setArtifactGlob( Artifact )

    /**
     * Set files holding the artifact.
     * 
     * @param files
     */
    public void setFiles( java.util.List<String> files )
    {
        this.files = files;
    } //-- void setFiles( java.util.List )

    /**
     * Set whether any reactor artifact matches artifact glob
     * pattern
     *             for this rule.  Non-optional rules cause build
     * failure if
     *             they are not matched.
     * 
     * @param matched
     */
    public void setMatched( Boolean matched )
    {
        this.matched = matched;
    } //-- void setMatched( Boolean )

    /**
     * Set whether this rule is optional.  Non-optional rules cause
     *             build failure if they are not matched.
     * 
     * @param optional
     */
    public void setOptional( Boolean optional )
    {
        this.optional = optional;
    } //-- void setOptional( Boolean )

    /**
     * Set name of binary package into which artifacts are
     * assigned.
     * 
     * @param targetPackage
     */
    public void setTargetPackage( String targetPackage )
    {
        this.targetPackage = targetPackage;
    } //-- void setTargetPackage( String )

    /**
     * Set iD of repository into which artifacts are installed.
     * 
     * @param targetRepository
     */
    public void setTargetRepository( String targetRepository )
    {
        this.targetRepository = targetRepository;
    } //-- void setTargetRepository( String )

    /**
     * Set compatibility versions of the artifact.
     * 
     * @param versions
     */
    public void setVersions( java.util.List<String> versions )
    {
        this.versions = versions;
    } //-- void setVersions( java.util.List )

    
            
    /**
     * Add an alias.
     * @param artifact alias to be added
     */
    public void addAlias( Artifact artifact )
    {
        getAliases().add( artifact );
    }

    /**
     * Remove an alias.
     * @param artifact alias to be removed
     */
    public void removeAlias( Artifact artifact )
    {
        getAliases().remove( artifact );
    }
            
          
}
