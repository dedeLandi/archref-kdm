package br.ufscar.REFARCH_KDM.wizardsPage;

import java.util.List;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import br.ufscar.archref_kdm.core.readDrifts.ArchitecturalDrift;
import br.ufscar.archref_kdm.core.readDrifts.ReadDriftsAlgorithm;
import br.ufscar.archref_kdm.core.readDrifts.ReadDriftsFromKDMFile;

public class Page03SelectDrift extends WizardPage {
	private Table tDrifts;

	/**
	 * Create the wizard.
	 */
	public Page03SelectDrift() {
		super("page03");
		setTitle("Architectural Refactoring Wizard");
		setDescription("Select the drift that you want to solve.");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));

		ScrolledComposite scDrifts = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scDrifts.setExpandHorizontal(true);
		scDrifts.setExpandVertical(true);

		tDrifts = new Table(scDrifts, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		tDrifts.setHeaderVisible(true);
		tDrifts.setLinesVisible(true);
		tDrifts.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				for (TableItem item : tDrifts.getItems()) {
					item.setChecked(false);
				}
				
				if(event.detail != SWT.CHECK){
					((TableItem) event.item).setChecked(true);
				}


			}
		});

		TableColumn tcDescription = new TableColumn(tDrifts, SWT.NONE);
		tcDescription.setWidth(260);
		tcDescription.setText("Description");

		scDrifts.setContent(tDrifts);
		scDrifts.setMinSize(tDrifts.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		ScrolledComposite scrolledComposite = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		Label lblProperty = new Label(composite, SWT.NONE);
		lblProperty.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblProperty.setText("Property 1:");
		new Label(composite, SWT.NONE);

		Label lblProperty_1 = new Label(composite, SWT.NONE);
		lblProperty_1.setText("Property 2:");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText("New Label");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setText("New Label");

		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setText("New Label");

		Label lblProperty_n = new Label(composite, SWT.NONE);
		lblProperty_n.setText("Property N:");
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

	}

	public void fillTDrifts() {
		tDrifts.removeAll();
		String KDMFilePath = ((Page02SelectFileWithDrift) getWizard().getPage("page02")).getPathKDMFile();
		ReadDriftsAlgorithm algorithmType = ((Page02SelectFileWithDrift) getWizard().getPage("page02")).getAlgorithmType();
		
		ReadDriftsFromKDMFile readDrifts = new ReadDriftsFromKDMFile(KDMFilePath, algorithmType);
		List<ArchitecturalDrift> drifts = readDrifts.getKDMDriftsRead();
		
		for (ArchitecturalDrift architecturalDrift : drifts) {
			
			TableItem item = new TableItem(tDrifts, SWT.NONE);

			item.setText(new String[]{architecturalDrift.getDescription()});
		}
		tDrifts.setRedraw(true);
	}

	@Override
	public IWizardPage getNextPage() {
		((Page04ProcessAnalisis) super.getNextPage()).setCanFlip(false);
		return super.getNextPage();
	}
}
