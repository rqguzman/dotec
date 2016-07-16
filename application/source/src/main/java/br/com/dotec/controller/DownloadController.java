package br.com.dotec.controller;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.download.FileDownload;
import br.com.dotec.persistence.dao.FileDAO;
import br.com.dotec.infra.file.File;


@Resource
public class DownloadController {
	private final File file;
	private final FileDAO fileDAO;
	private final Result result;
	
	
	public DownloadController(File file, FileDAO fileDAO, Result result) {
		super();
		this.file = file;
		this.fileDAO = fileDAO;
		this.result = result;
	}


	public void download(String fileId) {
				
		br.com.dotec.model.File fileDotec = fileDAO.carrega(fileId);
		String path = fileDotec.getPath();
		result.redirectTo(path);
	
	}
	
	
	private AmazonS3 getS3Cliente() {
		BasicAWSCredentials awsCredential = new BasicAWSCredentials(
				"AKIAJ6PYLF4ELZEPXPGA",
				"S1FSjq3+lGuM6G/Kn9IqU7IOsNJfGuAaldnxJqnk");
		AmazonS3 s3 = new AmazonS3Client(awsCredential);
		Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		s3.setRegion(usWest2);
		return s3;
	}

}
