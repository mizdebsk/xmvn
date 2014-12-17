// =================== DO NOT EDIT THIS FILE ====================
// Generated by Modello 1.8.2,
// any modifications will be overwritten.
// ==============================================================

package org.fedoraproject.xmvn.metadata;

/**
 * 
 *         Root element of the metadata file.
 *       
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings( "all" )
public class PackageMetadata
    implements java.io.Serializable, java.lang.Cloneable
{

      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 
     *             Universally unique identifier of this piece of
     * metadata.
     *           
     */
    private String uuid;

    /**
     * Field properties.
     */
    private java.util.Properties properties;

    /**
     * Field artifacts.
     */
    private java.util.List<ArtifactMetadata> artifacts;

    /**
     * Field skippedArtifacts.
     */
    private java.util.List<SkippedArtifactMetadata> skippedArtifacts;

    /**
     * Field modelEncoding.
     */
    private String modelEncoding = "UTF-8";


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addArtifact.
     * 
     * @param artifactMetadata
     */
    public void addArtifact( ArtifactMetadata artifactMetadata )
    {
        getArtifacts().add( artifactMetadata );
    } //-- void addArtifact( ArtifactMetadata )

    /**
     * Method addProperty.
     * 
     * @param key
     * @param value
     */
    public void addProperty( String key, String value )
    {
        getProperties().put( key, value );
    } //-- void addProperty( String, String )

    /**
     * Method addSkippedArtifact.
     * 
     * @param skippedArtifactMetadata
     */
    public void addSkippedArtifact( SkippedArtifactMetadata skippedArtifactMetadata )
    {
        getSkippedArtifacts().add( skippedArtifactMetadata );
    } //-- void addSkippedArtifact( SkippedArtifactMetadata )

    /**
     * Method clone.
     * 
     * @return PackageMetadata
     */
    public PackageMetadata clone()
    {
        try
        {
            PackageMetadata copy = (PackageMetadata) super.clone();

            if ( this.properties != null )
            {
                copy.properties = (java.util.Properties) this.properties.clone();
            }

            if ( this.artifacts != null )
            {
                copy.artifacts = new java.util.ArrayList<ArtifactMetadata>();
                for ( ArtifactMetadata item : this.artifacts )
                {
                    copy.artifacts.add( ( (ArtifactMetadata) item).clone() );
                }
            }

            if ( this.skippedArtifacts != null )
            {
                copy.skippedArtifacts = new java.util.ArrayList<SkippedArtifactMetadata>();
                for ( SkippedArtifactMetadata item : this.skippedArtifacts )
                {
                    copy.skippedArtifacts.add( ( (SkippedArtifactMetadata) item).clone() );
                }
            }

            return copy;
        }
        catch ( java.lang.Exception ex )
        {
            throw (java.lang.RuntimeException) new java.lang.UnsupportedOperationException( getClass().getName()
                + " does not support clone()" ).initCause( ex );
        }
    } //-- PackageMetadata clone()

    /**
     * Method getArtifacts.
     * 
     * @return List
     */
    public java.util.List<ArtifactMetadata> getArtifacts()
    {
        if ( this.artifacts == null )
        {
            this.artifacts = new java.util.ArrayList<ArtifactMetadata>();
        }

        return this.artifacts;
    } //-- java.util.List<ArtifactMetadata> getArtifacts()

    /**
     * Get the modelEncoding field.
     * 
     * @return String
     */
    public String getModelEncoding()
    {
        return this.modelEncoding;
    } //-- String getModelEncoding()

    /**
     * Method getProperties.
     * 
     * @return Properties
     */
    public java.util.Properties getProperties()
    {
        if ( this.properties == null )
        {
            this.properties = new java.util.Properties();
        }

        return this.properties;
    } //-- java.util.Properties getProperties()

    /**
     * Method getSkippedArtifacts.
     * 
     * @return List
     */
    public java.util.List<SkippedArtifactMetadata> getSkippedArtifacts()
    {
        if ( this.skippedArtifacts == null )
        {
            this.skippedArtifacts = new java.util.ArrayList<SkippedArtifactMetadata>();
        }

        return this.skippedArtifacts;
    } //-- java.util.List<SkippedArtifactMetadata> getSkippedArtifacts()

    /**
     * Get universally unique identifier of this piece of metadata.
     * 
     * @return String
     */
    public String getUuid()
    {
        return this.uuid;
    } //-- String getUuid()

    /**
     * Method removeArtifact.
     * 
     * @param artifactMetadata
     */
    public void removeArtifact( ArtifactMetadata artifactMetadata )
    {
        getArtifacts().remove( artifactMetadata );
    } //-- void removeArtifact( ArtifactMetadata )

    /**
     * Method removeSkippedArtifact.
     * 
     * @param skippedArtifactMetadata
     */
    public void removeSkippedArtifact( SkippedArtifactMetadata skippedArtifactMetadata )
    {
        getSkippedArtifacts().remove( skippedArtifactMetadata );
    } //-- void removeSkippedArtifact( SkippedArtifactMetadata )

    /**
     * Set list of installed artifacts described by this piece of
     *             metadata.
     * 
     * @param artifacts
     */
    public void setArtifacts( java.util.List<ArtifactMetadata> artifacts )
    {
        this.artifacts = artifacts;
    } //-- void setArtifacts( java.util.List )

    /**
     * Set the modelEncoding field.
     * 
     * @param modelEncoding
     */
    public void setModelEncoding( String modelEncoding )
    {
        this.modelEncoding = modelEncoding;
    } //-- void setModelEncoding( String )

    /**
     * Set properties of this piece of metadata.
     * 
     * @param properties
     */
    public void setProperties( java.util.Properties properties )
    {
        this.properties = properties;
    } //-- void setProperties( java.util.Properties )

    /**
     * Set list of artifacts built but not installed in any
     * package.
     *             Useful for detecting broken package
     * dependencies.
     * 
     * @param skippedArtifacts
     */
    public void setSkippedArtifacts( java.util.List<SkippedArtifactMetadata> skippedArtifacts )
    {
        this.skippedArtifacts = skippedArtifacts;
    } //-- void setSkippedArtifacts( java.util.List )

    /**
     * Set universally unique identifier of this piece of metadata.
     * 
     * @param uuid
     */
    public void setUuid( String uuid )
    {
        this.uuid = uuid;
    } //-- void setUuid( String )

}