
package org.mule.modules.cmis.processors;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.config.ConfigurationException;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.registry.RegistrationException;
import org.mule.common.DefaultResult;
import org.mule.common.FailureType;
import org.mule.common.Result;
import org.mule.common.metadata.ConnectorMetaDataEnabled;
import org.mule.common.metadata.DefaultMetaData;
import org.mule.common.metadata.DefaultPojoMetaDataModel;
import org.mule.common.metadata.DefaultSimpleMetaDataModel;
import org.mule.common.metadata.MetaData;
import org.mule.common.metadata.MetaDataKey;
import org.mule.common.metadata.MetaDataModel;
import org.mule.common.metadata.OperationMetaDataEnabled;
import org.mule.common.metadata.datatype.DataType;
import org.mule.common.metadata.datatype.DataTypeFactory;
import org.mule.devkit.processor.DevkitBasedMessageProcessor;
import org.mule.modules.cmis.CMISConnector;
import org.mule.modules.cmis.exception.CMISConnectorConnectionException;
import org.mule.modules.cmis.model.VersioningState;
import org.mule.security.oauth.callback.ProcessCallback;


/**
 * CreateDocumentByPathMessageProcessor invokes the {@link org.mule.modules.cmis.CMISConnector#createDocumentByPath(java.lang.String, java.lang.String, java.lang.Object, java.lang.String, org.mule.modules.cmis.model.VersioningState, java.lang.String, java.util.Map, boolean)} method in {@link CMISConnector }. For each argument there is a field in this processor to match it.  Before invoking the actual method the processor will evaluate and transform where possible to the expected argument type.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.7.1", date = "2015-10-14T01:33:34-03:00", comments = "Build UNNAMED.2613.77421cc")
public class CreateDocumentByPathMessageProcessor
    extends DevkitBasedMessageProcessor
    implements MessageProcessor, OperationMetaDataEnabled
{

    protected Object folderPath;
    protected String _folderPathType;
    protected Object filename;
    protected String _filenameType;
    protected Object content;
    protected Object _contentType;
    protected Object mimeType;
    protected String _mimeTypeType;
    protected Object versioningState;
    protected VersioningState _versioningStateType;
    protected Object objectType;
    protected String _objectTypeType;
    protected Object properties;
    protected Map<String, Object> _propertiesType;
    protected Object force;
    protected boolean _forceType;

    public CreateDocumentByPathMessageProcessor(String operationName) {
        super(operationName);
    }

    /**
     * Obtains the expression manager from the Mule context and initialises the connector. If a target object  has not been set already it will search the Mule registry for a default one.
     * 
     * @throws InitialisationException
     */
    public void initialise()
        throws InitialisationException
    {
    }

    @Override
    public void start()
        throws MuleException
    {
        super.start();
    }

    @Override
    public void stop()
        throws MuleException
    {
        super.stop();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    /**
     * Sets content
     * 
     * @param value Value to set
     */
    public void setContent(Object value) {
        this.content = value;
    }

    /**
     * Sets versioningState
     * 
     * @param value Value to set
     */
    public void setVersioningState(Object value) {
        this.versioningState = value;
    }

    /**
     * Sets filename
     * 
     * @param value Value to set
     */
    public void setFilename(Object value) {
        this.filename = value;
    }

    /**
     * Sets folderPath
     * 
     * @param value Value to set
     */
    public void setFolderPath(Object value) {
        this.folderPath = value;
    }

    /**
     * Sets force
     * 
     * @param value Value to set
     */
    public void setForce(Object value) {
        this.force = value;
    }

    /**
     * Sets properties
     * 
     * @param value Value to set
     */
    public void setProperties(Object value) {
        this.properties = value;
    }

    /**
     * Sets objectType
     * 
     * @param value Value to set
     */
    public void setObjectType(Object value) {
        this.objectType = value;
    }

    /**
     * Sets mimeType
     * 
     * @param value Value to set
     */
    public void setMimeType(Object value) {
        this.mimeType = value;
    }

    /**
     * Invokes the MessageProcessor.
     * 
     * @param event MuleEvent to be processed
     * @throws Exception
     */
    public MuleEvent doProcess(final MuleEvent event)
        throws Exception
    {
        Object moduleObject = null;
        try {
            moduleObject = findOrCreate(null, false, event);
            final String _transformedFolderPath = ((String) evaluateAndTransform(getMuleContext(), event, CreateDocumentByPathMessageProcessor.class.getDeclaredField("_folderPathType").getGenericType(), null, folderPath));
            final String _transformedFilename = ((String) evaluateAndTransform(getMuleContext(), event, CreateDocumentByPathMessageProcessor.class.getDeclaredField("_filenameType").getGenericType(), null, filename));
            final Object _transformedContent = ((Object) evaluateAndTransform(getMuleContext(), event, CreateDocumentByPathMessageProcessor.class.getDeclaredField("_contentType").getGenericType(), null, content));
            final String _transformedMimeType = ((String) evaluateAndTransform(getMuleContext(), event, CreateDocumentByPathMessageProcessor.class.getDeclaredField("_mimeTypeType").getGenericType(), null, mimeType));
            final VersioningState _transformedVersioningState = ((VersioningState) evaluateAndTransform(getMuleContext(), event, CreateDocumentByPathMessageProcessor.class.getDeclaredField("_versioningStateType").getGenericType(), null, versioningState));
            final String _transformedObjectType = ((String) evaluateAndTransform(getMuleContext(), event, CreateDocumentByPathMessageProcessor.class.getDeclaredField("_objectTypeType").getGenericType(), null, objectType));
            final Map<String, Object> _transformedProperties = ((Map<String, Object> ) evaluateAndTransform(getMuleContext(), event, CreateDocumentByPathMessageProcessor.class.getDeclaredField("_propertiesType").getGenericType(), null, properties));
            final Boolean _transformedForce = ((Boolean) evaluateAndTransform(getMuleContext(), event, CreateDocumentByPathMessageProcessor.class.getDeclaredField("_forceType").getGenericType(), null, force));
            Object resultPayload;
            final ProcessTemplate<Object, Object> processTemplate = ((ProcessAdapter<Object> ) moduleObject).getProcessTemplate();
            resultPayload = processTemplate.execute(new ProcessCallback<Object,Object>() {


                public List<Class<? extends Exception>> getManagedExceptions() {
                    return Arrays.asList(((Class<? extends Exception> []) new Class[] {CMISConnectorConnectionException.class }));
                }

                public boolean isProtected() {
                    return false;
                }

                public Object process(Object object)
                    throws Exception
                {
                    return ((CMISConnector) object).createDocumentByPath(_transformedFolderPath, _transformedFilename, _transformedContent, _transformedMimeType, _transformedVersioningState, _transformedObjectType, _transformedProperties, _transformedForce);
                }

            }
            , this, event);
            event.getMessage().setPayload(resultPayload);
            return event;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Result<MetaData> getInputMetaData() {
        return new DefaultResult<MetaData>(new DefaultMetaData(getPojoOrSimpleModel(Object.class)));
    }

    @Override
    public Result<MetaData> getOutputMetaData(MetaData inputMetadata) {
        return new DefaultResult<MetaData>(new DefaultMetaData(getPojoOrSimpleModel(ObjectId.class)));
    }

    private MetaDataModel getPojoOrSimpleModel(Class clazz) {
        DataType dataType = DataTypeFactory.getInstance().getDataType(clazz);
        if (DataType.POJO.equals(dataType)) {
            return new DefaultPojoMetaDataModel(clazz);
        } else {
            return new DefaultSimpleMetaDataModel(dataType);
        }
    }

    public Result<MetaData> getGenericMetaData(MetaDataKey metaDataKey) {
        ConnectorMetaDataEnabled connector;
        try {
            connector = ((ConnectorMetaDataEnabled) findOrCreate(null, false, null));
            try {
                Result<MetaData> metadata = connector.getMetaData(metaDataKey);
                if ((Result.Status.FAILURE).equals(metadata.getStatus())) {
                    return metadata;
                }
                if (metadata.get() == null) {
                    return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), "There was an error processing metadata at CMISConnector at createDocumentByPath retrieving was successful but result is null");
                }
                return metadata;
            } catch (Exception e) {
                return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
            }
        } catch (ClassCastException cast) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), "There was an error getting metadata, there was no connection manager available. Maybe you're trying to use metadata from an Oauth connector");
        } catch (ConfigurationException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (RegistrationException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (IllegalAccessException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (InstantiationException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (Exception e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        }
    }

}
