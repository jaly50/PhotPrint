package databeans;

import org.genericdao.PrimaryKey;


@PrimaryKey("photo_id")

public class Photo_Favor {
	private int photo_id;
	private String photo;//.jpg to show pic
	private String url; // href to website
	private String location;
	private String tag;
	private int count_like;
	private int count_dislike;
	
	public int getPhoto_id() {
		return photo_id;
	}
	public void setPhoto_id(int i) {
		photo_id = i;
	}
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getCount_like() {
		return count_like;
	}

	public void setCount_like(int count_like) {
		this.count_like = count_like;
	}

	public int getCount_Dislike() {
		return count_dislike;
	}

	public void setCount_Dislike(int count_dislike) {
		this.count_dislike = count_dislike;
	}

	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	

}
