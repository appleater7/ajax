package utils;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadFile {

	private static final String TMP_PATH = System.getProperty("java.io.tmpdir"); 
	private static final int MEMORY_SIZE = 10*1024*1024; // 메모리 사이즈
	private static final int TOTAL_SIZE = 100*1024*1024; // 총 사이즈
	private static final int FILE_SIZE = 10*1024*1024; // 파일 하나당 사이즈
	private static final File TMP_FOLDER = new File(TMP_PATH); // 임시 저장소
	
	private static final DiskFileItemFactory dfiFactory = new DiskFileItemFactory();
	
	static {
		dfiFactory.setSizeThreshold(MEMORY_SIZE); // 메모리크기
		dfiFactory.setRepository(TMP_FOLDER); // 저장위치
	}
	
	public static Map<String, Object> parseRequest(HttpServletRequest request) {
		System.out.println(ServletFileUpload.isMultipartContent(request));
		return null;
	}
}
