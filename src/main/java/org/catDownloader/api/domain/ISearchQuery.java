package org.catDownloader.api.domain;

import org.catDownloader.dto.CatInfo;

public interface ISearchQuery {
	public String CreateQuery(CatInfo catInfo);
}
