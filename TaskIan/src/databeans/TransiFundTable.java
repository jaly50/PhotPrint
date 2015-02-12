package databeans;

public class TransiFundTable {
	private int    fund_id;
	private String symbol;
	private String latestPrice;
	private String newDate;
	private String name;

	
	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}
	public int getFund_id() {
		return fund_id;
	}

	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getSymbol() {
		return symbol;
	}
	
	

	public void setLatestPrice(String latestPrice) {
		this.latestPrice = latestPrice;
	}
	public String getLatestPrice() {
		return latestPrice;
	}
	
	public void setNewDate(String newDate) {
		this.newDate = newDate;
	}
	public String getNewDate() {
		return newDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
