package br.ufscar.archref_kdm.ui.wizardsPage;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class Page08SaveAndFinish extends WizardPage {

	private boolean backButtonEnabled = false;
	
	/**
	 * Create the wizard.
	 */
	public Page08SaveAndFinish() {
		super("page08");
		setTitle("Architectural Refactoring Wizard");
		setDescription("Process complete.");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 554, 314);
		lblNewLabel.setText("Process to solve the architectural drift X executed with sucess.\r\n\r\nIf you want do efective the operetion press finish if you don't press cancel.");
	}
	
	public void setBackButtonEnabled(boolean enabled) {
        backButtonEnabled = enabled;
        getContainer().updateButtons();
    }

    @Override
    public IWizardPage getPreviousPage() {
        if (!backButtonEnabled) {
            return null;
        }
        return super.getPreviousPage();
    }
}
