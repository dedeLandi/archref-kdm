package br.ufscar.archref_kdm.ui.wizardsPage;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import br.ufscar.archref_kdm.core.readDrifts.ReadDriftsAlgorithm;
import br.ufscar.archref_kdm.ui.wizards.ArchitecturalRefactoringWizard;

public class Page02SelectFileWithDrift extends WizardPage {

	private Combo cbAlgorithm;
	private Text tPathFileDrifts;
	private Text tPathFilePlanned;
	private Text tPathFileActual;
	private Combo cbTypeActualArchitecture;
	private static final String ALREAD_MAPPED = "Architectural Elements Alread Mapped";
	private static final String ORIGINAL_MAP = "Original (From Discover)";

	/**
	 * Create the wizard.
	 */
	public Page02SelectFileWithDrift() {
		super("page02");
		setTitle("Architectural Refactoring Wizard");
		setDescription("Select the algorithm and the files containing the architectural drifts, planned architecture and actual architecture.");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout(3, false));

		Label lblSelectionOfThe = new Label(container, SWT.NONE);
		lblSelectionOfThe.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 3, 1));
		lblSelectionOfThe.setText("Selection of the algorithm to process the file containing the architectural drifts. ");

		Label lAlgorithmForExtractDrift = new Label(container, SWT.NONE);
		lAlgorithmForExtractDrift.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lAlgorithmForExtractDrift.setText("Algorithm for extract drifts");

		cbAlgorithm = new Combo(container, SWT.READ_ONLY);
		cbAlgorithm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		cbAlgorithm.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				getWizard().getContainer().updateButtons();
			}

		});

		Label lFileDrifts = new Label(container, SWT.NONE);
		lFileDrifts.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lFileDrifts.setText("File containing the drifts");

		tPathFileDrifts = new Text(container, SWT.BORDER);
		tPathFileDrifts.setText("C:\\Java\\workspaceMestradoJeeMars64\\archref-kdm\\br.ufscar.ARCHREF-KDM.ui\\tests\\br\\ufscar\\archref_kdm\\ui\\tests\\labSystemKDMComdesvios.xmi");
		tPathFileDrifts.setEditable(false);
		tPathFileDrifts.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button bSearchDrifts = new Button(container, SWT.NONE);
		bSearchDrifts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				chooseFileDrifts();
			}
		});
		bSearchDrifts.setText("Search");

		Label lFilePlannedArchitecture = new Label(container, SWT.NONE);
		lFilePlannedArchitecture.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lFilePlannedArchitecture.setText("File containing the \r\nplanned architecture");

		tPathFilePlanned = new Text(container, SWT.BORDER);
		tPathFilePlanned.setText("C:\\Java\\workspaceMestradoMars64\\archref-kdm\\br.ufscar.ARCHREF-KDM.ui\\tests\\br\\ufscar\\archref_kdm\\ui\\tests\\archPlan.xmi");
		tPathFilePlanned.setEditable(false);
		tPathFilePlanned.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button bSearchPlanned = new Button(container, SWT.NONE);
		bSearchPlanned.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				chooseFilePlanned();
			}
		});
		bSearchPlanned.setText("Search");
		
		Label lblTypeOfActual = new Label(container, SWT.NONE);
		lblTypeOfActual.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTypeOfActual.setText("Type of Actual Architecture");
		
		cbTypeActualArchitecture = new Combo(container, SWT.READ_ONLY);
		cbTypeActualArchitecture.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		cbTypeActualArchitecture.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				getWizard().getContainer().updateButtons();
			}

		});

		Label lFileActualArchitecture = new Label(container, SWT.NONE);
		lFileActualArchitecture.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lFileActualArchitecture.setText("File containing the \r\nactual architecture");

		tPathFileActual = new Text(container, SWT.BORDER);
		tPathFileActual.setText("C:\\Java\\workspaceMestradoMars64\\archref-kdm\\br.ufscar.ARCHREF-KDM.ui\\tests\\br\\ufscar\\archref_kdm\\ui\\tests\\SystemExampleMVC-SimplesComDesvios_kdm.xmi");
		tPathFileActual.setEditable(false);
		tPathFileActual.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button bSearchActual = new Button(container, SWT.NONE);
		bSearchActual.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				chooseFileActual();
			}
		});
		bSearchActual.setText("Search");

		fillCbAlgorithm();
		
		fillCbType();
	}

	protected void chooseFileDrifts() {

		if(cbAlgorithm.getSelectionIndex() == -1){
			setErrorMessage("Select one type of algorithm to continue.");
		}else{	
			setErrorMessage(null); // clear error message. 

			FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
			fd.setText("Open");
			fd.setFilterPath("C:/");
			String[] filterExt = ReadDriftsAlgorithm.getEnumObject(cbAlgorithm.getText()).getExtensions();
			fd.setFilterExtensions(filterExt);
			String selected = fd.open();
			if(selected == null){
				tPathFileDrifts.setText("");
			}else{
				System.out.println("Path of the XMI drifted file:" + selected);
				tPathFileDrifts.setText(selected);
			}
		}
		getWizard().getContainer().updateButtons();
	}

	protected void chooseFilePlanned() {

		setErrorMessage(null); // clear error message. 

		FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
		fd.setText("Open");
		fd.setFilterPath("C:/");
		String[] filterExt = new String[]{"*.xmi"};
		fd.setFilterExtensions(filterExt);
		String selected = fd.open();
		if(selected == null){
			tPathFilePlanned.setText("");
		}else{
			System.out.println("Path of the XMI planned file:" + selected);
			tPathFilePlanned.setText(selected);
		}
		getWizard().getContainer().updateButtons();
	}

	protected void chooseFileActual() {

		setErrorMessage(null); // clear error message. 

		FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
		fd.setText("Open");
		fd.setFilterPath("C:/");
		String[] filterExt = new String[]{"*.xmi"};
		fd.setFilterExtensions(filterExt);
		String selected = fd.open();
		if(selected == null){
			tPathFileActual.setText("");
		}else{
			System.out.println("Path of the XMI actual file:" + selected);
			tPathFileActual.setText(selected);
		}
		getWizard().getContainer().updateButtons();
	}

	private void fillCbAlgorithm() {
		cbAlgorithm.removeAll();
		for (ReadDriftsAlgorithm algo : ReadDriftsAlgorithm.values()) {
			cbAlgorithm.add(algo.getDescription());
		}
	}
	
	private void fillCbType() {
		cbTypeActualArchitecture.removeAll();
		cbTypeActualArchitecture.add(ALREAD_MAPPED);
		cbTypeActualArchitecture.add(ORIGINAL_MAP);
	}

	private boolean validateCbAlgorithm() {
		if(cbAlgorithm.getSelectionIndex() == -1) {  
			setErrorMessage("Select one type of algorithm to continue.");
			return false;
		}else{
			setErrorMessage(null); // clear error message. 
			return true;
		}
	}
	
	private boolean validateCbType() {
		if(cbTypeActualArchitecture.getSelectionIndex() == -1) {  
			setErrorMessage("Select one type of actual architecture to continue.");
			return false;
		}else{
			setErrorMessage(null); // clear error message. 
			return true;
		}
	}

	private boolean validateTPathFileDrifts() {
		if("".equalsIgnoreCase(tPathFileDrifts.getText())) { 
			setErrorMessage("Select one file to continue. (Drifts)");
			return false;
		}else{
			setErrorMessage(null); // clear error message. 
			return true;
		}
	}
	private boolean validateTPathFilePlanned() {
		if("".equalsIgnoreCase(tPathFilePlanned.getText())) { 
			setErrorMessage("Select one file to continue. (Planned)");
			return false;
		}else{
			setErrorMessage(null); // clear error message. 
			return true;
		}
	}
	private boolean validateTPathFileActual() {
		if("".equalsIgnoreCase(tPathFileActual.getText())) { 
			setErrorMessage("Select one file to continue. (Actual)");
			return false;
		}else{
			setErrorMessage(null); // clear error message. 
			return true;
		}
	}
	
	

	@Override
	public void performHelp(){
		//TODO Desenvolver o help dessa pagina
	    Shell shell = new Shell(getShell());
	    shell.setText("My Custom Help !!");
	    shell.setLayout(new GridLayout());
	    shell.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

	    Browser browser = new Browser(shell, SWT.NONE);
	    browser.setUrl("http://advanse.dc.ufscar.br/index.php/tools");
	    browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

	    shell.open();
	}
	
	@Override
	public boolean canFlipToNextPage() {
		return validateCbAlgorithm() && validateTPathFileDrifts() && validateTPathFilePlanned() && validateTPathFileActual() && validateCbType();
	}

	@Override
	public IWizardPage getNextPage() {
		executeWizardPageFinalAction();
		if(isMapped()){
			((Page03SelectDrift) getWizard().getPage("page03")).fillTDrifts();
			return getWizard().getPage("page03");
		}else{
			((Page02MapArchitecture) getWizard().getPage("page02_1")).fillPlannedArchitecture();
			((Page02MapArchitecture) getWizard().getPage("page02_1")).fillActualArchitecture();
			return getWizard().getPage("page02_1");
		}
	}

	private void executeWizardPageFinalAction() {
		ArchitecturalRefactoringWizard architecturalRefactoringWizard = (ArchitecturalRefactoringWizard) this.getWizard();
		
		architecturalRefactoringWizard.setPathActualArchitecture(this.tPathFileActual.getText());
		architecturalRefactoringWizard.setPathDriftsFile(this.tPathFileDrifts.getText());
		architecturalRefactoringWizard.setPathPlannedArchitecture(this.tPathFilePlanned.getText());
		
		architecturalRefactoringWizard.setTypeAlgorithmDrifts(ReadDriftsAlgorithm.getEnumObject(cbAlgorithm.getText()));
		
		architecturalRefactoringWizard.readSements();
	}

	/**
	 * @author Landi
	 * @return
	 */
	private boolean isMapped() {
		return cbTypeActualArchitecture.getText().equalsIgnoreCase(ALREAD_MAPPED);
	}

}
