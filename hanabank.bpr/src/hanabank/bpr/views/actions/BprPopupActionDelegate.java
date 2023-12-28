package hanabank.bpr.views.actions;

import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.eclipse.ui.navigator.NavigatorContentServiceFactory;
import org.eclipse.ui.navigator.resources.ProjectExplorer;

public class BprPopupActionDelegate implements IObjectActionDelegate, IViewActionDelegate , IEditorActionDelegate{
	
	private IWorkbenchPart targetPart;
	private IProject project;
	private ISelection selection;

	
	IResource getResource(IProject project, ISelection selection) throws JavaModelException {
//		IResource getResource(IProject project, String folderPath, String fileName) throws JavaModelException {
		TreeSelection treeSelection = (TreeSelection) selection;
		IJavaProject javaProject = JavaCore.create(project);
		IContainer resource  = javaProject.getResource().getParent();
		
		 IFolder f = Platform.getAdapterManager().getAdapter(treeSelection.getFirstElement(), IFolder.class);
		 Platform.getAdapterManager().getAdapter(treeSelection.getFirstElement(), IFile.class);
		 
		 
		
        
//	    resource.findMember
	    
	    
//	    for (IPackageFragmentRoot root : javaProject.getAllPackageFragmentRoots()) {
//	        IPackageFragment folderFragment = root.getPackageFragment(folderPath);
//	        IResource folder = folderFragment.getResource();
//	        if (folder == null || ! folder.exists() || !(folder instanceof IContainer)) {
//	            continue;
//	        }
//	
//	        IResource resource = ((IContainer) folder).findMember(fileName);
//	        if (resource.exists()) {
//	            return resource;
//	        }
//	    }
	    // file not found in any source path
	    return null;
	}
	
	@Override
	public void run(IAction action) {
		// TODO Auto-generated method 
		//만약에 한다고하면 여기서 소스 파싱 관련된 기능이 필요함.
//		MessageDialog.openInformation(this.targetPart.getSite().getShell(),"add to Favorites " , "triggered the " + getClass().getName() + " action");
		System.out.println("popup action delegate run");
		
		System.out.println("workbenchpart "  + targetPart);
		System.out.println("IProject "  + project);
		System.out.println("ISelection "  + selection);
		
		
//		ISelectionService  selectionService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		
		String targetSelection = targetPart.getSite().getId(); 	
	    ISelection selection = this.selection;    
//	    ISelection selection = selectionService.getSelection(targetSelection);
	    
	    if(selection instanceof ITreeSelection) {
	    	TreeSelection treeSelection = (TreeSelection) selection;
	    	System.out.println(treeSelection.getPaths().toString());
	    	
	    	if (((IStructuredSelection) selection).getFirstElement() instanceof IResource) {    
	            project= ((IResource)((IStructuredSelection) selection).getFirstElement()).getProject();
	            try {
					this.getResource(project, selection);
				} catch (JavaModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	
	    	
	    }
	    
	    //path에 있는 파일 가져오는거 해야함.


//	    if(selection instanceof IStructuredSelection) {    
//	        Object element = ((IStructuredSelection)selection).getFirstElement();    
//
//	        if (element instanceof IResource) {    
//	            project= ((IResource)element).getProject();    
//
//	        }  
//	        else if (element instanceof IPackageFragmentRoot) {    
//	            IJavaProject jProject = ((IPackageFragmentRoot)element).getJavaProject(); 
//	            project = jProject.getProject();
//	        }
//	        else if (element instanceof IJavaElement) {    
//	            IJavaProject jProject= ((IJavaElement)element).getJavaProject();    
//	            project = jProject.getProject();    
//	        }
//	    }
	    
	    
	    
	    
//		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
//		project = root.getProject("teste1111");
//		
//		
//		System.out.println("######## " + project);
//		if (!project.isOpen())
//			try {
//				project.open(null);
//			} catch (CoreException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		if (!project.exists())
//			try {
//				project.create(null);
//				
//			} catch (CoreException e) {
//				e.printStackTrace();
//			}
//		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
//	    if (window != null)
//	    {
//	        IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection();
//	        Object firstElement = selection.getFirstElement();
//	        if (firstElement instanceof IAdaptable)
//	        {
//	            IProject project = (IProject)((IAdaptable)firstElement).getAdapter(IProject.class);
//	            IPath path = project.getFullPath();
//	            System.out.println(path);
//	        }
//	    }
		
		
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		System.out.println("selectionChanged");
		this.selection = selection;
		
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub
		System.out.println("setActivePart");
		
		this.targetPart = targetPart;
		
	}                                                                                                                                 

	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub
		System.out.println("iVeiwPart init");
		System.out.println(view);
		this.targetPart = view;
//		MessageDialog.openInformation(this.targetPart.getSite().getShell(),"add to Favorites " , "triggered the " + getClass().getName() + " action");
		
	}

	
	
	@Override
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		// TODO Auto-generated method stub
		System.out.println("set Active Editor");
		this.targetPart = targetEditor;
	}


}
