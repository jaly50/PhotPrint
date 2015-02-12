package databeans;

import java.util.ArrayList;

import org.genericdao.PrimaryKey;

@PrimaryKey("photo_id")

public class Photo {
	private String photo_id;
	private String secret;
	private String photo_url;
	private String photo_href;
	private ArrayList<String> tags;
	private int count_like;
	private int count_dislike;
	
	
	public String getPhoto_id() {
		return photo_id;
	}
	public void setPhoto_id(String photo_id) {
		this.photo_id = photo_id;
	}
	
	
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	
	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	
	
	public String getPhoto_href() {
		return photo_href;
	}
	public void setPhoto_href(String photo_href) {
		this.photo_href = photo_href;
	}
	
	public ArrayList<String> getTags() {
		return tags;
	}
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	
	
	public int getCount_like() {
		return count_like;
	}
	public void setCount_like(int count_like) {
		this.count_like = count_like;
	}
	
	
	
	public int getCount_dislike() {
		return count_dislike;
	}
	public void setCount_dislike(int count_dislike) {
		this.count_dislike = count_dislike;
	}
	
	
	
	
}
