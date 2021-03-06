/*
 Copyright (c) 2002, 2010, Oracle and/or its affiliates. All rights reserved.
 

  The MySQL Connector/J is licensed under the terms of the GPLv2
  <http://www.gnu.org/licenses/old-licenses/gpl-2.0.html>, like most MySQL Connectors.
  There are special exceptions to the terms and conditions of the GPLv2 as it is applied to
  this software, see the FLOSS License Exception
  <http://www.mysql.com/about/legal/licensing/foss-exception.html>.

  This program is free software; you can redistribute it and/or modify it under the terms
  of the GNU General Public License as published by the Free Software Foundation; version 2
  of the License.

  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
  See the GNU General Public License for more details.

  You should have received a copy of the GNU General Public License along with this
  program; if not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth
  Floor, Boston, MA 02110-1301  USA



 */
package testsuite.regression;

import java.util.Properties;

import sun.applet.AppletSecurity;
import testsuite.BaseTestCase;

/**
 * Tests various applet-related issues.
 * 
 * @author Mark Matthews
 * @version $Id: AppletRegressionTest.java,v 1.1.2.1 2005/05/13 18:58:38
 *          mmatthews Exp $
 */
public class AppletRegressionTest extends BaseTestCase {
	private final static String TOGGLE_RUN_PROPERTY = "com.mysql.jdbc.testsuite.regression.runAppletRegressionTest";

	/**
	 * DOCUMENT ME!
	 * 
	 * @param name
	 */
	public AppletRegressionTest(String name) {
		super(name);
	}

	/**
	 * Runs all test cases in this test suite
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty(TOGGLE_RUN_PROPERTY, "true");
		junit.textui.TestRunner.run(AppletRegressionTest.class);
	}

	/**
	 * Tests if the driver wors with an Applet security manager installed.
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	public void testAppletSecurityManager() throws Exception {
		if ("true".equalsIgnoreCase(System.getProperty(TOGGLE_RUN_PROPERTY))) {
			System.setSecurityManager(new CustomAppletSecurity());

			getConnectionWithProps(new Properties());
		}
	}

	/**
	 * We need to customize the security manager a 'bit', so that JUnit still
	 * works (and we can connect to various databases).
	 */
	class CustomAppletSecurity extends AppletSecurity {
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.SecurityManager#checkAccess(java.lang.Thread)
		 */
		public synchronized void checkAccess(Thread arg0) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.SecurityManager#checkConnect(java.lang.String, int,
		 *      java.lang.Object)
		 */
		public void checkConnect(String host, int port, Object context) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.SecurityManager#checkConnect(java.lang.String, int)
		 */
		public void checkConnect(String host, int port) {
		}
	}
}
