package databeans;

public class SellFundTable {
	private String fundName;
	private String symbol;
	private String latestPrice;
	private String availableShares;
	private int fund_id;

	
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public String getFundName() {
		return fundName;
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
	
	
	public String getAvailableShares() {
		return availableShares;
	}
	public void setAvailableShares(String availableShares) {
		this.availableShares = availableShares;
	}
	public int getFund_id() {
		return fund_id;
	}
	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}
	
	

}
