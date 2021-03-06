package com.atlantis.supermarket.core.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.util.Pair;

import com.atlantis.supermarket.core.product.Batch.BatchType;
import com.atlantis.supermarket.core.product.events.ProductCreatedEvent;
import com.atlantis.supermarket.core.product.events.ProductMinStockEvent;
import com.atlantis.supermarket.core.product.exception.BatchNotAvailableException;
import com.atlantis.supermarket.core.product.exception.BatchNotExistException;
import com.atlantis.supermarket.core.product.exception.ProductNotSatisfiedStockException;
import com.atlantis.supermarket.core.product.search.ProductSolrDto;
import com.atlantis.supermarket.core.shared.BaseEntityAuditable;
import com.atlantis.supermarket.core.shared.search.SolrDto;
import com.atlantis.supermarket.core.shared.search.SolrIndexed;
import com.atlantis.supermarket.core.shared.search.SolrUpdate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * “First Expired, First Out” (FEFO)
 * http://justfooderp.com/blog/the-fefo-method-to-expiration-date-management/
 * 
 * @author jloscalzo
 *
 */
@Entity
@Table(name = "product")
@EntityListeners(SolrUpdate.class)
@Access(AccessType.FIELD)
public class Product extends BaseEntityAuditable implements SolrIndexed {

    private static final long serialVersionUID = -2257627604122619697L;
    // TODO: product kg/unit/... etc

    @Column(unique = true, nullable = false)
    private String sku;

    @Column(unique = true, nullable = false)
    private String upc;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private Double minStock;

    @Column(nullable = false)
    private BigDecimal providerPrice;

    @Column(nullable = false)
    private BigDecimal retailPrice;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean notifiedProvider = false;

    @Enumerated(EnumType.STRING)
    private BatchType type = BatchType.DEFAULT;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<Batch> batches;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonManagedReference
    private Provider provider;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    @OrderColumn(name = "category_order")
    @JsonManagedReference
    private Collection<Category> categories = new ArrayList<>();

    public Product() {
	this.batches = new ArrayList<Batch>();

	// evento para indexar en solr
	this.registerEvent(new ProductCreatedEvent(this));

    }

    @Override
    public String toString() {
	return this.getClass().getSimpleName() + " [id=" + sku + ", nombre=" + name + ", minimo=" + minStock
		+ ", cantidad= " + "]";
    }

    public BatchType getBatchType() {
	return type;
    }

    public Product setBatchType(BatchType type) {
	this.type = type;
	return this;
    }

    public String getSku() {
	return sku;
    }

    public void setSku(String sku) {
	this.sku = sku;
    }

    public String getUpc() {
	return upc;
    }

    public Product setUpc(String upc) {
	this.upc = upc;
	return this;
    }

    public String getName() {
	return name;
    }

    public Product setName(String name) {
	this.name = name;
	return this;
    }

    public String getBrand() {
	return brand;
    }

    public Product setBrand(String brand) {
	this.brand = brand;
	return this;
    }

    public Double getMinStock() {
	return minStock;
    }

    public Product setMinStock(Double minStock) {
	this.minStock = minStock;
	return this;
    }

    public BigDecimal getProviderPrice() {
	return providerPrice;
    }

    public Product setProviderPrice(BigDecimal supplierPrice) {
	this.providerPrice = supplierPrice;
	return this;
    }

    public BigDecimal getRetailPrice() {
	return retailPrice;
    }

    public Product setRetailPrice(BigDecimal retailPrice) {
	this.retailPrice = retailPrice;
	return this;
    }

    public BatchType getType() {
	return type;
    }

    public Product setType(BatchType type) {
	this.type = type;
	return this;
    }

    public Boolean getNotifiedProvider() {
	return notifiedProvider;
    }

    public void setNotifiedProvider(Boolean notifiedProvider) {
	this.notifiedProvider = notifiedProvider;
    }

    public Collection<Batch> getBatches() {
	return this.getBatches(false);
    }

    public void addCategory(Category tag) {
	categories.add(tag);
	tag.getProducts().add(this);
    }

    public void removeCategory(Category tag) {
	categories.remove(tag);
	tag.getProducts().remove(this);
    }

    /**
     * retrieve deleted elements ?
     * 
     * @param all if you want to retrieve deleted elements too
     * @return
     */
    public Collection<Batch> getBatches(Boolean all) {
	return batches.stream().filter(b -> all ? true : !b.getDeleted()).collect(Collectors.toList());
    }

    public Collection<Batch> getConsumableBatches() {
	Date today = new Date();
	if (this.getBatchType() == BatchType.EXPIRATION) {
	    return this.getBatches()
		    .stream()
		    .sorted((a, b) -> a.getEntry().compareTo(b.getEntry()))
		    .filter(x -> x.getExpiration().after(today) && x.getRemainingUnits() > 0)
		    .sorted((a, b) -> a.getExpiration().compareTo(b.getExpiration()))
		    .collect(Collectors.toList());
	}
	return batches.stream().sorted((a, b) -> a.getEntry().compareTo(b.getEntry())).collect(Collectors.toList());
    }

    public Product setBatches(Collection<Batch> batches) {
	this.batches = batches;
	return this;
    }

    public Collection<Category> getCategories() {
	return this.categories;
    }

    public Provider getProvider() {
	return provider;
    }

    public void setProvider(Provider provider) {
	this.provider = provider;
    }

    public String getDescription() {
	return description;
    }

    public Product setDescription(String description) {
	this.description = description;
	return this;
    }

    public Product setCategories(Collection<Category> categories) {
	this.categories = categories;
	return this;
    }

    public void addBatch(Batch... b) {
	this.batches.addAll(Arrays.asList(b));
    }

    public double getCurrentUnits() {
	return getCurrentUnits(this.getConsumableBatches());
    }

    public double getCurrentUnits(Collection<Batch> consumables) {
	return consumables
		.stream()
		.collect(Collectors.summingDouble(Batch::getRemainingUnits));
    }

    public List<Pair<Batch, Double>> consume(double quantity) throws ProductNotSatisfiedStockException {
	return this.consume(quantity, this.getConsumableBatches());
    }

    private List<Pair<Batch, Double>> consume(double quantity, Collection<Batch> consumableBatches)
	    throws ProductNotSatisfiedStockException {
	double units = quantity;
	List<Pair<Batch, Double>> consumed = new ArrayList<Pair<Batch, Double>>();

	if (quantity > this.getCurrentUnits(consumableBatches)) {
	    throw new ProductNotSatisfiedStockException(String.valueOf(quantity),
		    String.valueOf(this.getCurrentUnits(consumableBatches)), this.getId());
	}

	for (Batch b : consumableBatches) {

	    double consumable = Math.min(units, b.getRemainingUnits());
	    b.consume(consumable);

	    units -= consumable;

	    consumed.add(Pair.of(b, consumable));
	    if (units == 0) {
		break;
	    }
	}

	/**
	 * Si ya notifiqué al proveedor no lo vuelvo a hacer
	 */
	if (haveToNotifiedProvider()) {
	    // https://www.baeldung.com/spring-data-ddd
	    this.registerEvent(new ProductMinStockEvent(this));
	}

	return consumed;
    }

    @Transient
    public Boolean haveToNotifiedProvider() {
	return hasNotStock() && !this.getNotifiedProvider();
    }
    
    @Transient
    public Boolean hasNotStock() {
	return this.getCurrentUnits() <= this.getMinStock();
    }
    
    /**
     * Si tengo stock y notifiqué al proveedor de no stock cambio valor
     */
    @Transient
    public void changeNotified() {
	if (!this.hasNotStock() && this.getNotifiedProvider()) {
	    this.setNotifiedProvider(false);
	}
    }

    public List<Pair<Batch, Double>> consume(double quantity, UUID batch)
	    throws ProductNotSatisfiedStockException, BatchNotExistException, BatchNotAvailableException {
	Batch b = this.batches.stream()
		.filter(bi -> bi.getId() == batch)
		.findFirst()
		.orElse(null);

	if (b == null) {
	    throw new BatchNotExistException(batch.toString());
	}

	if (this.getBatchType() == BatchType.EXPIRATION) {
	    if (b.getExpiration().before(new Date())) {
		throw new BatchNotAvailableException(batch.toString());
	    }
	}

	List<Batch> l = new ArrayList<Batch>();
	l.add(b);

	return consume(quantity, l);

    }

    public boolean isPurchable() {
	return this.getCurrentUnits() > 0;
    }

    @Override
    public SolrDto getSolrDto() {
	ProductSolrDto dto = new ProductSolrDto();
	dto.setId(this.getClass(), this.getId());
	dto.setName(name);
	dto.setBrand(this.getBrand());
	dto.setDescription(this.getDescription());
	dto.setCategories(this.getCategories().stream().map(x -> x.getDescription()).collect(Collectors.toList()));
	dto.setRetailPrice(this.getRetailPrice());
	return dto;
    }

}