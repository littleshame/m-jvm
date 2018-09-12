package jvm.test;

import jvm.engine.MiniJVM;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

	public class MiniJVMTest {

		static final String PATH = "D:\\IntelliJ IDEA 2017.1.6\\workspace\\mutil\\out\\production\\mutil";
		@Before
		public void setUp() throws Exception {
		}

		@After
		public void tearDown() throws Exception {
		}

	@Test
	public void testMain() throws Exception{
		String[] classPaths = {PATH};
		MiniJVM jvm = new MiniJVM();

		jvm.run(classPaths, "jvm.test.EmployeeV1");

	}

}
