package br.com.dotec.infra.file;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.util.properties.ApplicationProperties;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Component
public class File {
	private java.io.File pastaImagens;
	private String caminhoDaPastaFiles;
	private AmazonS3 s3;
	private String bucketName = "dotecfiles";

	public File(ServletContext context) {
		
		
			this.caminhoDaPastaFiles = context.getRealPath("/WEB-INF/files");
		this.s3= null;
	}

	public br.com.dotec.model.File salva(UploadedFile uploadedFile) {
		
		
		try {
			
			this.s3 = getS3Cliente();
			
			
			if (s3.doesBucketExist(bucketName) == false) {
				s3.createBucket(bucketName);
			}
		} catch (AmazonClientException ace) {
			System.out.println("Unable to create a new Amazon S3 bucket: " + ace.getMessage());
			System.out.println("Error Creating Bucket");
				
		}
		
		
		
		
		br.com.dotec.model.File file = new br.com.dotec.model.File();
		file.setName(uploadedFile.getFileName().toString());
		file.setContentType(uploadedFile.getContentType());		
		file.setPath("http://dotecfiles.s3.amazonaws.com/"+file.getId() + "/" + uploadedFile.getFileName());
		
		try {

			pastaImagens = new java.io.File(caminhoDaPastaFiles + "/"
					+ file.getId());
			this.pastaImagens.mkdirs();
			java.io.File destino = new java.io.File(this.pastaImagens,
					uploadedFile.getFileName());
			
			String key =  file.getId() + "/" + uploadedFile.getFileName();			
						
			try {
				IOUtils.copyLarge(uploadedFile.getFile(), new FileOutputStream(
destino));
				
				PutObjectRequest put = new PutObjectRequest(bucketName, key , destino);
				put.setCannedAcl(CannedAccessControlList.PublicRead);
				s3.putObject(put);
				
				
				destino.deleteOnExit();		
				FileUtils.cleanDirectory(destino.getParentFile());
				FileUtils.deleteDirectory(destino.getParentFile());
				
			} catch (IOException e) {
				
				System.out.println(e.getMessage());
				
				throw new RuntimeException("Erro ao copiar imagem ["+e.getMessage()+"]", e);
			}
		} catch (AmazonServiceException ase) {
			System.out
					.println("Caught an AmazonServiceException, which means your request made it "
							+ "to Amazon S3, but was rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out
					.println("Caught an AmazonClientException, which means the client encountered "
							+ "a serious internal problem while trying to communicate with S3, "
							+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}

		return file;
	}

	public String getCaminhoDaPastaFiles() {
		return caminhoDaPastaFiles;
	}

	private AmazonS3 getS3Cliente() {
		BasicAWSCredentials awsCredential = new BasicAWSCredentials(
				ApplicationProperties.getInstance().getAwsS3AccessKey(),
				ApplicationProperties.getInstance().getAwsS3SecretKey());
		AmazonS3 s3 = new AmazonS3Client(awsCredential);
		Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		s3.setRegion(usWest2);
		return s3;
	}

}
