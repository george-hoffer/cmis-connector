
package org.mule.modules.cmis.config;

import javax.annotation.Generated;
import org.mule.config.MuleManifest;
import org.mule.modules.cmis.processors.CheckInMessageProcessor;
import org.mule.security.oauth.config.AbstractDevkitBasedDefinitionParser;
import org.mule.security.oauth.config.AbstractDevkitBasedDefinitionParser.ParseDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.BeanDefinitionParsingException;
import org.springframework.beans.factory.parsing.Location;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.7.1", date = "2015-10-14T01:33:34-03:00", comments = "Build UNNAMED.2613.77421cc")
public class CheckInDefinitionParser
    extends AbstractDevkitBasedDefinitionParser
{

    private static Logger logger = LoggerFactory.getLogger(CheckInDefinitionParser.class);

    private BeanDefinitionBuilder getBeanDefinitionBuilder(ParserContext parserContext) {
        try {
            return BeanDefinitionBuilder.rootBeanDefinition(CheckInMessageProcessor.class.getName());
        } catch (NoClassDefFoundError noClassDefFoundError) {
            String muleVersion = "";
            try {
                muleVersion = MuleManifest.getProductVersion();
            } catch (Exception _x) {
                logger.error("Problem while reading mule version");
            }
            logger.error(("Cannot launch the mule app, the @Processor [check-in] within the connector [cmis] is not supported in mule "+ muleVersion));
            throw new BeanDefinitionParsingException(new Problem(("Cannot launch the mule app, the @Processor [check-in] within the connector [cmis] is not supported in mule "+ muleVersion), new Location(parserContext.getReaderContext().getResource()), null, noClassDefFoundError));
        }
    }

    public BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = getBeanDefinitionBuilder(parserContext);
        builder.addConstructorArgValue("checkIn");
        builder.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        if (!hasAttribute(element, "config-ref")) {
            throw new BeanDefinitionParsingException(new Problem("It seems that the config-ref for @Processor [check-in] within the connector [cmis] is null or missing. Please, fill the value with the correct global element.", new Location(parserContext.getReaderContext().getResource()), null));
        }
        parseConfigRef(element, builder);
        if (hasAttribute(element, "document-ref")) {
            if (element.getAttribute("document-ref").startsWith("#")) {
                builder.addPropertyValue("document", element.getAttribute("document-ref"));
            } else {
                builder.addPropertyValue("document", (("#[registry:"+ element.getAttribute("document-ref"))+"]"));
            }
        }
        parseProperty(builder, element, "documentId", "documentId");
        if (hasAttribute(element, "content-ref")) {
            if (element.getAttribute("content-ref").startsWith("#")) {
                builder.addPropertyValue("content", element.getAttribute("content-ref"));
            } else {
                builder.addPropertyValue("content", (("#[registry:"+ element.getAttribute("content-ref"))+"]"));
            }
        }
        parseProperty(builder, element, "filename", "filename");
        parseProperty(builder, element, "mimeType", "mimeType");
        parseProperty(builder, element, "major", "major");
        parseProperty(builder, element, "checkinComment", "checkinComment");
        parseMapAndSetProperty(element, builder, "properties", "properties", "property", new ParseDelegate<String>() {


            public String parse(Element element) {
                return element.getTextContent();
            }

        }
        );
        BeanDefinition definition = builder.getBeanDefinition();
        setNoRecurseOnDefinition(definition);
        attachProcessorDefinition(parserContext, definition);
        return definition;
    }

}
