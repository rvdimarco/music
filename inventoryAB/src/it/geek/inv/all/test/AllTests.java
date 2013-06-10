package it.geek.inv.all.test;

import it.geek.inv.controller.test.InventoryControllerTest;
import it.geek.inv.pojo.test.ProductTest;
import it.geek.inv.service.test.SimpleProductManagerTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({InventoryControllerTest.class,
			   ProductTest.class,
			   SimpleProductManagerTest.class})
public class AllTests {

}
