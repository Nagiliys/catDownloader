package org.catDownloader.util;

import org.catDownloader.api.util.IDownloader;
import org.catDownloader.util.UrlParser;
import org.catDownloader.util.properties.FolderProperties;
import org.catDownloader.util.properties.HeaderProperties;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.logging.Logger;

@Component
public class Downloader implements IDownloader{
	private static final Logger logger = Logger.getLogger(Downloader.class.getName());
	private static final String FOLDER_PATH = "folderPath";
	private static final String HEADER_USER = "User-Agent";
	private static final String HEADER_API = "x-api-key";
	private URLConnection connection;
	@Autowired
	private UrlParser urlParser;

	private HeaderProperties headerProperties;
	private FolderProperties folderProperties;
	
	@Override
	public String download(List<String> urls){
		try {
			urlParser = new UrlParser();
			folderProperties = new FolderProperties(FOLDER_PATH);
			
			File file = new File(folderProperties.getFolder());
			if (!file.exists()) {
				file.mkdirs();
			}
			String path = file.getAbsolutePath();
			
			headerProperties = new HeaderProperties(HEADER_USER);
			for(String url : urls) {
				String fileName = urlParser.GetFileName(url);
				connection = new URL(url).openConnection();	
		        connection.addRequestProperty(headerProperties.getHeaderName(), headerProperties.getHeaderValue());
		        file = new File(folderProperties.getFolder() + fileName);
				try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
						FileOutputStream fileOutputStream = new FileOutputStream(folderProperties.getFolder() + fileName)) {
					byte dataBuffer[] = new byte[1024];
					int bytesRead;
					while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
						fileOutputStream.write(dataBuffer, 0, bytesRead);
					}
				}
			}
			return path+"\\";
		} catch(MalformedURLException e) {
				logger.info(e.toString());
		} catch (IOException e) {
				logger.info(e.toString());
		}	
		return null;
	}
	
	@Override
	public String downloadJSON(String url){	
		try {
			connection = new URL(url).openConnection();
			headerProperties = new HeaderProperties(HEADER_API);
	        connection.addRequestProperty(headerProperties.getHeaderName(), headerProperties.getHeaderValue());
	        BufferedReader in = new BufferedReader(
	                                new InputStreamReader(
	                                    connection.getInputStream()));
	        StringBuilder response = new StringBuilder();
	        String inputLine;
	        while ((inputLine = in.readLine()) != null) 
	            response.append(inputLine);
	        in.close();
	        return response.toString();
		} catch(MalformedURLException e) {
				logger.info(e.toString());
		} catch(IOException e) {
				logger.info(e.toString());	
		}	
		return null;
	}
}
