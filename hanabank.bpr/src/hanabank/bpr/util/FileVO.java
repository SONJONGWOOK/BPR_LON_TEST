package hanabank.bpr.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FileVO {
	
//	methodName : currentMethod.getName()
//	Annotation : URL내용
//	fullPath : cu.getPackage().getName() + currentMethod.getName() -> \com\example\springboot\HelloController.java
//	packageName  cu.getPackage().getName() com.example.springboot
//	LineNumber : cu.getLineNumber(currentMethod.getBody().getStartPosition())
//	JavaDoc : javadoc내용들
	
	@JsonProperty("methodName")
	private String methodName;
	
	@JsonProperty("annotationValue")
	private String annotationValue;
	
	@JsonProperty("fullPath")
	private String fullPath;
	
	@JsonProperty("className")
	private String className;
	
	@JsonProperty("packageName")
	private String packageName;
	
	@JsonProperty("LineNumber")
	private long LineNumber;
	
	@JsonProperty("JavaDoc")
	private String JavaDoc;

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getAnnotationValue() {
		return annotationValue;
	}

	public void setAnnotationValue(String annotationValue) {
		this.annotationValue = annotationValue;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public long getLineNumber() {
		return LineNumber;
	}

	public void setLineNumber(long lineNumber) {
		LineNumber = lineNumber;
	}

	public String getJavaDoc() {
		return JavaDoc;
	}

	public void setJavaDoc(String javaDoc) {
		JavaDoc = javaDoc;
	}
	
	
	
//	@JsonProperty("targetKey")
//	private String targetKey;
//	
//	@JsonProperty("targetInfo")
//	private FileInfo targetInfo;
//	
//		
//	public String getTargetKey() {
//		return targetKey;
//	}
//
//	public void setTargetKey(String targetKey) {
//		this.targetKey = targetKey;
//	}
//
//	public FileInfo getTargetInfo() {
//		return targetInfo;
//	}
//
//	public void setTargetInfo(FileInfo targetInfo) {
//		this.targetInfo = targetInfo;
//	}
//
//	public FileInfo newFileInfo() {
//		return new FileInfo();
//	}
//
//	public class FileInfo{
//		
//		@JsonProperty("methodName")
//		private String methodName;
//		
//		@JsonProperty("annotationValue")
//		private String annotationValue;
//		
//		@JsonProperty("fullPath")
//		private String fullPath;
//		
//		@JsonProperty("className")
//		private String className;
//		
//		@JsonProperty("packageName")
//		private String packageName;
//		
//		@JsonProperty("LineNumber")
//		private long LineNumber;
//		
//		@JsonProperty("JavaDoc")
//		private String JavaDoc;
//
//		public String getMethodName() {
//			return methodName;
//		}
//
//		public void setMethodName(String methodName) {
//			this.methodName = methodName;
//		}
//
//		public String getAnnotationValue() {
//			return annotationValue;
//		}
//
//		public void setAnnotationValue(String annotationValue) {
//			this.annotationValue = annotationValue;
//		}
//
//		public String getFullPath() {
//			return fullPath;
//		}
//
//		public void setFullPath(String fullPath) {
//			this.fullPath = fullPath;
//		}
//
//		public String getClassName() {
//			return className;
//		}
//
//		public void setClassName(String className) {
//			this.className = className;
//		}
//
//		public String getPackageName() {
//			return packageName;
//		}
//
//		public void setPackageName(String packageName) {
//			this.packageName = packageName;
//		}
//
//		public long getLineNumber() {
//			return LineNumber;
//		}
//
//		public void setLineNumber(long lineNumber) {
//			LineNumber = lineNumber;
//		}
//
//		public String getJavaDoc() {
//			return JavaDoc;
//		}
//
//		public void setJavaDoc(String javaDoc) {
//			JavaDoc = javaDoc;
//		}
//		
//		
//	}
	
	
	
}
