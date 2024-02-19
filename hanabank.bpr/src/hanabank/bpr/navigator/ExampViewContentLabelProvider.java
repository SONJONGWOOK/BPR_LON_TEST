package hanabank.bpr.navigator;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.IMemberValuePair;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.internal.compiler.ast.MethodDeclaration;
import org.eclipse.jdt.internal.core.JavaElement;
import org.eclipse.jdt.internal.core.SourceMethod;
import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.IToolTipProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.internal.navigator.NavigatorContentService;
import org.eclipse.ui.internal.navigator.NavigatorContentServiceLabelProvider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import hanabank.bpr.util.FileUtil;
import hanabank.bpr.util.FileVO;
import hanabank.bpr.views.ExampleView;

public class ExampViewContentLabelProvider extends JavaElementLabelProvider implements ILabelProvider, IStyledLabelProvider , IBaseLabelProvider  {

	private FileUtil fileutil = null;
	private IProject project;
	private Set<FileVO> targetList = new HashSet<FileVO>();
	
	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		return super.getImage(element);
	}

	@Override
	public String getText(Object element) {
		// TODO Auto-generated method stub
		ObjectMapper obj = new ObjectMapper();
		String text = super.getText(element);
		if(!(element instanceof JavaElement)) {
			return text;
		}
		JavaElement inner = (JavaElement) element;
		if(inner.getElementType() == JavaElement.METHOD) {
			SourceMethod sm = (SourceMethod) inner;
			System.out.println("methodName  = "+sm.getElementName());
			fileutil = fileutil == null ? FileUtil.getInstace(sm.getJavaProject().getProject()) : fileutil;
//			fileutil.setProject(project);
			try {
				System.out.println(this.targetList);
				JsonNode jn = fileutil.readContentJsonNode();
//				jsonndoe에서 key가 지금 java파일 패스로지정되어있음 
//				"/complete/src/main/java/com/example/springboot/HelloController.java" 키를 가진 값을 다 찾으면됨.
//				->{
//				  "/complete/src/main/java/com/example/springboot/HelloController.java" : [ {
//					    "lineNumber" : 18,
//					    "javaDoc" : "/** \n * 문서화 주석 대상의 요약 설명이다.\n * @param a - ~~ 문자열\n * @param b - ~~ 문자열\n * @return a와 b를 더한 문자열\n * @throws 어떤 상황에서 예외가 발생!\n */\n",
//					    "targetKey" : "/complete/src/main/java/com/example/springboot/HelloController.java.index",
//					    "methodName" : "index",
//					    "annotationValue" : "\"/test1\"",
//					    "fullPath" : "/complete/src/main/java/com/example/springboot/HelloController.java",
//					    "className" : "HelloController.java",
//					    "packageName" : "com.example.springboot",
//					    "LineNumber" : 18,
//					    "JavaDoc" : "/** \n * 문서화 주석 대상의 요약 설명이다.\n * @param a - ~~ 문자열\n * @param b - ~~ 문자열\n * @return a와 b를 더한 문자열\n * @throws 어떤 상황에서 예외가 발생!\n */\n"
//					  }, {
				
				
				
//				String fullPath = sm.getPath().toPortableString();
//				System.out.println(jn.get(fullPath));
//				JsonNode jn1 = jn.path(sm.getPath().toPortableString());
//				JsonNode list = jn.findParent(sm.getPath().toPortableString());
				
//				System.out.println(list);
				
				
//				
//				for(JsonNode el : list) {
//					if(el.path("methodName").asText().equalsIgnoreCase(sm.getElementName()) ){
//						//매칭되었을때 정보를 출력하기위한 메서드호출
//						text = outText(el);
//						
//					}
//				}
				
//				for(JsonNode target : jn) {
//					if(target.get("fullPath").asText().equals(sm.getPath().toPortableString())){
//						this.targetList.add(obj.treeToValue(target,FileVO.class));
//					}
//				}
				
//				List<String> targetList = jn.findValuesAsText("targetKey");
				
				
//				sm.getResource().getName() .java 파일이름
				
				//sm.getOpenableParent()?? 아마도 여는거?
//				this.targetKey = this.packageName + this.className + this.methodName;
				
//				MethodDeclaration methodDeclarationNode = (MethodDeclaration) inner;
//				String compaerTarget = sm.get
//				for(targetList.equals(sm.getElementName())
			} catch (IOException Ioerrror) {
				// TODO Auto-generated catch block
				Ioerrror.printStackTrace();
			}
//			IViewPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ExampleView.ID);
//			try {
//				System.out.println(sm.getElementName());
//				System.out.println(sm.getSource());
//				System.out.println(sm.getSourceRange());
//				IAnnotation[] sma = sm.getAnnotations();
//				for(IAnnotation ain  : sma ) {
//					System.out.println(ain);
//					System.out.println(ain.getElementName());
//					for(IMemberValuePair min : ain.getMemberValuePairs() ) {
//						System.out.println(min.getValue());
//						text = sm.getElementName() +" : ServiceCode " + min.getValue();
//					}
//				}
//				
//			} catch (JavaModelException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		
		return text;
		
	}
	

	private String outText(JsonNode node) throws JsonProcessingException {
		StringBuilder output = null;
		ObjectMapper mapper = new ObjectMapper();
		FileVO vo = mapper.treeToValue(node,FileVO.class);
		
		return vo.getAnnotationValue() + " / " +vo.getJavaDoc();
	}
//	public class ExampViewContentLabelProvider extends NavigatorContentServiceLabelProvider implements ILabelProvider, IStyledLabelProvider  , IBaseLabelProvider{




	
//
//	public ExampViewContentLabelProvider(NavigatorContentService aContentService) {
//		super(aContentService);
//	}
//
//	@Override
//	public String getText(Object anElement) {
//		// TODO Auto-generated method stub
//		return super.getText(anElement);
//		
//	}
//
//	@Override
//	public String getToolTipText(Object anElement) {
//		// TODO Auto-generated method stub
//		return super.getToolTipText(anElement);
//	}
	
	

}

