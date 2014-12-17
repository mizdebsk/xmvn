// =================== DO NOT EDIT THIS FILE ====================
// Generated by Modello 1.8.2,
// any modifications will be overwritten.
// ==============================================================

package org.fedoraproject.xmvn.config;

/**
 * Basic build settings.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings( "all" )
public class BuildSettings
    implements java.io.Serializable, java.lang.Cloneable
{

      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 
     *             Whether detailed debugging information about the
     * build
     *             process should be logged.
     *           
     */
    private Boolean debug;

    /**
     * 
     *             Whether compilation and execution of unit and
     * integration tests should be skipped.
     *           
     */
    private Boolean skipTests;

    /**
     * 
     *             Compatibility version of Java sources in the
     * reactor. If
     *             not specified then it defaults to 1.5 or
     * whatever is
     *             specified in configuration of Maven Compiler
     * Plugin.
     *           
     */
    private String compilerSource;


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method clone.
     * 
     * @return BuildSettings
     */
    public BuildSettings clone()
    {
        try
        {
            BuildSettings copy = (BuildSettings) super.clone();

            return copy;
        }
        catch ( java.lang.Exception ex )
        {
            throw (java.lang.RuntimeException) new java.lang.UnsupportedOperationException( getClass().getName()
                + " does not support clone()" ).initCause( ex );
        }
    } //-- BuildSettings clone()

    /**
     * Get compatibility version of Java sources in the reactor. If
     *             not specified then it defaults to 1.5 or
     * whatever is
     *             specified in configuration of Maven Compiler
     * Plugin.
     * 
     * @return String
     */
    public String getCompilerSource()
    {
        return this.compilerSource;
    } //-- String getCompilerSource()

    /**
     * Get whether detailed debugging information about the build
     *             process should be logged.
     * 
     * @return Boolean
     */
    public Boolean isDebug()
    {
        return this.debug;
    } //-- Boolean isDebug()

    /**
     * Get whether compilation and execution of unit and
     * integration tests should be skipped.
     * 
     * @return Boolean
     */
    public Boolean isSkipTests()
    {
        return this.skipTests;
    } //-- Boolean isSkipTests()

    /**
     * Set compatibility version of Java sources in the reactor. If
     *             not specified then it defaults to 1.5 or
     * whatever is
     *             specified in configuration of Maven Compiler
     * Plugin.
     * 
     * @param compilerSource
     */
    public void setCompilerSource( String compilerSource )
    {
        this.compilerSource = compilerSource;
    } //-- void setCompilerSource( String )

    /**
     * Set whether detailed debugging information about the build
     *             process should be logged.
     * 
     * @param debug
     */
    public void setDebug( Boolean debug )
    {
        this.debug = debug;
    } //-- void setDebug( Boolean )

    /**
     * Set whether compilation and execution of unit and
     * integration tests should be skipped.
     * 
     * @param skipTests
     */
    public void setSkipTests( Boolean skipTests )
    {
        this.skipTests = skipTests;
    } //-- void setSkipTests( Boolean )

}