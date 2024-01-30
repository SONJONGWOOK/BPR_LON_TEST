package hanabank.bpr.views;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.eclipse.ui.navigator.resources.ProjectExplorer;
import org.eclipse.ui.part.ViewPart;

public class ExampleView extends CommonNavigator{

	public static final String ID = "hanabank.bpr.navigator.view";

	@Override
	public void createPartControl(Composite parent) {
		System.out.println("createPart");
		// TODO Auto-generated method stub
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IViewPart view = page.findView(IPageLayout.ID_PROJECT_EXPLORER);                
		view.createPartControl(parent);
		
		ProjectExplorer expl = (ProjectExplorer) page.findView(IPageLayout.ID_PROJECT_EXPLORER);
		INavigatorContentService content = expl.getNavigatorContentService();
		content.getActivationService().deactivateExtensions(new String[] {ID}, false);
		
		

		
		
	}
//
//	@Override
//	public void setFocus() {
//		// TODO Auto-generated method stub
//		
//	}

	
	
}
