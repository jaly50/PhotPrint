package databeans;

import org.genericdao.PrimaryKey;

@PrimaryKey("customer_id,fund_id")
public class Position {
	
	private int customer_id = -1;
	private int fund_id = -1;
	private long shares = 0;
	private long availableShares = 0;

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	
	
	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}
	public int getFund_id() {
		return fund_id;
	}

	
	public void setShares(long shares) {
		this.shares = shares;
	}
	public long getShares() {
		return shares;
	}
	
	public void setAvailableShares(long availableShares) {
		this.availableShares = availableShares;
	}
	public long getAvailableShares() {
		return availableShares;
	}

}
