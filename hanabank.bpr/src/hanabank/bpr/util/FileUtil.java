package hanabank.bpr.util;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import hanabank.bpr.views.actions.BprPopupActionDelegate;

public class FileUtil {
	
	public static String FILE_NAME  = "bprview.json";
	
	private FileUtil() {
		FileUtil.FILE_NAME = "bprview.json";
	}
	public static FileUtil getInstace(){
		
		 return LazyHolder.INSTANCE;
	}
	
	//나중에 private로 변경
	public File getFile() throws IOException {
//		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		//해당 파일이 있는 프로젝트는 action delegate에서 받아온다.
		IProject project = BprPopupActionDelegate.project;
		if(project == null) {
			return null;
		}
		IPath path = project.getRawLocation().append(FILE_NAME);
		File f = path.toFile();
		if(!f.exists()) {
			f.createNewFile();
		}
		return f;
	}
	
	
	public void setMethod(FileVO vo) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
//		String jsonString = mapper.writeValueAsString(vo);
		JsonNode jsonNode = mapper.convertValue(vo, JsonNode.class);
//		File target = this.getFile();
		System.out.println(jsonNode);
		
		
	}
	
	private static class LazyHolder {
        private static final FileUtil INSTANCE = new FileUtil();
    }
	
	
	
	public static void main(String[] args) {
		FileVO vo = new FileVO();
//		FileInfo info = vo.newFileInfo();
		
		vo.setMethodName("tesT");
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.convertValue(vo, JsonNode.class);
			System.out.println(jsonNode);
			
			
//			FileUtil.getInstace().setMethod(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
	}
}
