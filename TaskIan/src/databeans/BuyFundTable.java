package databeans;

public class BuyFundTable {

	private String name;
	private String symbol;
	private String latestPrice;
	private int fund_id;

	public BuyFundTable() {

	}

	public BuyFundTable(Fund f, String latestPrice) {
		this.name = f.getName();
		this.symbol = f.getSymbol();
		this.latestPrice = latestPrice;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getName() {
		return name;
	}


	public String getLatestPrice() {
		return latestPrice;
	}

	public int getFund_id() {
		return fund_id;
	}

	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}

}
