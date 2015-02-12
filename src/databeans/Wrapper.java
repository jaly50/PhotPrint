package databeans;

public class Wrapper {
	private Photo photo;
	private Title title;
	private Tags[]  tag;
	private int count_like;
	private int count_dislike;
	
	// photo
	public Photo getPhoto() {
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	// title
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	// tags
	public Tags[] getTags() {
		return tag;
	}
	public void setTags(Tags[] tag) {
		this.tag = tag;
	}
	
	// count_like
	public int getCount_like() {
		return count_like;
	}
	public void setCount_like(int count_like) {
		this.count_like = count_like;
	}
	
	// count_dislike
	public int getCount_dislike() {
		return count_dislike;
	}
	public void setCount_dislike(int count_dislike) {
		this.count_dislike = count_dislike;
	}
	
	
	
	
	
	

}
