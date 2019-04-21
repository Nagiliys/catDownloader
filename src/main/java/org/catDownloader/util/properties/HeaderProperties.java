package org.catDownloader.util.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;
import org.catDownloader.api.util.properties.IHeaderProperties;



public class HeaderProperties implements IHeaderProperties{
	private static final Logger logger = Logger.getLogger(HeaderProperties.class.getName());
	private static final String PATH = "src/main/properties/header.properties";
	private String headerName;
	private String headerValue;
	private Properties property;
	
	public HeaderProperties(String headerName)
	{
		try {
			this.headerName = headerName;
			property = new Properties();
			property.load(new FileInputStream(PATH));
			headerValue = property.getProperty(headerName);
		} catch (IOException e) {
			logger.info(e.toString());
		}
	}
	
	@Override
	public String getHeaderName() {
		return headerName;
	}
	
	@Override
	public String getHeaderValue() {
		return headerValue;
	}
}
