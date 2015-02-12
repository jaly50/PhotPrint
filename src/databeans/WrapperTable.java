package databeans;

public class WrapperTable {
	
	private String photo;
	private String title;
	private String[] tags;
	private int count_like;
	private int count_dislike;

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}


}
