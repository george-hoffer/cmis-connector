/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.cmis.automation.runner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.mule.modules.cmis.automation.unit.CMISConnectorTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ CMISConnectorTest.class })
public class UnitTestSuite {
}
