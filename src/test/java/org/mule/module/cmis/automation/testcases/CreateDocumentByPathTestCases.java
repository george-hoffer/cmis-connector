/**
 * (c) 2003-2014 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.module.cmis.automation.testcases;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.module.cmis.automation.CMISTestParent;
import org.mule.module.cmis.automation.RegressionTests;
import org.mule.module.cmis.automation.SmokeTests;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.List;

import static org.junit.Assert.*;

public class CreateDocumentByPathTestCases extends CMISTestParent {

    private String objectId;
    private String folderId;
    private Folder folder = null;

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("createDocumentByPathTestData");
        upsertOnTestRunMessage("parentObjectId", getRootFolderId());
        folderId = ((ObjectId) runFlowAndGetPayload("create-folder")).getId();
        upsertOnTestRunMessage("folderId", folderId);
    }

    @After
    public void tearDown() throws Exception {
        deleteObject(objectId, true);
        deleteTree(folderId, true, true);
    }

    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testCreateDocumentByPath_rootPath() {
        upsertOnTestRunMessage("folderPath", "/");
        try {
            ObjectId result = runFlowAndGetPayload("create-document-by-path");
            assertNotNull(result.getId());
            assertNotNull(getObjectById(result.getId()));
            objectId = result.getId();
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testCreateDocumentByPath_no_properties() {
        upsertOnTestRunMessage("propertiesRef", null);
        upsertOnTestRunMessage("folderPath", "/");
        try {
            ObjectId result = runFlowAndGetPayload("create-document-by-path");
            assertNotNull(result.getId());
            assertNotNull(getObjectById(result.getId()));
            objectId = result.getId();
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @Category({RegressionTests.class, SmokeTests.class})
    @Test
    public void testCreateDocumentByPath_nonRootPath() {
        upsertOnTestRunMessage("folderPath", "/" + getTestRunMessageValue("folderName"));
        try {
            ObjectId result = runFlowAndGetPayload("create-document-by-path");
            assertNotNull(result.getId());
            assertNotNull(getObjectById(result.getId()));

            objectId = result.getId();
            upsertOnTestRunMessage("objectId", objectId);
            List<Folder> folders = getParentFolders(objectId);
            assertTrue(folders.size() == 1);

            folder = folders.get(0);
            assertEquals(getTestRunMessageValue("folderName"), folder.getName());

        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

}
