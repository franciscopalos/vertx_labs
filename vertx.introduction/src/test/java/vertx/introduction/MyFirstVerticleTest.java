package vertx.introduction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.fmpb.vertx.introduction.MyFirstVerticle;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class MyFirstVerticleTest {

	private Vertx vertx;
	
	@Before
	public void setUp(TestContext context){
		vertx = Vertx.vertx();
		vertx.deployVerticle(MyFirstVerticle.class.getName(), context.asyncAssertSuccess());
	}
	
	@After
	public void tearDown(TestContext context){
		vertx.close(context.asyncAssertSuccess());
	}
	
	@Test
	public void testMyApplication(TestContext context){
		
		final Async async = context.async();
		
		//create client  to test the first vert.x server application
		vertx.createHttpClient().getNow(8080, "localhost", "/", 
				response -> {
					response.handler(body ->{
						context.assertTrue(body.toString().contains("jungle"));
						async.complete();
					});
				});
		
	}
}
