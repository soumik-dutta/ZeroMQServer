import jacksonJson.JsonClass;
import jacksonJson.JsonDecode;
import jacksonJson.User;

import java.util.Map;
import java.util.StringTokenizer;

import controller.ReqResController;
import mongoConnect.MongoSelect;
/**
 * @author soumik
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonDecode jsonDecode=new JsonDecode();
		String request="{\"classType\":\"Auth\",\"name\":\"soumik\",\"password\":\"root\"}";
		
		ReqResController controller=new ReqResController();
		controller.setRequest(request);
		String responseString=controller.mainController();
		System.out.println(responseString);
	}

}
/*git remote add server1 git@github.com:soumik652/ZeroMQServer.git
git push server1 master*/
