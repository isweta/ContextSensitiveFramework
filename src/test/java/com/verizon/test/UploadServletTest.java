
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.verizon.test.*;
public class UploadServletTest 
{
  UploadServlet client;
  @BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		client = new CustomerServicesClient();
	}

	@After
	public void tearDown() {
		client.close();
	}

}
