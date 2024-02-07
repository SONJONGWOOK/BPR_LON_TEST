package hanabank.bpr.navigator;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.navigator.NavigatorContentService;
import org.eclipse.ui.internal.navigator.NavigatorContentServiceLabelProvider;
import org.eclipse.ui.navigator.IDescriptionProvider;

public class CustomLableProvider extends LabelProvider implements ILabelProvider, IDescriptionProvider {
	
	public Image getImage(Object element) {
		if (element instanceof AnnotationTreeData)
			return PlatformUI.getWorkbench().getSharedImages().getImage(
					ISharedImages.IMG_OBJS_INFO_TSK); 
		return null;
	}

	public String getText(Object element) {
		if (element instanceof AnnotationTreeData) {
			AnnotationTreeData data = (AnnotationTreeData) element;
			return data.getName() + "= " + data.getValue(); //$NON-NLS-1$
		}  
		return null;
	}

	public String getDescription(Object anElement) {
		if (anElement instanceof AnnotationTreeData) {
			AnnotationTreeData data = (AnnotationTreeData) anElement;
			return "Annotation: " + data.getName(); //$NON-NLS-1$
		}
		return null;
	}
		

}
