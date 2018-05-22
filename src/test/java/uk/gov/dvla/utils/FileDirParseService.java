package uk.gov.dvla.utils;

import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;


public class FileDirParseService {

	private FileNameMap fileNameMap;
	private static FileDirParseService instance = null;
	
	private FileDirParseService() {
		
	}
	
	public static FileDirParseService getInstance() {
		if(instance == null) {
	         instance = new FileDirParseService();
	      }
	      return instance;
		
	}

	private void getFileDirListWithExt(String basePath, String[] allowedExts, String[] allowedMimeTypes, HashMap<String, TestFileObject> resultFileMap) {
		File basePathDir = new File(basePath);
		File[] allFiles = basePathDir.listFiles();
		for (File file : allFiles) {
			if (file.isDirectory()) {
				this.getFileDirListWithExt(file.getPath(), allowedExts, allowedMimeTypes, resultFileMap);
			} else {
				String fileExt = file.getName().substring(file.getName().lastIndexOf(".")+1);
				String fileMimeType = this.getMimeType(file.getPath());
				if (Arrays.asList(allowedExts).contains(fileExt) | Arrays.asList(allowedMimeTypes).contains(fileMimeType)) {
					String fileSize = (file.length() / 1024) + " KB";
					resultFileMap.put(
							file.getName(),
							new TestFileObject(file.getName(), this.getMimeType(file.getPath()),
									fileExt, fileSize, file.getAbsolutePath())
							);
				}

			}
		}
	}

	private String getMimeType(String path) {
		if (this.fileNameMap == null) {
			this.fileNameMap = URLConnection.getFileNameMap();
		}
		String mimeType = this.fileNameMap.getContentTypeFor(path);
		if (mimeType == null) {
			return "";
		}
		return mimeType;
	}

	public HashMap<String, TestFileObject> getFileListMap(String path, String[] allowedExts, String[] allowedMimes) {
		HashMap<String, TestFileObject> resultFileMap = new HashMap<String, TestFileObject>();
		try {
			File f = new File(path);
			if (!f.isDirectory()) {
				throw new IllegalArgumentException("Please supply valid path");
			}
		} catch (Exception e1) {
			throw new IllegalArgumentException("Please supply valid well formed path");
		}
		
		
		if (Arrays.asList(allowedExts).size() <= 0 && Arrays.asList(allowedMimes).size() <= 0) {
			throw new IllegalArgumentException("Please give valid exts or mimetypes");
		}
		getFileDirListWithExt(
				path,				
				allowedExts,
				allowedMimes,
				resultFileMap
		);
		return resultFileMap;
	}
	

}
