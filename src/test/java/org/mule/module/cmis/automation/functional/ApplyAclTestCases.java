/**
 * (c) 2003-2014 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.module.cmis.automation.functional;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.commons.data.Ace;
import org.apache.chemistry.opencmis.commons.data.Acl;
import org.apache.chemistry.opencmis.commons.data.Principal;
import org.apache.chemistry.opencmis.commons.enums.AclPropagation;
import org.apache.chemistry.opencmis.commons.enums.UnfileObject;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.AccessControlEntryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ApplyAclTestCases extends AbstractTestCases {

    private ObjectId folderObjectId;
    private ObjectId documentObjectId;

    @Before
    public void setUp() throws Exception {
        testData = TestDataBuilder.getTestData("applyAclTestData");
        folderObjectId = getFolderObjectId();
        documentObjectId = getDocumentObjectId(folderObjectId.getId());
    }

    @Test
    public void testApplyAclAdd() {
        List<Ace> addAces = new ArrayList<Ace>();
        try {
            Acl acl = getConnector().getAcl(null, documentObjectId.getId());

            Principal principal = getPrincipal(acl);

            List<String> permissions = new ArrayList<String>();
            permissions.add("cmis:write");
            AccessControlEntryImpl acei = new AccessControlEntryImpl(principal,
                    permissions);
            addAces.add(acei);

            Acl result = getConnector().applyAcl(null, documentObjectId.getId(), addAces, Collections.<Ace>emptyList(), (AclPropagation) testData.get("aclPropagation"));

            assertEquals(2, result.getAces().size());
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @Test
    public void testApplyAclRemove() {
        List<Ace> removeAces = new ArrayList<Ace>();
        List<Ace> addAces = new ArrayList<Ace>();
        Acl result;
        try {
            Acl acl = getConnector().getAcl(null, documentObjectId.getId());
            Principal principal = getPrincipal(acl);

            List<String> firstPermissions = new ArrayList<String>();
            firstPermissions.add("cmis:write");
            AccessControlEntryImpl firstAcei = new AccessControlEntryImpl(principal,
                    firstPermissions);
            addAces.add(firstAcei);

            result = getConnector().applyAcl(null, documentObjectId.getId(), addAces, Collections.<Ace>emptyList(), (AclPropagation) testData.get("aclPropagation"));

            assertEquals(2, result.getAces().size());

            List<String> secondPermissions = new ArrayList<String>();
            secondPermissions.add("cmis:write");
            AccessControlEntryImpl secondAcei = new AccessControlEntryImpl(
                    principal, secondPermissions);
            removeAces.add(secondAcei);

            result = getConnector().applyAcl(null, documentObjectId.getId(), Collections.<Ace>emptyList(), removeAces, (AclPropagation) testData.get("aclPropagation"));

            assertEquals(1, result.getAces().size());
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @Test
    public void testApplyAclAdd_With_CmisObjectRef() {
        List<Ace> addAces = new ArrayList<Ace>();
        try {
            CmisObject cmisObject = getConnector().getObjectById(folderObjectId.getId());

            Acl acl = getConnector().getAcl(cmisObject, null);
            Principal principal = getPrincipal(acl);

            List<String> permissions = new ArrayList<String>();
            permissions.add("cmis:write");
            AccessControlEntryImpl acei = new AccessControlEntryImpl(principal, permissions);
            addAces.add(acei);

            Acl result = getConnector().applyAcl(cmisObject, null, addAces, Collections.<Ace>emptyList(), (AclPropagation) testData.get("aclPropagation"));
            assertEquals(2, result.getAces().size());
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }

    }

    @After
    public void tearDown() throws Exception {
        getConnector().deleteTree(null, folderObjectId.getId(), UnfileObject.DELETE, true, true);
    }

}
