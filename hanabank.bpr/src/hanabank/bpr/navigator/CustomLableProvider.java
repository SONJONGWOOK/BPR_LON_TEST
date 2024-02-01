package hanabank.bpr.navigator;

import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;

public class CustomLableProvider extends JavaElementLabelProvider implements ILabelProvider{

	public CustomLableProvider() {
		super();
	}
	
//	/**
//	 * Provides a label and icon for objects of type {@link PropertiesTreeData}.
//	 * 
//	 * @since 3.2
//	 *
//	 */
//
//	public Image getImage(Object element) {
//		if (element instanceof PropertiesTreeData)
//			return PlatformUI.getWorkbench().getSharedImages().getImage(
//					ISharedImages.IMG_OBJS_INFO_TSK); 
//		return null;
//	}
//
//	public String getText(Object element) {
//		if (element instanceof PropertiesTreeData) {
//			PropertiesTreeData data = (PropertiesTreeData) element;
//			return data.getName() + "= " + data.getValue(); //$NON-NLS-1$
//		}  
//		return null;
//	}
//
//	public String getDescription(Object anElement) {
//		if (anElement instanceof PropertiesTreeData) {
//			PropertiesTreeData data = (PropertiesTreeData) anElement;
//			return "Property: " + data.getName(); //$NON-NLS-1$
//		}
//		return null;
//	}
		

}
