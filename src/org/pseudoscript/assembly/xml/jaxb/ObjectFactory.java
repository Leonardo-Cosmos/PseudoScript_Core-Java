//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.13 at 03:52:27 PM CST 
//


package org.pseudoscript.assembly.xml.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.pseudoscript.assembly.xml.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Assembly_QNAME = new QName("http://www.pseudoscript.org/Assembly", "assembly");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.pseudoscript.assembly.xml.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Operation }
     * 
     */
    public Operation createOperation() {
        return new Operation();
    }

    /**
     * Create an instance of {@link Assembly }
     * 
     */
    public Assembly createAssembly() {
        return new Assembly();
    }

    /**
     * Create an instance of {@link Executor }
     * 
     */
    public Executor createExecutor() {
        return new Executor();
    }

    /**
     * Create an instance of {@link Parameter }
     * 
     */
    public Parameter createParameter() {
        return new Parameter();
    }

    /**
     * Create an instance of {@link Operation.Parameters }
     * 
     */
    public Operation.Parameters createOperationParameters() {
        return new Operation.Parameters();
    }

    /**
     * Create an instance of {@link Assembly.Operations }
     * 
     */
    public Assembly.Operations createAssemblyOperations() {
        return new Assembly.Operations();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Assembly }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.pseudoscript.org/Assembly", name = "assembly")
    public JAXBElement<Assembly> createAssembly(Assembly value) {
        return new JAXBElement<Assembly>(_Assembly_QNAME, Assembly.class, null, value);
    }

}
