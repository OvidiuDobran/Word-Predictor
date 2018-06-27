package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;

abstract class MyComposite extends Composite {

	public MyComposite(Composite parent) {
		super(parent, SWT.NONE);
		FormLayout formLayout = new FormLayout();
		setLayout(formLayout);
		generateContent();
	}

	public abstract void generateContent();
}
