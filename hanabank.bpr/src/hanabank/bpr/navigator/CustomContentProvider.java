package hanabank.bpr.navigator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.JavaElement;
import org.eclipse.jdt.internal.core.SourceMethod;
import org.eclipse.jdt.internal.core.SourceType;
import org.eclipse.jdt.internal.ui.navigator.PackageExplorerOpenActionProvider;
import org.eclipse.jdt.internal.ui.packageview.PackageExplorerContentProvider;
import org.eclipse.jdt.internal.ui.packageview.PackageExplorerPart;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.progress.UIJob;

public class CustomContentProvider extends PackageExplorerContentProvider {

	
	public CustomContentProvider(boolean provideMembers) {
		super(provideMembers);
		super.setIsFlatLayout(false);
		super.setShowLibrariesNode(false);
		
		// TODO Auto-generated constructor stub
	}
	

//	public CustomContentProvider(boolean provideMembers) {
//		System.out.println("test");
//		super(provideMembers);
//		// TODO Auto-generated constructor stub
//		 
//	}
	
	
	@SuppressWarnings("restriction")
	@Override
	public Object[] getChildren(Object parentElement) {
		// TODO Auto-generated method stub
		
		
		Object[] test = super.getChildren(parentElement);
		List<JavaElement> test2 = new ArrayList<>();
		for(Object o : test) {
//			System.out.println(o);
			if(o instanceof JavaElement) {
				JavaElement inner = (JavaElement) o;
				System.out.println("테스트 " + inner.getElementName());
				System.out.println("테스트2 " + inner.getElementType());
				System.out.print("태스트3  ");
				System.out.println(inner instanceof SourceType);
				System.out.print("태스트4  ");
				System.out.println(inner instanceof SourceMethod);
				
				
				if(inner instanceof SourceType) {
					System.out.println("소스타입 걸리는애  " + inner.getElementName());
					System.out.println("소스타입 걸리는애  " + inner.getElementType());
					test2.add(inner);
				}
				
				if(inner.getElementType() == JavaElement.METHOD && inner instanceof SourceMethod) {
					SourceMethod sm = (SourceMethod) inner;
					System.out.println();
					System.out.println();
					System.out.println("메서드임");
					System.out.println(sm);
					
					System.out.println("이름" + sm.getElementName());
					System.out.println("패스" + sm.getPath());
					
					System.out.println();
					System.out.println();
					test2.add(inner);
					
				}
				
			}
//			if(o instanceof SourceType) {
//				System.out.println("소스");
//				System.out.println(o);
//
//			}
		}
		System.out.println(test2);
		return test2.toArray();
//		return test;
	}



//	private static final Object[] NO_CHILDREN = new Object[0];
//
//	private static final Object PROPERTIES_EXT = "java"; //$NON-NLS-1$
//
//	private final Map/*<IFile, PropertiesTreeData[]>*/ cachedModelMap = new HashMap();
//
//	private StructuredViewer viewer;

//	@Override
//	public Object[] getElements(Object inputElement) {
//		// TODO Auto-generated method stub
//		System.out.println(inputElement);
//		return null;
//	}
//
//	@Override
//	public Object[] getChildren(Object parentElement) {
//		// TODO Auto-generated method stub
//		System.out.println(parentElement);
//		return null;
//		
//	}
//
//	@Override
//	public Object getParent(Object element) {
//		System.out.println(element);
//		return null;
//	}
//
//	@Override
//	public boolean hasChildren(Object element) {
//		// TODO Auto-generated method stub
//		return false;
//	}
	
	/**
	 * Create the PropertiesContentProvider instance.
	 * 
	 * Adds the content provider as a resource change listener to track changes on disk.
	 *
	 */
//	public CustomContentProvider() {
////		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
//	}



	/**
	 * Return the model elements for a *.properties IFile or
	 * NO_CHILDREN for otherwise.
	 */
//	public Object[] getChildren(Object parentElement) {  
//		Object[] children = null;
//		if (parentElement instanceof PropertiesTreeData) { 
//			children = NO_CHILDREN;
//		} else if(parentElement instanceof IFile) {
//			/* possible model file */
//			IFile modelFile = (IFile) parentElement;
//			if(PROPERTIES_EXT.equals(modelFile.getFileExtension())) {				
//				children = (PropertiesTreeData[]) cachedModelMap.get(modelFile);
//				if(children == null && updateModel(modelFile) != null) {
//					children = (PropertiesTreeData[]) cachedModelMap.get(modelFile);
//				}
//			}
//		}   
//		return children != null ? children : NO_CHILDREN;
//	}  
	
//
//	/**
//	 * Load the model from the given file, if possible.  
//	 * @param modelFile The IFile which contains the persisted model 
//	 */ 
//	private synchronized Properties updateModel(IFile modelFile) { 
//		
//		if(PROPERTIES_EXT.equals(modelFile.getFileExtension()) ) {
//			Properties model = new Properties();
//			if (modelFile.exists()) {
//				try {
//					model.load(modelFile.getContents()); 
//					
//					String propertyName; 
//					List properties = new ArrayList();
//					for(Enumeration names = model.propertyNames(); names.hasMoreElements(); ) {
//						propertyName = (String) names.nextElement();
//						properties.add(new PropertiesTreeData(propertyName,  model.getProperty(propertyName), modelFile));
//					}
//					PropertiesTreeData[] propertiesTreeData = (PropertiesTreeData[])
//						properties.toArray(new PropertiesTreeData[properties.size()]);
//					
//					cachedModelMap.put(modelFile, propertiesTreeData);
//					return model; 
//				} catch (IOException e) {
//				} catch (CoreException e) {
//				}
//			} else {
//				cachedModelMap.remove(modelFile);
//			}
//		}
//		return null; 
//	}

//	public Object getParent(Object element) {
//		if (element instanceof PropertiesTreeData) {
//			PropertiesTreeData data = (PropertiesTreeData) element;
//			return data.getFile();
//		} 
//		return null;
//	}
//
//	public boolean hasChildren(Object element) {		
//		if (element instanceof PropertiesTreeData) {
//			return false;		
//		} else if(element instanceof IFile) {
//			return PROPERTIES_EXT.equals(((IFile) element).getFileExtension());
//		}
//		return false;
//	}
//
//	public Object[] getElements(Object inputElement) {
//		return getChildren(inputElement);
//	}
//
//	public void dispose() {
//		cachedModelMap.clear();
//		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this); 
//	}
//
//	public void inputChanged(Viewer aViewer, Object oldInput, Object newInput) {
//		if (oldInput != null && !oldInput.equals(newInput))
//			cachedModelMap.clear();
//		viewer = (StructuredViewer) aViewer;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
//	 */
//	public void resourceChanged(IResourceChangeEvent event) {
//
//		IResourceDelta delta = event.getDelta();
//		try {
//			delta.accept(this);
//		} catch (CoreException e) { 
//			e.printStackTrace();
//		} 
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
//	 */
//	public boolean visit(IResourceDelta delta) {
//
//		IResource source = delta.getResource();
//		switch (source.getType()) {
//		case IResource.ROOT:
//		case IResource.PROJECT:
//		case IResource.FOLDER:
//			return true;
//		case IResource.FILE:
//			final IFile file = (IFile) source;
//			if (PROPERTIES_EXT.equals(file.getFileExtension())) {
//				updateModel(file);
//				new UIJob("Update Properties Model in CommonViewer") {  //$NON-NLS-1$
//					public IStatus runInUIThread(IProgressMonitor monitor) {
//						if (viewer != null && !viewer.getControl().isDisposed())
//							viewer.refresh(file);
//						return Status.OK_STATUS;						
//					}
//				}.schedule();
//			}
//			return false;
//		}
//		return false;
//	}




	
}
