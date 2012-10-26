import com.ruoogle.akka.BaseActorManager;
import com.ruoogle.comm.util.UniqueIDUtil;

public class MyTest {
	public static void main(String[] args){
		BaseActorManager manager=new BaseActorManager("uniqueid",2,UniqueIDUtil.class);
		
	}
}
