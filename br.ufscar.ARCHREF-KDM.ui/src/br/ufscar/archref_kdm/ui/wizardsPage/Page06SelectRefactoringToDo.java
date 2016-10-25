package br.ufscar.archref_kdm.ui.wizardsPage;

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

public class Page06SelectRefactoringToDo extends WizardPage {
	private Table tRefactorings;
	private Table tRecommendation;

	/**
	 * Create the wizard.
	 */
	public Page06SelectRefactoringToDo() {
		super("page06");
		setTitle("Architectural Refactoring Wizard");
		setDescription("Select the recommendation that you want to apply.");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		ScrolledComposite scRecommendation = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scRecommendation.setExpandHorizontal(true);
		scRecommendation.setExpandVertical(true);
		
		tRecommendation = new Table(scRecommendation, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		tRecommendation.setHeaderVisible(true);
		tRecommendation.setLinesVisible(true);
		tRecommendation.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				for (TableItem item : tRecommendation.getItems()) {
					item.setChecked(false);
				}
				
				if(event.detail != SWT.CHECK){
					((TableItem) event.item).setChecked(true);
				}


			}
		});
		
		TableColumn tcDescription = new TableColumn(tRecommendation, SWT.NONE);
		tcDescription.setWidth(260);
		tcDescription.setText("Description");
		scRecommendation.setContent(tRecommendation);
		scRecommendation.setMinSize(tRecommendation.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		ScrolledComposite scProperty = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scProperty.setExpandHorizontal(true);
		scProperty.setExpandVertical(true);
		
		Composite cProperty = new Composite(scProperty, SWT.NONE);
		cProperty.setLayout(new GridLayout(1, false));
		
		Label lblProperty = new Label(cProperty, SWT.NONE);
		lblProperty.setText("Property 1:");
		new Label(cProperty, SWT.NONE);
		
		Label lblProperty_1 = new Label(cProperty, SWT.NONE);
		lblProperty_1.setText("Property 2:");
		new Label(cProperty, SWT.NONE);
		new Label(cProperty, SWT.NONE);
		
		ScrolledComposite scRefactorings = new ScrolledComposite(cProperty, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gd_scRefactorings = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_scRefactorings.widthHint = 233;
		scRefactorings.setLayoutData(gd_scRefactorings);
		scRefactorings.setExpandHorizontal(true);
		scRefactorings.setExpandVertical(true);
		
		tRefactorings = new Table(scRefactorings, SWT.BORDER | SWT.FULL_SELECTION);
		tRefactorings.setHeaderVisible(true);
		tRefactorings.setLinesVisible(true);
		
		TableColumn tcOrder = new TableColumn(tRefactorings, SWT.NONE);
		tcOrder.setWidth(83);
		tcOrder.setText("Order");
		
		TableColumn tcRefactoring = new TableColumn(tRefactorings, SWT.NONE);
		tcRefactoring.setWidth(141);
		tcRefactoring.setText("Refactoring Used");
		
		scRefactorings.setContent(tRefactorings);
		scRefactorings.setMinSize(tRefactorings.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		new Label(cProperty, SWT.NONE);
		new Label(cProperty, SWT.NONE);
		new Label(cProperty, SWT.NONE);
		new Label(cProperty, SWT.NONE);
		new Label(cProperty, SWT.NONE);
		
		Label lblNewLabel = new Label(cProperty, SWT.NONE);
		lblNewLabel.setText("New Label");
		new Label(cProperty, SWT.NONE);
		new Label(cProperty, SWT.NONE);
		
		Label lblNewLabel_1 = new Label(cProperty, SWT.NONE);
		lblNewLabel_1.setText("New Label");
		
		Label lblNewLabel_2 = new Label(cProperty, SWT.NONE);
		lblNewLabel_2.setText("New Label");
		
		Label lblProperty_n = new Label(cProperty, SWT.NONE);
		lblProperty_n.setText("Property N:");
		scProperty.setContent(cProperty);
		scProperty.setMinSize(cProperty.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		fillTRecommendation();
	}

	private void fillTRecommendation() {
		for (int i = 0; i < 10; i++) {
			new TableItem(tRecommendation, SWT.NONE).setText("Recommendation " + (i+1));
		}
	}
	
	@Override
	public IWizardPage getNextPage() {
		((Page07ProcessEffectRefactor) super.getNextPage()).setCanFlip(false);
		return super.getNextPage();
	}
}
