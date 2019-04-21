package org.catDownloader.domain;

import java.util.Collection;

import org.catDownloader.api.domain.ISearchQuery;
import org.catDownloader.dto.CatInfo;
import org.catDownloader.util.properties.SearchProperties;

public class SearchQuery implements ISearchQuery{
	
	private static final String COMMA = ",";
	private static final String AMPERSAND = "&";
	private static final String QUESTION_MARK = "?";
	private static final String LIMIT = "limit=";
	private static final String PAGE = "page=";
	private static final String ORDER = "order=";
	private static final String BREED_IDS = "breed_ids=";
	private static final String CATEGORY_ID = "category_id=";
	private static final String MIME_TYPES = "mime_types=";
	private static StringBuilder builder;
	private static String searchUrl;
	private static SearchProperties searchProperty;
	
	@Override
	public String CreateQuery(CatInfo catInfo){
        
        searchUrl = getURL();
        
        builder = new StringBuilder(searchUrl);
        builder.append(QUESTION_MARK);
		if (catInfo.getLimit() != null) {
			builder.append(LIMIT);
			builder.append(catInfo.getLimit());
			builder.append(AMPERSAND);
		}
		if (catInfo.getPage() != null) {
			builder.append(PAGE);
			builder.append(catInfo.getPage());
			builder.append(AMPERSAND);
		}
		if (catInfo.getBreedId() != null) {
			builder.append(BREED_IDS);
			builder.append(catInfo.getBreedId());
			builder.append(AMPERSAND);
		}
		if (catInfo.getCategoryIds() != null) {
			builder.append(CATEGORY_ID);
			builder.append(collectionToString(catInfo.getCategoryIds()));
			builder.append(AMPERSAND);
		}
		if (catInfo.getMimeTypes() != null) {
			builder.append(MIME_TYPES);
			builder.append(collectionToString(catInfo.getMimeTypes()));
			builder.append(AMPERSAND);
		}
		if (catInfo.getOrder() != null) {
			builder.append(ORDER);
			builder.append(catInfo.getOrder());
		}
        
		return builder.toString();
	}
	
	private String collectionToString(Collection<?> collection) {
		StringBuilder builder = new StringBuilder();
		int i = 0;
		for (Object obj : collection) {
			builder.append(obj);
			i++;
			if (i == collection.size()) {
				break;
			}
			builder.append(COMMA);
		}
		return builder.toString();
	}
	
	private String getURL() {
		searchProperty = new SearchProperties("searchUrl");
	    return searchProperty.getSearchUrl();
		
	}
}