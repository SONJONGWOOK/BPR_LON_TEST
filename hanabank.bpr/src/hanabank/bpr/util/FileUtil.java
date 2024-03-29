package hanabank.bpr.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import hanabank.bpr.views.actions.BprPopupActionDelegate;

public class FileUtil {
	
	private static String FILE_NAME  = "bprview.json";
	
	
	private Map<String, List<FileVO>> mainMap = new HashMap<String, List<FileVO>>();
//	private List<FileVO> list = new ArrayList<FileVO>();
	
	private File jsonFile;
	
	private static IProject project;
	
	private FileUtil() {
		FileUtil.FILE_NAME = "bprview.json";
		try {
			this.jsonFile = this.getFile();
			this.mainMap = this.readContent();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static FileUtil getInstace(){
		
		 return LazyHolder.INSTANCE;
	}
	
	public static FileUtil getInstace(IProject project){
		FileUtil.project = project;
		return LazyHolder.INSTANCE;
	}

	public void setProject(IProject project) {
		this.project = project;
	}
	
	
	//나중에 private로 변경
	public File getFile() throws IOException {
//		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		//해당 파일이 있는 프로젝트는 action delegate에서 받아온다.
		ObjectMapper mapper = new ObjectMapper();
//		this.project = BprPopupActionDelegate.project;
		if(this.project == null) {
			return null;
		}
		IPath path = project.getRawLocation().append(FILE_NAME);
		File f = path.toFile();
		//map list로 변경 		
		if(f.exists()) {
			try {
//				mainMap = mapper.readValue(f, Map.class);
				mainMap = mapper.readValue(f,  new TypeReference<Map<String, List<FileVO>>>() {});
			} catch (MismatchedInputException e) {
				System.out.println("파일지우기");
				f.deleteOnExit();
				mapper.writeValue(f, mainMap);
			}
		}else if(!f.exists()) {
			f.createNewFile();
			mapper.writeValue(f, mainMap);
		}
		return f;
	}
	
	
	public Map<String, List<FileVO>> readContent() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
//		return mapper.readValue(jsonFile, Map.class); 
		return mapper.readValue(jsonFile, new TypeReference<Map<String, List<FileVO>>>() {}); 
	}
	
	
	//테스트필요
	public JsonNode readContentJsonNode() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(jsonFile, JsonNode.class); 
	}
	
	public void writeContent() {
		ObjectMapper mapper = new ObjectMapper();
		try {
//			mapper.writeValue(jsonFile, mapper.convertValue(list, JsonNode.class).toPrettyString());
//			list = dubuplicationCheck(list , FileVO::getTargetKey);
			
			for(String mainPath : mainMap.keySet()) {
				List<FileVO> list = mainMap.get(mainPath);
				if(!list.isEmpty()) {
					list = dubuplicationCheck( list , FileVO::getTargetKey);
				}
				mainMap.put(mainPath, list);
			}
			
			mapper.writeValue(jsonFile, mainMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//파일읽기 실패시 다이어로그 있어야함.
			e.printStackTrace();
		}
	}
	
	public void setMethod(String mainPath ,FileVO vo) {
//		ObjectMapper mapper = new ObjectMapper();
//		String jsonString = mapper.writeValueAsString(vo);
//		JsonNode jsonNode = mapper.convertValue(list, JsonNode.class);
//		jsonNode.findValuesAsText(fieldName)
		//중복데이터 체크해서 덮어쓰기해야함. set으로하는거 말고 구현이 필요할듯.
		
		List<FileVO> list = mainMap.get(mainPath);
		if(list == null ) {
			list = new ArrayList<FileVO>();
		}
		list.add(vo);
		mainMap.put(mainPath, list);
		
//		list = dubuplicationCheck(list , FileVO::getTargetKey);
//		this.writeContent(); //언제 작성할지에 대한 시점 필요함. 우선 매번 새로쓰는걸로.
		
		
	}
	
	private <T> List<T> dubuplicationCheck(List<T> list , Function<? super T, ?> key ) {
		
		Stream<T> s = list.stream();
		s = s.filter(this.dubuplicationCheck(key));
		return s.collect(Collectors.toList());  
	}
	
	private <T> Predicate<T> dubuplicationCheck( Function<? super T, ?> key ) {
		final Set<Object> set = ConcurrentHashMap.newKeySet();
		return predicate -> set.add(key.apply(predicate));
	}
	
	private static class LazyHolder {
        private static final FileUtil INSTANCE = new FileUtil();
    }
}
