/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.cmis.runtime;

import org.apache.chemistry.opencmis.commons.exceptions.CmisConnectionException;
import org.mule.modules.cmis.exception.CMISConnectorConnectionException;
import org.mule.modules.cmis.exception.CMISConnectorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CMISFacadeAdaptor {

    private static final Logger logger = LoggerFactory.getLogger(CMISFacadeAdaptor.class);

    private CMISFacadeAdaptor() {

    }

    public static CMISFacade adapt(CMISFacade facade) {
        return (CMISFacade) Proxy.newProxyInstance(CMISFacadeAdaptor.class.getClassLoader(), new Class<?>[] { CMISFacade.class }, new MyInvocationHandler(facade));
    }

    private static class MyInvocationHandler implements InvocationHandler {

        private final CMISFacade facade;

        private MyInvocationHandler(CMISFacade facade) {
            this.facade = facade;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            if (logger.isDebugEnabled()) {
                logger.debug("Invoked method {0} with arguments {1}", method.getName(), args);
            }

            try {
                Object ret = method.invoke(facade, args);

                if (logger.isDebugEnabled()) {
                    logger.debug("Returned method {0} with value {1}", ret);
                }
                return ret;
            } catch (InvocationTargetException e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Method " + method.getName() + " thew " + e.getClass(), e);
                }
                Throwable cause = e.getCause();

                if (cause instanceof CmisConnectionException) {
                    throw new CMISConnectorConnectionException(cause);
                } else if (cause instanceof CMISConnectorConnectionException || cause instanceof RuntimeException) {
                    throw (RuntimeException) cause;
                } else {
                    throw new CMISConnectorException(cause);
                }
            }
        }
    }
}