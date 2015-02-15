package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class LikeForm extends FormBean {
	private String photo;//.jpg to show pic
	private int count_like;
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getCount_Like() {
		return count_like;
	}

	public void setCount_Like(int count_like) {
		this.count_like = count_like;
	}

	
}