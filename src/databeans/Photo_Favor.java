package databeans;

import org.genericdao.PrimaryKey;


@PrimaryKey("photo_id")

public class Photo_Favor {
	private String photo_id;
	private int count_like;
	private int count_dislike;

	public String getPhoto_id() {
		return photo_id;
	}

	public void setPhoto_id(String photo_id) {
		this.photo_id = photo_id;
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

}
