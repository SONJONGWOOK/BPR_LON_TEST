package hanabank.bpr.views;

import org.eclipse.jdt.ui.StandardJavaElementContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.ToolTip;
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
import org.eclipse.ui.internal.navigator.NavigatorContentService;
import org.eclipse.ui.internal.navigator.NavigatorContentServiceContentProvider;
import org.eclipse.ui.internal.navigator.NavigatorContentServiceLabelProvider;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.eclipse.ui.navigator.resources.ProjectExplorer;
import org.eclipse.ui.part.ViewPart;

import hanabank.bpr.navigator.ExampViewContentLabelProvider;
import hanabank.bpr.navigator.ExampViewContentProvider;

public class ExampleView extends CommonNavigator{

	public static final String ID = "hanabank.bpr.navigator.view";

	@Override
	protected void initListeners(TreeViewer viewer) {
		// TODO Auto-generated method stub
		ColumnViewerToolTipSupport.enableFor(viewer ,ToolTip.RECREATE);
		super.initListeners(viewer);
	}

	@Override
	protected CommonViewer createCommonViewer(Composite aParent) {
		// TODO Auto-generated method stub
		 CommonViewer cv = super.createCommonViewer(aParent);
//		 IBaseLabelProvider lp = cv.getLabelProvider();
//		 NavigatorContentService nc = (NavigatorContentService) cv.getNavigatorContentService();
		 ExampViewContentLabelProvider elp = new ExampViewContentLabelProvider();
//		 ExampViewContentProvider ecp = new ExampViewContentProvider();
//		 NavigatorContentServiceLabelProvider b = (NavigatorContentServiceLabelProvider) cv.getLabelProvider();
//		 b.getToolTipText(anElement);
//		 
//		 NavigatorContentServiceContentProvider a = (NavigatorContentServiceContentProvider) cv.getContentProvider();
//		 ITreeContentProvider contentProvider= new StandardJavaElementContentProvider(true);
//		 cv.setContentProvider(contentProvider);
//		 cv.setContentProvider(ecp);
	     cv.setLabelProvider(elp);
	     
	    return cv; 
	}
	
	

//	@Override
//	public void createPartControl(Composite parent) {
//		System.out.println("createPart");
//		// TODO Auto-generated method stub
//		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//		IViewPart view = page.findView(IPageLayout.ID_PROJECT_EXPLORER);                
//		view.createPartControl(parent);	
//		
//		ProjectExplorer expl = (ProjectExplorer) page.findView(IPageLayout.ID_PROJECT_EXPLORER);
//		INavigatorContentService content = expl.getNavigatorContentService();
//		content.getActivationService().deactivateExtensions(new String[] {ID}, false);
//		
//		
//
//		
//		
//	}
//
//	@Override
//	public void setFocus() {
//		// TODO Auto-generated method stub
//		
//	}

	
	
}
