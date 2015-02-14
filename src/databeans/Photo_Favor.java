package databeans;

import org.genericdao.PrimaryKey;


@PrimaryKey("photo")

public class Photo_Favor {
	private String photo;//.jpg to show pic
	private String url; // href to website
	private String location;
	private String tag;
	private int count_Like;
	private int count_Dislike;
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getCount_Like() {
		return count_Like;
	}

	public void setCount_Like(int count_like) {
		this.count_Like = count_like;
	}

	public int getCount_Dislike() {
		return count_Dislike;
	}

	public void setCount_Dislike(int count_dislike) {
		this.count_Dislike = count_dislike;
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
