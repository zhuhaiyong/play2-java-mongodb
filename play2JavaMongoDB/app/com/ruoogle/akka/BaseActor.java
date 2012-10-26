package com.ruoogle.akka;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.japi.Option;

public class BaseActor extends UntypedActor {
	@Override
	public void onReceive(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	//Actors are automatically started asynchronously when created.
	//When you create the UntypedActor then it will automatically call the preStart callback method on the UntypedActor class. 
	//This is an excellent place to add initialization code for the actor.
	//Actor在被创建的时候就自动的以异步方式启动了。
	//当你创建UntypedActor的时候，它会自动调用UntypedActor里的preStart回调方法。
	//这里是一个为actor添加初始化方法的好地方。
	@Override
	public void preStart() {
	    // initialization code
		System.err.println(self().path().name()+" start......");
	}
	  
	//postRestart方法会被导致restart的异常调用。
	@Override
	public void postRestart(Throwable reason) {
		preStart();
		System.err.println(self().path().name()+" restart......");
	}
	
	//After stopping an actor, its postStop hook is called, which may be used e.g. for deregistering this actor from other services. 
	//This hook is guaranteed to run after message queuing has been disabled for this actor, 
	//i.e. messages sent to a stopped actor will be redirected to the deadLetters of the ActorSystem.
	//关闭一个actor时，它的postStop hook会被调用，可能被用来从其他服务注销这个actor。
	//这个钩子保证是在这个actor的消息队列被禁用后执行，
	//发送给已经关闭的actor的消息会被转发到ActorSystem的deadLetters。
	@Override
	public void postStop() {
		System.err.println(self().path().name()+" stop......");
	}
	
	
	public void preRestart(Throwable reason, Option<Object> message) {
	    for (ActorRef each : getContext().getChildren())
	      getContext().stop(each);
	    
	    postStop();
	}
}
