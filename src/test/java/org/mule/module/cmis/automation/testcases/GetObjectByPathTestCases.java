/**
 * (c) 2003-2014 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.module.cmis.automation.testcases;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.module.cmis.automation.CMISTestParent;
import org.mule.module.cmis.automation.RegressionTests;
import org.mule.modules.tests.ConnectorTestUtils;

import static org.junit.Assert.*;

public class GetObjectByPathTestCases extends CMISTestParent {

    private String folderId;
    private String documentId;

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("getObjectByPathTestData");
        upsertOnTestRunMessage("parentObjectId", getRootFolderId());

        folderId = ((ObjectId) runFlowAndGetPayload("create-folder")).getId();
        upsertOnTestRunMessage("folderId", folderId);

        documentId = ((ObjectId) runFlowAndGetPayload("create-document-by-id")).getId();
        upsertOnTestRunMessage("path", "/" + getTestRunMessageValue("folderName") + "/" + getTestRunMessageValue("filename"));

    }

    @Category({RegressionTests.class})
    @Test
    public void testGetObjectByPath() {
        try {
            CmisObject cmisObj = runFlowAndGetPayload("get-object-by-path");
            assertNotNull(cmisObj);
            assertEquals(getTestRunMessageValue("filename"), cmisObj.getName());
            assertEquals(documentId, cmisObj.getId());
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        deleteTree(folderId, true, true);
    }
}
