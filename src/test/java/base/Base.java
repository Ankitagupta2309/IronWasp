package base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import rest.impl.IronWasp;

public class Base {

	@BeforeClass
	public void startIronWasp() {
		IronWasp.workflowStart();
	}

	@AfterClass
	public void endIronWasp() {
		IronWasp.workflowEnd();
	}
}
