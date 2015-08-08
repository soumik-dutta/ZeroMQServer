package mongoConnect;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class MongoSelect {
	
	/*
	 * @param String that is to be converted into Hashcode
	 * @return String that is converted into Hashcode
	 */
	
	public  String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	
	public Boolean authenticate(String username,String password){
		Boolean status= false;
		 try{   
			 // To connect to mongodb server
	         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	         // Now connect to your databases
	         DB db = mongoClient.getDB( "cloud_tripper" );
			 System.out.println("Connect to database successfully");
//			 String username="root";
//			 String password="root";
	         boolean auth =db.authenticate(username, password.toCharArray());
			 System.out.println("Authentication: "+auth);
			 
	         DBCollection coll = db.getCollection("user_details");
	         System.out.println("Collection user_details selected successfully");
	         /***********************************************************
	          * Find all the data from the document
	          ***********************************************************/
	         /* DBCursor cursor = coll.find();
	         System.out.println("number of document:"+coll.count());
	         int i=1;
	         while (cursor.hasNext()) { 
	            System.out.println("Inserted Document: "+i); 
	            System.out.println(cursor.next()); 
	            i++;
	         } */
	         /************************************************************
	          *  Find the document using where clause
	          ************************************************************/
	         
	         BasicDBObject query = new BasicDBObject("user_id", "soumik")
	         						.append("password", this.getMD5("root").toString());
	         DBCursor cursor = coll.find(query);
	         System.out.println(cursor.count());
	         if(cursor.count()>=1)
	        	 status=true;
	         else
	        	 status=false;
	            
	      }catch(Exception e){
		     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  }
		 
		 // need to be changed
		 return status;
	}
	
}
