package hanabank.bpr.views.actions;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.internal.WorkbenchPlugin;

public class JobUtil extends Job{

	public static Job job = null; 
	private IViewActionDelegate viewAction;
	
	
	public static Job getInstance(String title) throws Exception {
		//process Infomation dialog background config 진행창 기본이 백그라운드설정 이걸로하면 됨.
		WorkbenchPlugin.getDefault().getPreferenceStore().setValue("RUN_IN_BACKGROUND", false);
		
		if(job != null && job.getState() != Job.NONE) {
			throw new Exception("job이 현재 실행중입니다");
		}else if(job == null) {
			job = new JobUtil(title);
		}
				
		return job;
	}
	
	private JobUtil(String name) {
		super(name);
	}
	
	public void setBprPopupAction(BprPopupActionDelegate viewAction) {
		this.viewAction = viewAction;
	}
	
	@Override
	protected IStatus run(IProgressMonitor monitor) {
		System.out.println("run start");
		monitor.beginTask( "parsing Data...", IProgressMonitor.UNKNOWN );
		try {
		      monitor.subTask( "Opening Database..." );
		      System.out.println("Opening Database");
		      Thread.sleep(3000);
		      monitor.subTask( "Reading Elements..." );
		      Thread.sleep(3000);
		      System.out.println("Reading Elements");
		      Thread.sleep(3000);
		      ((BprPopupActionDelegate) this.viewAction).action();
		      // read elements
		    } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
		      System.out.println("done");
		      monitor.done();
		    }
		    return Status.OK_STATUS;
	}
	
	
	

}
