package org.catDownloader.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.catDownloader.domain.BreedId;
import org.catDownloader.domain.MimeType;
import org.catDownloader.domain.Order;

public class CatInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer limit;

	private Integer page;

	private BreedId breedId;

	private List<Integer> categoryIds;

	private Set<MimeType> mimeTypes;

	private Order order;
	
	public CatInfo()
	{
		limit = null;
		page = null;
		breedId = null;
		categoryIds = null;
		mimeTypes = null;
		order = null;
	}
	public CatInfo(Integer limit, Integer page, BreedId breedId, List<Integer> categoryIds, Set<MimeType> mimeTypes, Order order) {
		this.limit = limit;
		this.page = page;
		this.breedId = breedId;
		this.categoryIds = categoryIds;
		this.mimeTypes = mimeTypes;
		this.order = order;
	}
	
	public Integer getLimit() {
		return this.limit;
	}
	public Integer getPage() {
		return this.page;
	}
	public BreedId getBreedId() {
		return this.breedId;
	}	
	public List<Integer> getCategoryIds() {
		return this.categoryIds;
	}
	public Set<MimeType> getMimeTypes() {

		return this.mimeTypes;
	}
	public Order getOrder() {
		return this.order;
	}
}