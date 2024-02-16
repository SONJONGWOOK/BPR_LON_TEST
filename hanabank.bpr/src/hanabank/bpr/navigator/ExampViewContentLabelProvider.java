package hanabank.bpr.navigator;

import java.io.IOException;
import java.util.List;

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

import com.fasterxml.jackson.databind.JsonNode;

import hanabank.bpr.util.FileUtil;
import hanabank.bpr.views.ExampleView;

public class ExampViewContentLabelProvider extends JavaElementLabelProvider implements ILabelProvider, IStyledLabelProvider , IBaseLabelProvider  {

	private FileUtil fileutil = null;
	private IProject project;
	
	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		return super.getImage(element);
	}

	@Override
	public String getText(Object element) {
		// TODO Auto-generated method stub
		String text = super.getText(element);
		if(!(element instanceof JavaElement)) {
			return text;
		}
		JavaElement inner = (JavaElement) element;
		if(inner.getElementType() == JavaElement.METHOD) {
			SourceMethod sm = (SourceMethod) inner;
			System.out.println("methodName  = "+sm.getElementName());
			fileutil = fileutil == null ? FileUtil.getInstace(sm.getJavaProject().getProject()) : fileutil;
			fileutil.setProject(project);
			
			try {
				JsonNode jn = fileutil.readContentJsonNode();
				List<String> targetList = jn.findValuesAsText("targetKey");
				System.out.println(targetList);
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
			try {
				System.out.println(sm.getElementName());
				System.out.println(sm.getSource());
				System.out.println(sm.getSourceRange());
				IAnnotation[] sma = sm.getAnnotations();
				for(IAnnotation ain  : sma ) {
					System.out.println(ain);
					System.out.println(ain.getElementName());
					for(IMemberValuePair min : ain.getMemberValuePairs() ) {
						System.out.println(min.getValue());
						text = sm.getElementName() +" : ServiceCode " + min.getValue();
					}
				}
				
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return text;
		
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

