package jp.dcnet.ec.obj;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Product {
	private long product_id;
	private String name;
	private String description;
	private boolean public_status;
	private boolean new_icon;
	private boolean popular_icon;
	
	public Product() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPublic_status() {
		return public_status;
	}

	public void setPublic_status(boolean public_status) {
		this.public_status = public_status;
	}

	public boolean isNew_icon() {
		return new_icon;
	}

	public void setNew_icon(boolean new_icon) {
		this.new_icon = new_icon;
	}

	public boolean isPopular_icon() {
		return popular_icon;
	}

	public void setPopular_icon(boolean popular_icon) {
		this.popular_icon = popular_icon;
	}
	
	
}
