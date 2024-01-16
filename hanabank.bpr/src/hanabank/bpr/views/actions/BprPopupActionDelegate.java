package hanabank.bpr.views.actions;

import java.util.Map;

import org.apache.commons.io.IOUtils;
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
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.ModuleDeclaration;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.TagElement;
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
	private static final Object PROPERTIES_EXT = "java"; //$NON-NLS-1$


	void process(IFile modelFile) {
		if(PROPERTIES_EXT.equals(modelFile.getFileExtension()) ) {
			System.out.println("file만 처리 " + modelFile);
			try {
				Map<String , String> options = JavaCore.getOptions();
				options.put(JavaCore.COMPILER_DOC_COMMENT_SUPPORT, JavaCore.ENABLED);
				options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_5); 
				options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_5); 
				options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_5);
				System.out.println(options);
				JavaCore.setComplianceOptions(JavaCore.VERSION_1_5, options);
				
				IJavaProject javaProject = JavaCore.create(modelFile.getProject());
				ASTParser parser = ASTParser.newParser(AST.JLS14);
				parser.setKind(ASTParser.K_COMPILATION_UNIT);
				parser.setProject(javaProject);
				parser.setResolveBindings(true);
				parser.setCompilerOptions(options);
				parser.setSource(IOUtils.toCharArray(modelFile.getContents() , modelFile.getCharset()));
				
				final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
			    cu.accept(new ASTVisitor() {
			    	
			    	MethodDeclaration currentMethod = null;
			    	
					@Override
					public boolean visit(AnnotationTypeDeclaration node) {
						// TODO Auto-generated method stub
						System.out.println("AnnotationTypeDeclaration : " + node);
						return super.visit(node);
					}


					@Override
					public boolean visit(MarkerAnnotation node) {
						// TODO Auto-generated method stub
						System.out.println("MarkerAnnotation : " + node);
						return super.visit(node);
					}


					@Override
					public boolean visit(NormalAnnotation node) {
						
						System.out.println("NormalAnnotation node : " + node);
						// TODO Auto-generated method stub
						return super.visit(node);
					}

					@Override
					public boolean visit(SingleMemberAnnotation node) {
						// TODO Auto-generated method stub
						System.out.println("SingleMemberAnnotation node : " + node);
						//어노테이션이름
						System.out.println("@@어노테이션이름");
						System.out.println(node.getTypeName().getFullyQualifiedName());
						System.out.println("##어노테이션 벨류");
						System.out.println(node.getValue());
						
						
						
						//sigleAnnotation 
						
						Javadoc currntDoc= currentMethod.getJavadoc();
						//javadoc 부분
						for (Object tag : currntDoc.tags()) {
							
							TagElement tagElement = (TagElement) tag;
							
							String tagName = tagElement.getTagName() == null  ? null : tagElement.getTagName().trim();
							
							if("@param".equals(tagName)) {
								System.out.println("@param 출력");
								for(Object inner : tagElement.fragments()) {
									System.out.println(inner);
								}
								
							}else if("@return".equals(tagName)) {
								System.out.println("@return 출력");
								
								for(Object inner : tagElement.fragments()) {
									System.out.println(inner);
								}
							}else if("@logicalname".equals(tagName)) {
								System.out.println("@logicalname 출력");
								
								for(Object inner : tagElement.fragments()) {
									System.out.println(inner);
								}
							}
							
						}
						
						
						return super.visit(node);
					}
					@Override
					public boolean visit(MethodDeclaration node) {
						// TODO Auto-generated method stub
 						currentMethod = node;
						return super.visit(node);
					}

					@Override
					public void endVisit(ModuleDeclaration node) {
						// TODO Auto-generated method stub
						super.endVisit(node);
					}
			    });
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	
	}
	
	
	IResource getResource(IResource resouce) throws CoreException {
		
		if(resouce instanceof IFolder) {
			IFolder target = (IFolder) resouce;
			for(IResource inner : target.members() ) {
//				System.out.println(inner);
//				System.out.println(inner instanceof IFolder);
//				System.out.println(inner instanceof IFile);
				this.getResource(inner);
				
			}
		}else if(resouce instanceof IFile) {
			this.process((IFile) resouce);
		}
		
//		IResource getResource(IProject project, String folderPath, String fileName) throws JavaModelException {
//		TreeSelection treeSelection = (TreeSelection) selection;
//		

		
//		if(Platform.getAdapterManager().getAdapter(treeSelection.getFirstElement(), IFolder.class) instanceof IFolder) {
//			IFolder target =  Platform.getAdapterManager().getAdapter(treeSelection.getFirstElement(), IFolder.class);
//			
//			
//			System.out.println(target);
//			System.out.println("folder "+target);
//			
//			for(IResource inner : target.members() ) {
//				
//				if(inner.getType() == IResource.FILE) {
//					//이건 내부함수로 처리
//					IFile innerFile =  Platform.getAdapterManager().getAdapter(treeSelection.getFirstElement(), IFile.class);
//					System.out.println("file "+innerFile);
//				}else if(inner.getType() == IResource.FOLDER) {
//					//이건 리커시브로 처리
//					System.out.println("testcode");
//				}
//				
////				IFile modelFile = (IFile) inner;
////				System.out.println(modelFile.getFileExtension());
//				
//				
//			}
//			
//		}else if(Platform.getAdapterManager().getAdapter(treeSelection.getFirstElement(), IFile.class) instanceof IFile) {
//			IFile target =  Platform.getAdapterManager().getAdapter(treeSelection.getFirstElement(), IFile.class);
//			System.out.println("file "+target);
//		}
		
		
//		 IFolder f = Platform.getAdapterManager().getAdapter(treeSelection.getFirstElement(), IFolder.class);
//		 Platform.getAdapterManager().getAdapter(treeSelection.getFirstElement(), IFile.class);
		 
		 
		
        
//		IJavaProject javaProject = JavaCore.create(project);
//		IContainer resource  = javaProject.getResource().getParent();
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
		
//		String targetSelection = targetPart.getSite().getId(); 	
	    ISelection selection = this.selection;    
//	    ISelection selection = selectionService.getSelection(targetSelection);
	    
	    if(selection instanceof ITreeSelection) {
	    	TreeSelection treeSelection = (TreeSelection) selection;
	    	System.out.println(treeSelection.getPaths().toString());
	    	
	    	if (((IStructuredSelection) selection).getFirstElement() instanceof IResource) {    
	            project= ((IResource)((IStructuredSelection) selection).getFirstElement()).getProject();
	            try {
	            	//입력인자를 IFolder , IFile 로 받아야 판별해서 리커시브 돌림.
	        		if(Platform.getAdapterManager().getAdapter(treeSelection.getFirstElement(), IFolder.class) instanceof IFolder) {
	        			IFolder target =  Platform.getAdapterManager().getAdapter(treeSelection.getFirstElement(), IFolder.class);
	        			System.out.println("folder "+target);
	        			this.getResource(target);
	        		}else if(Platform.getAdapterManager().getAdapter(treeSelection.getFirstElement(), IFile.class) instanceof IFile) {
	        			IFile target =  Platform.getAdapterManager().getAdapter(treeSelection.getFirstElement(), IFile.class);
	        			System.out.println("file "+target);
	        			this.getResource(target);
	        		}
										
				} catch (Exception e) {
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
