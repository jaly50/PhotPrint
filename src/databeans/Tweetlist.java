package databeans;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class Tweetlist {


	private int    id          = -1;
	private String userTweet     = null;
	private String userScreenName = null;
		
	public int getId(){return id;}
    public String getUserTweet()     { return userTweet;     }
    public String getUserScreenName() { return userScreenName; }
    public void setId(int x){id=x;}
    public void setUserTweet(String s)      { userTweet = s;      }
    public void setUserScreenName(String s)  { userScreenName = s;  }
    
    public String toString() {
    	return "Tweet("+userTweet+")";
    }
}
