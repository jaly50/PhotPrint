package databeans;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class Photo implements Comparable<Photo> {
	public static final List<String> EXTENSIONS = Collections.unmodifiableList(Arrays.asList( new String[] {
			".jpg", ".gif", ".JPG"
	} ));

	private int    id          = -1;
	
	private byte[] bytes       = null;
	private String description     = null;
	private String contentType = null;
	private String owner       = null;
	private String location = null;
	private int    position    = 0;
	
	public int compareTo(Photo other) {
		// Order first by owner, then by position
		if (owner == null && other.owner != null) return -1;
		if (owner != null && other.owner == null) return 1;
		int c = owner.compareTo(other.owner);
		if (c != 0) return c;
		return position - other.position;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Photo) {
			Photo other = (Photo) obj;
			return compareTo(other) == 0;
		}
		return false;
	}
	
    public byte[] getBytes()       { return bytes;       }
    public String getContentType() { return contentType; }
    public int    getId()          { return id;          }
    public String getOwner()       { return owner;       }
    public int    getPosition()    { return position;    }
    
    public void setBytes(byte[] a)        { bytes = a;        }
    public void setContentType(String s)  { contentType = s;  }
    public void setId(int x)              { id = x;           }
    public void setOwner(String userName) { owner = userName; }
    public void setPosition(int p)        { position = p;     }
    
    public String toString() {
    	return "Photo("+id+")";
    }

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
