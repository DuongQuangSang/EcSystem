package jp.dcnet.ec.obj;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ProductDTO {
	private Long productId;
    private String name;
    private String description;
    private String attributeName;
    private String attributeValue;
    private boolean publicStatus;
    private boolean suggest;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDate;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endDate;
    private boolean newIcon;
    private boolean popularIcon;
    
	public ProductDTO() {
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
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
