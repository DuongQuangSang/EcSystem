package jp.dcnet.ec.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private long productId;

	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "attribute_name")
	private String attributeName;

	@Column(name = "attribute_value")
	private String attributeValue;

	@Column(name = "public_status")
	private boolean publicStatus;

	@Column(name = "start_date")
	private LocalDateTime startDate;

	@Column(name = "end_date")
	private LocalDateTime endDate;

	@Column(name = "new_icon")
	private boolean newIcon;

	@Column(name = "popular_icon")
	private boolean popularIcon;
	
	@Column(name = "suggest")
	private boolean suggest;
	
	public ProductEntity() {
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
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

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public boolean isPublicStatus() {
		return publicStatus;
	}

	public void setPublicStatus(boolean publicStatus) {
		this.publicStatus = publicStatus;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public boolean isNewIcon() {
		return newIcon;
	}

	public void setNewIcon(boolean newIcon) {
		this.newIcon = newIcon;
	}

	public boolean isPopularIcon() {
		return popularIcon;
	}

	public void setPopularIcon(boolean popularIcon) {
		this.popularIcon = popularIcon;
	}

	public boolean isSuggest() {
		return suggest;
	}

	public void setSuggest(boolean suggest) {
		this.suggest = suggest;
	}

}
