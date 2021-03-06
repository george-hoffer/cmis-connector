/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.cmis.automation.functional;

import static org.junit.Assert.fail;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GetParentFoldersTestCases extends AbstractTestCases {

    private ObjectId folderObjectId;
    private CmisObject folderObjectRef;

    @Before
    public void setUp() throws Exception {
        testData = TestDataBuilder.getTestData("getParentFoldersTestData");
        folderObjectId = getFolderObjectId();
        folderObjectRef = getConnector().getObjectById(folderObjectId.getId());
    }

    @Test
    public void testGetParentFolders() {
        try {
            List<Folder> folders = getConnector().getParentFolders(folderObjectRef, folderObjectId.getId());
            Assert.assertEquals(1, folders.size());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @After
    public void tearDown() throws Exception {
        getConnector().delete(folderObjectRef, folderObjectId.getId(), true);
    }
}
