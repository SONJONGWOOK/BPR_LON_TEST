package hanabank.bpr.views;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jdt.internal.ui.packageview.PackageExplorerContentProvider;
import org.eclipse.jdt.internal.ui.packageview.PackageExplorerPart;

import hanabank.bpr.navigator.CustomContentProvider;

public class ExampleView extends PackageExplorerPart  {

	@Override
	public PackageExplorerContentProvider createContentProvider() {
		// TODO Auto-generated method stub
//		Object test = super.createContentProvider();
		PackageExplorerContentProvider test = new CustomContentProvider(true);
//		test.getHierarchicalPackageParent(child)
		
		IEclipsePreferences workbenchPrefs = InstanceScope.INSTANCE.getNode("org.eclipse.ui.workbench");
		workbenchPrefs.putBoolean("org.eclipse.ui.commands/state/org.eclipse.ui.navigator.resources.nested.changeProjectPresentation/org.eclipse.ui.commands.radioState", true);

	    IEclipsePreferences jdtPrefs = InstanceScope.INSTANCE.getNode("org.eclipse.jdt.ui");
	    jdtPrefs.putInt("org.eclipse.jdt.internal.ui.navigator.layout", 1);
	    
		return (PackageExplorerContentProvider) test;
		
	}
	
	
	
	
	
	
//	createLabelProvider는 없는데?
	
	
	
	
	
	
	
}
