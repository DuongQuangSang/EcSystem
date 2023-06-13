package jp.dcnet.ec.obj;

public class QuantityDTO {
	private Long quantityId;
	private Long productId;
	private int quantity;
	
	public QuantityDTO() {
	}

	public Long getQuantityId() {
		return quantityId;
	}

	public void setQuantityId(Long quantityId) {
		this.quantityId = quantityId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
