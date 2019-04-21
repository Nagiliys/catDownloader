package org.catDownloader.util;

import org.catDownloader.api.util.IUrlParser;
import org.springframework.stereotype.Component;

@Component
public class UrlParser implements IUrlParser{
	
	@Override
	public String GetFileName(String _url) {
		String[] url = _url.split("/");
		return url[url.length-1];
	}
}
