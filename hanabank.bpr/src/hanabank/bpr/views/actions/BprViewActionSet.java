package hanabank.bpr.views.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class BprViewActionSet implements IWorkbenchWindowActionDelegate{

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		System.out.println("@@@run");
		
		
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		System.out.println("@@@selectionChanged");
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		System.out.println("@@@dispose");
		
	}

	@Override
	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		System.out.println("@@@init");
		
	}

}
