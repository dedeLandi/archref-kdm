package br.ufscar.archref_kdm.ui.wizardsPage;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class Page01Introduction extends WizardPage {

	/**
	 * Create the wizard.
	 */
	public Page01Introduction() {
		super("page01");
		setPageComplete(false);
		setMessage("");
		
		setTitle("Architectural Refactoring Wizard");
		setDescription("");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		Label lDescritption = new Label(container, SWT.WRAP | SWT.HORIZONTAL);
		lDescritption.setBounds(10, 10, 554, 314);
		lDescritption.setText("Hello developer!\r\n\r\nThis wizard aims to facilitate your architectural refactoring based on architectural drifts previously discovered.\r\n\r\nLets go to start the wizard!");
	}
	
	@Override
	public boolean canFlipToNextPage() {
		return true;
	}
	
}
