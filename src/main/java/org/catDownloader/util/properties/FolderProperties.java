package org.catDownloader.util.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;
import org.catDownloader.api.util.properties.IFolderProperties;



public class FolderProperties implements IFolderProperties{
	private static final Logger logger = Logger.getLogger(HeaderProperties.class.getName());
	private static final String PATH = "src/main/properties/download_folder.properties";
	private String Folder;
	private Properties property;
	
	
	
	public FolderProperties(String Folder)
	{
		try {
			property = new Properties();
			property.load(new FileInputStream(PATH));
			this.Folder = property.getProperty(Folder);
		} catch (IOException e) {
			logger.info(e.toString());
		}
	}
	@Override
	public String getFolder() {
		return Folder;
	}
}
