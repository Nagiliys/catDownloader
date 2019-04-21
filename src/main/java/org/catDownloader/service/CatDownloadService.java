package org.catDownloader.service;

import org.catDownloader.dto.CatInfo;
import org.catDownloader.util.Downloader;
import org.catDownloader.util.Urls;
import org.catDownloader.api.service.ICatDownloadService;
import org.catDownloader.domain.SearchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CatDownloadService implements ICatDownloadService{
	@Autowired
	private Downloader downloader;
	@Autowired
	private Urls urls;
	
	@Override
	public String download(CatInfo catInfo) {
		urls = new Urls();
		downloader = new Downloader();
		String searchUrl = new SearchQuery().CreateQuery(catInfo);
		String json = downloader.downloadJSON(searchUrl);
		List<String> URLs = urls.GetURLsFromJSON(json);
		String path = downloader.download(URLs);
		return path;
	}
}