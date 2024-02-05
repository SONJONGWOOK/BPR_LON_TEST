package hanabank.bpr.navigator;

import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.internal.navigator.NavigatorContentService;
import org.eclipse.ui.internal.navigator.NavigatorContentServiceLabelProvider;

public class ExampViewContentLabelProvider extends NavigatorContentServiceLabelProvider implements ILabelProvider, IStyledLabelProvider  , IBaseLabelProvider{

	public ExampViewContentLabelProvider(NavigatorContentService aContentService) {
		super(aContentService);
	}

	@Override
	public String getText(Object anElement) {
		// TODO Auto-generated method stub
		return super.getText(anElement);
	}

	@Override
	public String getToolTipText(Object anElement) {
		// TODO Auto-generated method stub
		return super.getToolTipText(anElement);
	}
	
	

}
