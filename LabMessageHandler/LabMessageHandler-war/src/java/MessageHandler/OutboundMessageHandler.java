/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageHandler;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.Text;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author dhruvinparikh.byethost24.com
 */
public class OutboundMessageHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public boolean handleMessage(SOAPMessageContext messageContext) {
        Boolean outbound = (Boolean) messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        SOAPMessage msg = messageContext.getMessage();
        if (outbound) {
            try {
                System.out.println("Message outbound");
                System.out.println("---Outbound Message Before---");
                msg.writeTo(System.out);
                SOAPBody soapBody = msg.getSOAPBody();
                NodeList childrenNodes = soapBody.getElementsByTagName("return");
                Node nameNode = childrenNodes.item(0);
                String str = "Hello " + childrenNodes.item(0).getTextContent();
                Iterator it = soapBody.getChildElements();
                SOAPElement opElem = (SOAPElement) it.next();
                it = opElem.getChildElements();
                SOAPElement pin = (SOAPElement) it.next();
                it = pin.getChildElements();
                Text textNode = (Text) it.next();
                textNode.detachNode();
                pin.addTextNode(str);
                msg.saveChanges();
                System.out.println("---Outbound Message After---");
                msg.writeTo(System.out);
                System.out.println("Outbound handler log => " + str);
            } catch (SOAPException | IOException ex) {
                Logger.getLogger(OutboundMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    @Override
    public Set<QName> getHeaders() {
        System.out.println("OutboundMessageHandler : Server : getHeaders()......");
        return null;
    }

    @Override
    public boolean handleFault(SOAPMessageContext messageContext) {
        System.out.println("OutboundMessageHandler : Server : handleFault()......");
        return true;
    }

    @Override
    public void close(MessageContext context) {
        System.out.println("OutboundMessageHandler : Server : close()......");
    }

}
