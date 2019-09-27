package kr.or.ddit.servlet03;

import java.io.File;

import javax.servlet.ServletContext;

/**
 * (object) Adapter / Wrapper pattern
 * 
 *
 */
public class FileWrapper {
	public FileWrapper(File resource, ServletContext application) {
		this(resource, application, false);
	}
	
	public FileWrapper(File resource, ServletContext application, boolean wildcard) {
		super();
		this.resource = resource;
		this.application = application;
		contextRealPath = application.getRealPath("");
		displayName = resource.getName();
		if(wildcard) displayName = "..";
		String absolutePath = resource.getAbsolutePath();
		id = absolutePath.substring(contextRealPath.length()-1);
		id = id.replace(File.separatorChar, '/');
		file = resource.isFile();
		directory = resource.isDirectory();
	}

	private File resource;
	private ServletContext application;
	private String contextRealPath;
	
	private String displayName; // li 태그의 innerText
	private String id; // li태그의 id(서버사이드 경로)
	
	private boolean file;
	private boolean directory;

	public String getDisplayName() {
		return displayName;
	}
	public String getId() {
		return id;
	}
	public File getResource() {
		return resource;
	}

	public ServletContext getApplication() {
		return application;
	}

	public void setApplication(ServletContext application) {
		this.application = application;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isFile() {
		return file;
	}

	public void setFile(boolean file) {
		this.file = file;
	}

	public boolean isDirectory() {
		return directory;
	}

	public void setDirectory(boolean directory) {
		this.directory = directory;
	}
	
}










