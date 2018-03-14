package uk.gov.dvla.utils;
public class TestFileObject{
	private String mimeType;
	private String fileName;
	private String extension;
	private String size;
	private String absolutePath;

	public TestFileObject(String fileName, String mimeType, String extension, String size, String absolutePath) {
		this.fileName = fileName;
		this.mimeType = mimeType;
		this.extension = extension;
		this.size = size;
		this.absolutePath = absolutePath;
		
	}
	
	public String getMimeType() {
		return this.mimeType;
	}

	public String getFileName() {
		return this.fileName;
	}


	public String getExtension() {
		return this.extension;
	}

	public String getSize() {
		return this.size;
	}
	
	public String getAbsolutePath() {
		return this.absolutePath;
	}
	
	@Override
	public String toString() {
		return "TestFileObject [mimeType=" + mimeType + ", fileName=" + fileName + ", extension=" + extension
				+ ", size=" + size + " filePath="+ this.absolutePath + "]";
	}
}