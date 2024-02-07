package hanabank.bpr.navigator.view.decorator;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.IMemberValuePair;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.internal.core.JavaElement;
import org.eclipse.jdt.internal.core.MemberValuePair;
import org.eclipse.jdt.internal.core.SourceMethod;
import org.eclipse.jdt.ui.JavaElementSorter;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import hanabank.bpr.views.ExampleView;

public class BprViewDecorator extends ColumnLabelProvider implements ILabelDecorator {

	@Override
	public Image decorateImage(Image image, Object element) {
		// TODO Auto-generated method stub
		System.out.println("decorateImage");
		return super.getImage(element);
	}

	@Override
	public String decorateText(String text, Object element) {
		// TODO Auto-generated method stub
		System.out.println("decorateText");
		IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage activePage = workbenchWindow.getActivePage();
		IWorkbenchPart avtivePart = activePage.getActivePart();
		return super.getText(element);
	}
	
	
//	@Override
//	public void update(ViewerCell cell) {
//		// TODO Auto-generated method stub
//		super.update(cell);
//	}
	
	

	
}

	
//	@Override
//	public Image decorateImage(Image image, Object element) {
//		System.out.println("decorateImg");
//		return super.getImage(element); 
//	}
//	
//	
//	@Override
//	public String decorateText(String text, Object element) {
//		System.out.println("decorateText");
//		IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
//		IWorkbenchPage activePage = workbenchWindow.getActivePage();
//		IWorkbenchPart avtivePart = activePage.getActivePart();
//		
//		if (avtivePart != null) {
//			String viewName = avtivePart.getSite().getId() == null ? "" : avtivePart.getSite().getId();
//			System.out.println("decotxt");
//			System.out.println(text);
//			System.out.println(element);
//			JavaElement inner = (JavaElement) element;
//			if(inner.getElementType() == JavaElement.METHOD && viewName.equals(ExampleView.ID)) {
//				SourceMethod sm = (SourceMethod) inner;
////				IViewPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ExampleView.ID);
//				try {
//					System.out.println(sm.getElementName());
//					System.out.println(sm.getSource());
//					System.out.println(sm.getSourceRange());
//					IAnnotation[] sma = sm.getAnnotations();
//					for(IAnnotation ain  : sma ) {
//						System.out.println(ain);
//						System.out.println(ain.getElementName());
//						for(IMemberValuePair min : ain.getMemberValuePairs() ) {
//							System.out.println(min.getValue());
//							text =sm.getElementName() +" : ServiceCode " + min.getValue();
//						}
//					}
////				IAnnoatation an = sm.getAnnotations();
//					
//				} catch (JavaModelException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			
//		}
//		
//		
//		
//		return text;
//	}
//public class BprViewDecorator extends ResourceExtensionLabelProvider implements ILabelDecorator {
//
//	@Override
//	public void decorate(Object element, IDecoration decoration) {
//		// TODO Auto-generated method stub
//		System.out.println("decorate");
//		System.out.println(element);
//		System.out.println(decoration);
//		
//		if(((IResource)element).getType() == IResource.FILE) {
//			IFile file = (IFile) element;
//			String extension = FilenameUtils.getExtension(file.getFullPath().toString());
////			if(extension.equals("png"))
////                decoration.addOverlay(DecoratorDescriptors.iconDescriptor_1);
////            else if(extension.equals("txt"))
////                decoration.addOverlay(DecoratorDescriptors.iconDescriptor_2);
//		}
//		
//	}
//
//	
//
//}
