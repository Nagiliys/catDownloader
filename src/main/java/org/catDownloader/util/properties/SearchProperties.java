package org.catDownloader.util.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;
import org.catDownloader.api.util.properties.ISearchProperties;



public class SearchProperties implements ISearchProperties{
	private static final Logger logger = Logger.getLogger(HeaderProperties.class.getName());
	private static final String PATH = "src/main/properties/search_url.properties";
	private String SearchUrl;
	private Properties property;
	
	public SearchProperties(String SearchName)
	{
		try {
			property = new Properties();
			property.load(new FileInputStream(PATH));
			SearchUrl = property.getProperty(SearchName);
		} catch (IOException e) {
			logger.info(e.toString());
		}
	}
	
	@Override
	public String getSearchUrl() {
		return SearchUrl;
	}
}
