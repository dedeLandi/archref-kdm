package br.ufscar.archref_kdm.ui.wizardsPage;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufscar.archref_kdm.ui.wizards.ArchitecturalRefactoringWizard;

public class Page02MapArchitecture extends WizardPage {

	private List listArchitecturalElements;
	private List listCodeElements;
	private List listMappedElements;

	/**
	 * Create the wizard.
	 */
	public Page02MapArchitecture() {
		super("page02_1");
		setTitle("Architectural Refactoring Wizard");
		setDescription("Map the actual architecture with the planned architecture.");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(null);
		
		Label lblArchitecturalElements = new Label(container, SWT.NONE);
		lblArchitecturalElements.setAlignment(SWT.CENTER);
		lblArchitecturalElements.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblArchitecturalElements.setBounds(10, 5, 270, 25);
		lblArchitecturalElements.setText("Architectural Elements");
		
		Label lblCodeElements = new Label(container, SWT.NONE);
		lblCodeElements.setAlignment(SWT.CENTER);
		lblCodeElements.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblCodeElements.setBounds(290, 5, 274, 25);
		lblCodeElements.setText("Code Elements");
		
		ListViewer listViewer = new ListViewer(container, SWT.BORDER | SWT.V_SCROLL);
		listArchitecturalElements = listViewer.getList();
		listArchitecturalElements.setBounds(10, 30, 270, 100);
		
		ListViewer listViewer_1 = new ListViewer(container, SWT.BORDER | SWT.V_SCROLL);
		listCodeElements = listViewer_1.getList();
		listCodeElements.setBounds(290, 30, 274, 100);
		
		ListViewer listViewer_2 = new ListViewer(container, SWT.BORDER | SWT.V_SCROLL);
		listMappedElements = listViewer_2.getList();
		listMappedElements.setBounds(10, 198, 554, 100);
		
		Label lblMappedElements = new Label(container, SWT.NONE);
		lblMappedElements.setAlignment(SWT.CENTER);
		lblMappedElements.setText("Mapped Elements");
		lblMappedElements.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblMappedElements.setBounds(10, 167, 554, 25);
		
		Button bMap = new Button(container, SWT.NONE);
		bMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				mapSelectedElements();
			}
		});
		bMap.setBounds(180, 136, 100, 25);
		bMap.setText("Map");
		
		Button bRemoveMap = new Button(container, SWT.NONE);
		bRemoveMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				removeSelectedMap();
			}
		});
		bRemoveMap.setBounds(290, 136, 100, 25);
		bRemoveMap.setText("Remove Map");
		
		Button bInitialMap = new Button(container, SWT.NONE);
		bInitialMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				generateInitialMap();
			}

		});
		bInitialMap.setBounds(278, 304, 140, 25);
		bInitialMap.setText("Generate Initial Map");
		
		Button bCompleteMap = new Button(container, SWT.NONE);
		bCompleteMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				generateCompleteMap();
			}
		});
		bCompleteMap.setBounds(424, 304, 140, 25);
		bCompleteMap.setText("Generate Complete Map");

	}

	@Override
	public void performHelp(){
		//TODO Desenvolver o help dessa pagina
	    Shell shell = new Shell(getShell());
	    shell.setText("My Custom Help !!");
	    shell.setLayout(new GridLayout());
	    shell.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

	    Browser browser = new Browser(shell, SWT.NONE);
	    browser.setUrl("http://stackoverflow.com/questions/7322489/cant-put-content-behind-swt-wizard-help-button");
	    browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

	    shell.open();
	}
	
	/**
	 * @author Landi
	 */
	protected void removeSelectedMap() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @author Landi
	 */
	protected void mapSelectedElements() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @author Landi
	 */
	protected void generateCompleteMap() {
		if(validateMapRealized()){
			
		}
	}
	
	/**
	 * 
	 * @author Landi
	 */
	private void generateInitialMap() {
		if(validateMapRealized()){
			
		}
	}
	
	/**
	 * @author Landi
	 * @return
	 */
	private boolean validateMapRealized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canFlipToNextPage() {
		return validateCanFlip();
	}

	/**
	 * @author Landi
	 * @return
	 */
	private boolean validateCanFlip() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IWizardPage getNextPage() {
		((Page03SelectDrift) super.getNextPage()).fillTDrifts();
		return super.getNextPage();
	}

	/**
	 * @author Landi
	 */
	public void fillPlannedArchitecture() {
		
	}

	/**
	 * @author Landi
	 */
	public void fillActualArchitecture() {
		// TODO Auto-generated method stub
		
	}
}
