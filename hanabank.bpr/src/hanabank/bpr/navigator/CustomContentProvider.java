package hanabank.bpr.navigator;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.metal.MetalIconFactory.TreeControlIcon;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
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
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.internal.navigator.NavigatorContentService;
import org.eclipse.ui.internal.navigator.NavigatorContentServiceContentProvider;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonNavigatorManager;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.eclipse.ui.navigator.NavigatorActionService;
import org.eclipse.ui.navigator.NavigatorContentServiceFactory;

public class CustomContentProvider  implements ITreeContentProvider  {
//	public class AnnotationContentProvider implements ITreeContentProvider , IResourceChangeListener, IResourceDeltaVisitor {

	
	private static final Object[] NO_CHILDREN = new Object[0];

	private static final Object PROPERTIES_EXT = "java"; //$NON-NLS-1$

	private final Map/*<IFile, PropertiesTreeData[]>*/ cachedModelMap = new HashMap();

	private StructuredViewer viewer;
	
	//TEST
	private static Viewer myViewer;
	
	/**
	 * Create the PropertiesContentProvider instance.W
	 * 
	 * Adds the content provider as a resource change listener to track changes on disk.
	 *
	 */

//	public Object[] getChildren(Object arg0) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
	/**
	 * Return the model elements for a *.properties IFile or
	 * NO_CHILDREN for otherwise.
	 */
	public Object[] getChildren(Object parentElement) {
		Object[] children = null;
		System.out.println("anootaion getChildren");
		
		
//		 Object[] children = null;
//	        if (CustomProjectWorkbenchRoot.class.isInstance(parentElement)) {
//	            if (_customProjectParents == null) {
//	                _customProjectParents = initializeParent(parentElement);
//	            }
//	 
//	            children = _customProjectParents;
//	        } else if (ICustomProjectElement.class.isInstance(parentElement)) {
//	            children = ((ICustomProjectElement) parentElement).getChildren();
//	        } else {
//	            children = NO_CHILDREN;
//	        }
//	 
//	        return children;
		
		
//		INavigatorContentService n = NavigatorContentServiceFactory.INSTANCE.createContentService("hanabank.bpr.navigator.view");
//		ITreeContentProvider tcp = n.createCommonContentProvider();
//		Object[] els = tcp.getElements(new Object());
		
		System.out.println(parentElement);
		
		if (parentElement instanceof AnnotationTreeData) {
			System.out.println("AnnotationTreeData");
			children = NO_CHILDREN;
		}else if(parentElement instanceof IFile) {
			
			System.out.println("IFile");
			IFile modelFile = (IFile) parentElement;
			if(PROPERTIES_EXT.equals(modelFile.getFileExtension())) {
				
//				children = (AnnotationTreeData[]) cachedModelMap.get(modelFile);
				//잠시 주석처리
//				if(children == null && updateModel(modelFile) != null) {
//					children = (AnnotationTreeData[]) cachedModelMap.get(modelFile);
//				}
			}
		}
		
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
		
		return children != null ? children : NO_CHILDREN;
		
	}  

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		System.out.println("inputChanged"  );
		System.out.println(viewer  );
		System.out.println(oldInput );
		System.out.println(newInput);
		
		this.myViewer = viewer;
		
		ITreeContentProvider.super.inputChanged(viewer, oldInput, newInput);
	}


	/**
	 * Load the model from the given file, if possible.  
	 * @param modelFile The IFile which contains the persisted model 
	 */ 
	private synchronized Annotation updateModel(IFile modelFile) {
				
		if(PROPERTIES_EXT.equals(modelFile.getFileExtension()) ) {
			System.out.println("target Ext java");
			
			
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
						
						Javadoc currntDoc= currentMethod.getJavadoc();
						
						for (Object tag : currntDoc.tags()) {
							
							TagElement tagElement = (TagElement) tag;
							
							String tagName = tagElement.getTagName() == null  ? null : tagElement.getTagName().trim();
							
							if("@param".equals(tagName)) {
								for(Object inner : tagElement.fragments()) {
									System.out.println(inner);
								}
								
							}else if("@return".equals(tagName)) {
								
								for(Object inner : tagElement.fragments()) {
									System.out.println(inner);
								}
							}else if("@logicalname".equals(tagName)) {
								
								for(Object inner : tagElement.fragments()) {
									System.out.println(inner);
								}
							}
							
						}
						
						
						return super.visit(node);
					}

//					@Override
//					public boolean visit(Javadoc node) {
//						System.out.println(currentMethod);
//						if(currentMethod != null) {
//							System.out.println(currentMethod.getName().getIdentifier());
//							
//							for(Object inner : currentMethod.modifiers()) {
////								System.out.println(inner);
//							}
//							
//
//							
//							for (Object tag : node.tags()) {
//								
//								TagElement tagElement = (TagElement) tag;
//								
//								String tagName = tagElement.getTagName() == null  ? null : tagElement.getTagName().trim();
//								
//								if("@param".equals(tagName)) {
//									for(Object inner : tagElement.fragments()) {
//										System.out.println(inner);
//									}
//									
//								}else if("@return".equals(tagName)) {
//									
//									for(Object inner : tagElement.fragments()) {
//										System.out.println(inner);
//									}
//								}else if("@logicalname".equals(tagName)) {
//									
//									for(Object inner : tagElement.fragments()) {
//										System.out.println(inner);
//									}
//								}
//								
//								
//								
//							}
//							
//							
//							
//						}
//
//						return super.visit(node);
//					}

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
					
					
			    	
					
					
			    	
			    	
			    	
//			        public boolean visit(AnnotationTypeDeclaration node) {
//			            System.out.println("Annotaion: " + node.getName());
//			            return true;
//			        }
//
//			        public boolean visit(TypeDeclaration node) {
//			            System.out.println("Type: " + node.getName());
//			            return true;
//			        }
			    });
				
				
				
				
				
//				// gets URI for EFS.
//				URI uri = modelFile.getLocationURI();
//				
//
//				// what if file is a link, resolve it.
//				if(modelFile.isLinked()){
//				   uri = modelFile.getRawLocationURI();
//				}
				
				
				

				// Gets native File using EFS
//				File javaFile = EFS.getStore(uri).toLocalFile(0, new NullProgressMonitor());
//				ArrayList<URL> urls = new ArrayList<>();
//				
//				urls.add(uri.toURL());
//				
//				
//				
//				URLClassLoader urlCl = new URLClassLoader(urls.toArray(new URL[urls.size()]));
//				
//				System.out.println(urlCl);
//				Class<?> clz = urlCl.loadClass("BHC");
//				
//				System.out.println(clz);
				
				
				
				
								
//				Reflections reflections = new Reflections(urlCl,new TypeAnnotationsScanner(),new SubTypesScanner(true));
//	            Set<Class<?>> classes = reflections.getTypesAnnotatedWith(<???>.class);
//	            System.out.println(classes);
				
				 
				
//				Reflections reflections = new Reflections(urlCl,new TypeAnnotationsScanner(),new SubTypesScanner(true));
//		        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(<???>.class);
//		        System.out.println(classes);
		            

//				IProject project = modelFile.getProject();
//				IJavaProject targetProject = JavaCore.create(project);
//	            final IClasspathEntry[] resolvedClasspath = targetProject.getResolvedClasspath(true);
//	            ArrayList<URL> urls = new ArrayList<>();
//	            for (IClasspathEntry classpathEntry : resolvedClasspath) {
//	
//	                if (classpathEntry.getPath().toFile().isAbsolute()) {
//	                    urls.add(classpathEntry.getPath().toFile().toURI().toURL());
//	                } else {
//	                    urls.add(new File(project.getWorkspace().getRoot().getLocation().toFile(),classpathEntry.getPath().toString()).toURI().toURL());
//	                }
//	            }
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		
		}
		
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
		
		
		return null; 
	}

	public Object getParent(Object element) {
		if (element instanceof AnnotationTreeData) {
			AnnotationTreeData data = (AnnotationTreeData) element;
			return data.getFile();
		} 
		return null;
	}

	public boolean hasChildren(Object element) {		
		if (element instanceof AnnotationTreeData) {
			return false;		
		} else if(element instanceof IFile) {
			return PROPERTIES_EXT.equals(((IFile) element).getFileExtension());
		}
		return false;
	}

	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}
	
}
