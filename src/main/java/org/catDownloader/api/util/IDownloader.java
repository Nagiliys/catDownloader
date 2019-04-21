package org.catDownloader.api.util;

import java.util.List;

public interface IDownloader {
	public String download(List<String> urls);
	
	public String downloadJSON(String url);
}
