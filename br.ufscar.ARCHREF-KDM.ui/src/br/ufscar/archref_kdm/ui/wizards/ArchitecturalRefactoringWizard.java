package br.ufscar.archref_kdm.ui.wizards;

import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import br.ufscar.archref_kdm.core.readDrifts.ReadDriftsAlgorithm;
import br.ufscar.archref_kdm.ui.wizardsPage.Page01Introduction;
import br.ufscar.archref_kdm.ui.wizardsPage.Page02MapArchitecture;
import br.ufscar.archref_kdm.ui.wizardsPage.Page02SelectFileWithDrift;
import br.ufscar.archref_kdm.ui.wizardsPage.Page03SelectDrift;
import br.ufscar.archref_kdm.ui.wizardsPage.Page04ProcessAnalisis;
import br.ufscar.archref_kdm.ui.wizardsPage.Page05FillRefactoringCatalog;
import br.ufscar.archref_kdm.ui.wizardsPage.Page05ProcessFillRefactoringCatalog;
import br.ufscar.archref_kdm.ui.wizardsPage.Page06SelectRefactoringToDo;
import br.ufscar.archref_kdm.ui.wizardsPage.Page07ProcessEffectRefactor;
import br.ufscar.archref_kdm.ui.wizardsPage.Page08SaveAndFinish;
import br.ufscar.kdm_manager.core.exceptions.KDMFileException;
import br.ufscar.kdm_manager.core.loads.factory.KDMFileReaderFactory;

public class ArchitecturalRefactoringWizard extends Wizard {
	
	private Page01Introduction page1 = new Page01Introduction();
	private Page02SelectFileWithDrift page2 = new Page02SelectFileWithDrift();
	private Page03SelectDrift page3 = new Page03SelectDrift();
	private Page04ProcessAnalisis page4 = new Page04ProcessAnalisis();
	private Page06SelectRefactoringToDo page6 = new Page06SelectRefactoringToDo();
	private Page07ProcessEffectRefactor page7 = new Page07ProcessEffectRefactor();
	private Page08SaveAndFinish page8 = new Page08SaveAndFinish();
	
	private Page05ProcessFillRefactoringCatalog page5_1 = new Page05ProcessFillRefactoringCatalog();
	private Page05FillRefactoringCatalog page5_2 = new Page05FillRefactoringCatalog();

	private Page02MapArchitecture page2_1 = new Page02MapArchitecture();
	
	private Segment segmentPlannedArchitecture = null;
	private Segment segmentActualArchitecture = null;
	
	private ReadDriftsAlgorithm typeAlgorithmDrifts = null;
	
	private String pathPlannedArchitecture = null;
	private String pathActualArchitecture = null;
	private String pathDriftsFile = null;
	
	private void cleanObjects() {
		segmentPlannedArchitecture = null;
		segmentActualArchitecture = null;
		
		setTypeAlgorithmDrifts(null);
		
		setPathPlannedArchitecture(null);
		setPathActualArchitecture(null);
		setPathDriftsFile(null);
	}

	public ArchitecturalRefactoringWizard() {
		setWindowTitle("Architectural Refactoring Wizard");
		setNeedsProgressMonitor(true);
		cleanObjects();
	}


	@Override
	public void addPages() {
		addPage(page1);
		addPage(page2);
		addPage(page2_1);
		addPage(page3);
		addPage(page4);
		addPage(page5_1);
		addPage(page5_2);
		addPage(page6);
		addPage(page7);
		addPage(page8);
	}

	@Override
	public boolean canFinish() {
		 if(getContainer().getCurrentPage() == page8){
			 return true;
		 }else{
			 return false;
		 }
	}
	
	@Override
	public boolean performFinish() {
		//usar o mensage dialog
		MessageDialog.open(MessageDialog.INFORMATION, getShell(), "Wizzard finished", "Wizzard finished", MessageDialog.INFORMATION);
		return true;
	}

	public boolean performCancel() {
		boolean ans = MessageDialog.openConfirm(getShell(), "Confirmation", "Are you sure to cancel the wizard?");
		if(ans)
			return true;
		else
			return false;
	}

	/**
	 * @return the setPlannedArchitecture
	 */
	public Segment getSegmentPlannedArchitecture() {
		return segmentPlannedArchitecture;
	}

	/**
	 * @param setPlannedArchitecture the setPlannedArchitecture to set
	 */
	public void setSegmentPlannedArchitecture(Segment setPlannedArchitecture) {
		this.segmentPlannedArchitecture = setPlannedArchitecture;
	}

	/**
	 * @return the setActualArchitecture
	 */
	public Segment getSegmentActualArchitecture() {
		return segmentActualArchitecture;
	}

	/**
	 * @param setActualArchitecture the setActualArchitecture to set
	 */
	public void setSegmentActualArchitecture(Segment setActualArchitecture) {
		this.segmentActualArchitecture = setActualArchitecture;
	}

	public ReadDriftsAlgorithm getTypeAlgorithmDrifts() {
		return typeAlgorithmDrifts;
	}

	public void setTypeAlgorithmDrifts(ReadDriftsAlgorithm typeAlgorithmDrifts) {
		this.typeAlgorithmDrifts = typeAlgorithmDrifts;
	}

	public String getPathPlannedArchitecture() {
		return pathPlannedArchitecture;
	}

	public void setPathPlannedArchitecture(String pathPlannedArchitecture) {
		this.pathPlannedArchitecture = pathPlannedArchitecture;
	}

	public String getPathActualArchitecture() {
		return pathActualArchitecture;
	}

	public void setPathActualArchitecture(String pathActualArchitecture) {
		this.pathActualArchitecture = pathActualArchitecture;
	}

	public String getPathDriftsFile() {
		return pathDriftsFile;
	}

	public void setPathDriftsFile(String pathDriftsFile) {
		this.pathDriftsFile = pathDriftsFile;
	}

	public void readSements() {
		try {
			this.segmentActualArchitecture = KDMFileReaderFactory.eINSTANCE.createKDMFileReaderToSegment().readFromPath(this.pathActualArchitecture);
			this.segmentPlannedArchitecture = KDMFileReaderFactory.eINSTANCE.createKDMFileReaderToSegment().readFromPath(this.pathPlannedArchitecture);
		} catch (KDMFileException e) {
			e.printStackTrace();
		}
	}

}
