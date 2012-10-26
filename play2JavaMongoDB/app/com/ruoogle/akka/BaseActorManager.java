package com.ruoogle.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.RoundRobinRouter;

public class BaseActorManager {
	private ActorSystem system;
	private ActorRef router;
	
	public void shutDown(){
		system.stop(router);
		system.shutdown();
	}

	public BaseActorManager(String systemName,int actor_nums,Class clazz){
		system = ActorSystem.create(systemName);
		router=system.actorOf(new Props(clazz).withRouter(new RoundRobinRouter(actor_nums)));
	}
	
	public BaseActorManager(){
	}
	
	public void sendMessage(Object object){
		router.tell(object);
	}
}
