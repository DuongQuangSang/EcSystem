package jp.dcnet.ec.obj;

public class QuantityDTO {
	private long quantityId;
	private ProductDTO product;
	private int quantity;
	
	public QuantityDTO() {
	}

	public long getQuantityId() {
		return quantityId;
	}

	public void setQuantityId(long quantityId) {
		this.quantityId = quantityId;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
