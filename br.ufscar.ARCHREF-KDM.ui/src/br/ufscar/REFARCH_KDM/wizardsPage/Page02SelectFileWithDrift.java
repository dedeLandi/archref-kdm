package br.ufscar.REFARCH_KDM.wizardsPage;

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

public class Page02SelectFileWithDrift extends WizardPage {

	private Combo cbAlgorithm;
	private Text tPathFileDrifts;
	private Text tPathFilePlanned;
	private Text tPathFileActual;

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

		Label lFileActualArchitecture = new Label(container, SWT.NONE);
		lFileActualArchitecture.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lFileActualArchitecture.setText("File containing the \r\nactual architecture");

		tPathFileActual = new Text(container, SWT.BORDER);
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

	private boolean validateCbAlgorithm() {
		if(cbAlgorithm.getSelectionIndex() == -1) {  
			setErrorMessage("Select one type of algorithm to continue.");
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
	    browser.setUrl("http://stackoverflow.com/questions/7322489/cant-put-content-behind-swt-wizard-help-button");
	    browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

	    shell.open();
	}
	
	@Override
	public boolean canFlipToNextPage() {
		return validateCbAlgorithm() && validateTPathFileDrifts() && validateTPathFilePlanned() && validateTPathFileActual();
	}

	public String getPathKDMFile() {
		return tPathFileDrifts.getText();
	}

	public ReadDriftsAlgorithm getAlgorithmType(){
		return ReadDriftsAlgorithm.getEnumObject(cbAlgorithm.getText());
	}

	@Override
	public IWizardPage getNextPage() {
		((Page03SelectDrift) super.getNextPage()).fillTDrifts();
		return super.getNextPage();
	}

}
