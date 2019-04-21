package org.catDownloader.util;

import java.util.ArrayList;
import java.util.List;
import org.catDownloader.api.util.IUrls;
import org.springframework.stereotype.Component;

@Component
public class Urls implements IUrls{
	
	@Override
	public List<String> GetURLsFromJSON(String json) {
		List<String> urls = new ArrayList<>();
		for (String str : json.split(",")) {
            if(str.indexOf("\"url\"")!=-1)
            {
            	urls.add(str.replace("\"", "").replace("url:", ""));
            }
        }
		return urls;
	}
}
