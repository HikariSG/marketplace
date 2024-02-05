package com.fdmgroup.OnlineMarketplace;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.fdmgroup.OnlineMarketplace.Controllers.ItemControllerIntegrationtest;
import com.fdmgroup.OnlineMarketplace.Controllers.ItemControllerUnitTest;
import com.fdmgroup.OnlineMarketplace.Controllers.UserControllerIntegrationTest;
import com.fdmgroup.OnlineMarketplace.Controllers.UserControllerUnitTest;

@Suite
@SelectClasses({UserControllerUnitTest.class, UserControllerIntegrationTest.class,
				ItemControllerUnitTest.class, ItemControllerIntegrationtest.class})
public class OnlineMarketplaceApplicationTestSuite {

}
