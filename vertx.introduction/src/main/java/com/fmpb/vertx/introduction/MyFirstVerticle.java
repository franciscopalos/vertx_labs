package com.fmpb.vertx.introduction;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Hello world!
 *
 */
public class MyFirstVerticle extends AbstractVerticle
{
   
	@Override
	public void start(Future<Void> future){
		vertx.createHttpServer().requestHandler(r ->{r.response().end("<h1>Welcome to the jungle of " +
	              "Vert.x 3 application</h1>");})
		.listen(8080,result ->{
			if(result.succeeded()){
				future.complete();
			}else{
				future.fail(result.cause());
			}
		});
		
	}
	
}
