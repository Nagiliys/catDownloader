package org.catDownloader.service;

import org.catDownloader.dto.CatInfo;
import org.catDownloader.domain.SearchQuery;

import org.springframework.stereotype.Service;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class CatDownloadService{
	
	public String download(CatInfo catInfo) {
		try {
		String searchUrl = SearchQuery.CreateQuery(catInfo);
		String json = DownloadJSON(searchUrl);
		List<String> URLs = GetURLsFromJSON(json);
		String path = downloadImages(URLs);
		return path;
		} catch (Exception e)
		{
			return "";
		}
	}
	
	private String DownloadJSON(String url) throws IOException {	
		URLConnection connection = new URL(url).openConnection();
		Properties property = new Properties();
		property.load(new FileInputStream("src/main/properties/header.properties"));
        String HeaderName = "x-api-key";
        connection.addRequestProperty(HeaderName, property.getProperty(HeaderName));
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                    connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) 
            response.append(inputLine);
        in.close();
        return response.toString();
	}
	
	private String GetIdFromURL(String url) {
		String[] _url = url.split("/");
		return _url[_url.length-1];
	}
	
	private List<String> GetURLsFromJSON(String json) {
		List<String> urls = new ArrayList<>();
		for (String str : json.split(",")) {
            if(str.indexOf("\"url\"")!=-1)
            {
            	urls.add(str.replace("\"", "").replace("url:", ""));
            }
        }
		return urls;
	}
	
	private String downloadImages(List<String> urls) throws FileNotFoundException, IOException {
		Properties property = new Properties();
		property.load(new FileInputStream("src/main/properties/download_folder.properties"));
		File file = new File(property.getProperty("folderPath"));
		if (!file.exists()) {
			file.mkdirs();
		}
		String path = file.getAbsolutePath();
		for(String url : urls) {
			String fileName = GetIdFromURL(url);
			URLConnection connection = new URL(url).openConnection();
			
			property.load(new FileInputStream("src/main/properties/header.properties"));
	        String HeaderName = "User-Agent";
	        connection.addRequestProperty(HeaderName, property.getProperty(HeaderName));
	        property.load(new FileInputStream("src/main/properties/download_folder.properties"));
	        //
	        file = new File(property.getProperty("folderPath") + fileName);
			try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
					FileOutputStream fileOutputStream = new FileOutputStream(property.getProperty("folderPath") + fileName)) {
				byte dataBuffer[] = new byte[1024];
				int bytesRead;
				while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
					fileOutputStream.write(dataBuffer, 0, bytesRead);
				}
	
			}
		}
		return path+"\\";
	}	
}