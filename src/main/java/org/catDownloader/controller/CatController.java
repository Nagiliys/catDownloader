package org.catDownloader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.catDownloader.service.CatDownloadService;
import org.catDownloader.dto.CatInfo;

@RestController
public class CatController {

	
	@Autowired
	private CatDownloadService catDownloadService;
	
	@PostMapping("cat")
	public Object downloadCat(@RequestBody CatInfo catInfo) {
		String path = catDownloadService.download(catInfo);
		return path;
	}
}