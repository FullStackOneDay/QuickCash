
/*
This Java source file was generated by test-to-java.xsl
and is a derived work from the source document.
The source document contained the following notice:


Copyright (c) 2001-2004 World Wide Web Consortium,
(Massachusetts Institute of Technology, Institut National de
Recherche en Informatique et en Automatique, Keio University). All
Rights Reserved. This program is distributed under the W3C's Software
Intellectual Property License. This program is distributed in the
hope that it will be useful, but WITHOUT ANY WARRANTY; without even
the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
PURPOSE.
See W3C License http://www.w3.org/Consortium/Legal/ for more details.

*/

package org.w3c.domts.level1.core;

import org.w3c.dom.*;


import org.w3c.domts.DOMTestCase;
import org.w3c.domts.DOMTestDocumentBuilderFactory;



/**
 *     The "createCDATASection(data)" method creates a new 
 *    CDATASection node whose value is the specified string.
 *    Retrieve the entire DOM document and invoke its 
 *    "createCDATASection(data)" method.  It should create a
 *    new CDATASection node whose "data" is the specified 
 *    string.  The content, name and type are retrieved and
 *    output.
* @author NIST
* @author Mary Brady
* @see <a href="http://www.w3.org/TR/1998/REC-DOM-Level-1-19981001/level-one-core#ID-D26C0AF8">http://www.w3.org/TR/1998/REC-DOM-Level-1-19981001/level-one-core#ID-D26C0AF8</a>
*/
public final class documentcreatecdatasection extends DOMTestCase {

   /**
    * Constructor.
    * @param factory document factory, may not be null
    * @throws org.w3c.domts.DOMTestIncompatibleException Thrown if test is not compatible with parser configuration
    */
   public documentcreatecdatasection(final DOMTestDocumentBuilderFactory factory)  throws org.w3c.domts.DOMTestIncompatibleException {
      super(factory);

    //
    //   check if loaded documents are supported for content type
    //
    String contentType = getContentType();
    preload(contentType, "staff", true);
    }

   /**
    * Runs the test case.
    * @throws Throwable Any uncaught exception causes test to fail
    */
   public void runTest() throws Throwable {
      Document doc;
      CDATASection newCDATASectionNode;
      String newCDATASectionValue;
      String newCDATASectionName;
      int newCDATASectionType;
      doc = (Document) load("staff", true);
      newCDATASectionNode = doc.createCDATASection("This is a new CDATASection node");
      newCDATASectionValue = newCDATASectionNode.getNodeValue();
      assertEquals("nodeValue", "This is a new CDATASection node", newCDATASectionValue);
      newCDATASectionName = newCDATASectionNode.getNodeName();
      assertEquals("nodeName", "#cdata-section", newCDATASectionName);
      newCDATASectionType = (int) newCDATASectionNode.getNodeType();
      assertEquals("nodeType", 4, newCDATASectionType);
      }
   /**
    *  Gets URI that identifies the test.
    *  @return uri identifier of test
    */
   public String getTargetURI() {
      return "http://www.w3.org/2001/DOM-Test-Suite/level1/core/documentcreatecdatasection";
   }
   /**
    * Runs this test from the command line.
    * @param args command line arguments
    */
   public static void main(final String[] args) {
        DOMTestCase.doMain(documentcreatecdatasection.class, args);
   }
}

