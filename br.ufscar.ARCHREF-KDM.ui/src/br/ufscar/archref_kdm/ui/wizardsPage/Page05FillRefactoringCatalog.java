package br.ufscar.archref_kdm.ui.wizardsPage;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class Page05FillRefactoringCatalog extends WizardPage {

	/**
	 * Create the wizard.
	 */
	public Page05FillRefactoringCatalog() {
		super("page05_2");
		setTitle("Architectural Refactoring Wizard");
		setDescription("Validate recommendation generated.");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		Label label = new Label(container, SWT.NONE);
		label.setBounds(180, 155, 55, 15);
		label.setText("????????????");
	}
}
