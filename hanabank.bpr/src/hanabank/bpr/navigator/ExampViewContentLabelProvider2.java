package hanabank.bpr.navigator;

import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.IMemberValuePair;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.JavaElement;
import org.eclipse.jdt.internal.core.SourceMethod;
import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.IToolTipProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.internal.navigator.NavigatorContentService;
import org.eclipse.ui.internal.navigator.NavigatorContentServiceLabelProvider;

import hanabank.bpr.views.ExampleView;

public abstract class ExampViewContentLabelProvider2 extends ColumnLabelProvider implements IToolTipProvider {

	@Override
	public void update(ViewerCell cell) {
		// TODO Auto-generated method stub
		super.update(cell);
	}

	@Override
	public Font getFont(Object element) {
		// TODO Auto-generated method stub
		return super.getFont(element);
	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return super.getBackground(element);
	}

	@Override
	public Color getForeground(Object element) {
		// TODO Auto-generated method stub
		return super.getForeground(element);
	}

	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		return super.getImage(element);
	}

	@Override
	public String getText(Object element) {
		// TODO Auto-generated method stub
		return super.getText(element);
	}

	@Override
	public String getToolTipText(Object element) {
		// TODO Auto-generated method stub
		return super.getToolTipText(element);
	}

	@Override
	public boolean useNativeToolTip(Object object) {
		// TODO Auto-generated method stub
		return super.useNativeToolTip(object);
	}

	
}
