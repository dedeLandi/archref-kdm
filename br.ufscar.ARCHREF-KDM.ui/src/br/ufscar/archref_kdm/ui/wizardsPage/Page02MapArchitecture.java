package br.ufscar.archref_kdm.ui.wizardsPage;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.EnumeratedType;
import org.eclipse.gmt.modisco.omg.kdm.code.InterfaceUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.gmt.modisco.omg.kdm.structure.AbstractStructureElement;
import org.eclipse.gmt.modisco.omg.kdm.structure.StructureModel;
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
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufscar.archref_kdm.ui.enums.IconsType;
import br.ufscar.archref_kdm.ui.wizards.ArchitecturalRefactoringWizard;
import br.ufscar.kdm_manager.core.readers.modelReader.factory.KDMModelReaderJavaFactory;
import br.ufscar.kdm_manager.core.recovers.recoverHierarchy.factory.RecoverHierarchyJavaFactory;

public class Page02MapArchitecture extends WizardPage {

	private Tree treeArchitecturalElements;
	private Tree treeCodeElements;
	private StructureModel plannedArchitecture;
	private CodeModel actualArchitecture;
	private java.util.List<String> codeElementsPath;
	private Tree treeElementsMapped;

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
		container.setLayout(new GridLayout(2, true));
		
		Label lArchitecturalElements = new Label(container, SWT.NONE);
		lArchitecturalElements.setFont(SWTResourceManager.getFont("Segoe UI", 15, SWT.NORMAL));
		lArchitecturalElements.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lArchitecturalElements.setText("Architectural Elements");
		
		Label lCodeElements = new Label(container, SWT.NONE);
		lCodeElements.setFont(SWTResourceManager.getFont("Segoe UI", 15, SWT.NORMAL));
		lCodeElements.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lCodeElements.setText("Code Elements");
		
		treeArchitecturalElements = new Tree(container, SWT.BORDER);
		GridData gd_treeArchitecturalElements = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_treeArchitecturalElements.minimumHeight = 100;
		treeArchitecturalElements.setLayoutData(gd_treeArchitecturalElements);
		
		treeCodeElements = new Tree(container, SWT.BORDER);
		GridData gd_treeCodeElements = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_treeCodeElements.minimumHeight = 100;
		treeCodeElements.setLayoutData(gd_treeCodeElements);
		
		Button bMap = new Button(container, SWT.NONE);
		bMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				mapElements();
			}
		});
		GridData gd_bMap = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_bMap.widthHint = 150;
		bMap.setLayoutData(gd_bMap);
		bMap.setText("Map Element");
		
		Button bRemoveMap = new Button(container, SWT.NONE);
		GridData gd_bRemoveMap = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_bRemoveMap.widthHint = 150;
		bRemoveMap.setLayoutData(gd_bRemoveMap);
		bRemoveMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				removeMappedElement();
			}
		});
		bRemoveMap.setText("Remove Mapped Element");
		
		Label lElementsMapped = new Label(container, SWT.NONE);
		lElementsMapped.setFont(SWTResourceManager.getFont("Segoe UI", 15, SWT.NORMAL));
		lElementsMapped.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		lElementsMapped.setText("Elements Alread Mapped");
		
		treeElementsMapped = new Tree(container, SWT.BORDER);
		GridData gd_treeElementsMapped = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_treeElementsMapped.minimumHeight = 90;
		treeElementsMapped.setLayoutData(gd_treeElementsMapped);
		
		Button bSaveSimpleMap = new Button(container, SWT.NONE);
		bSaveSimpleMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				executeInitialMap();
				save("initialMap");
			}
		});
		GridData gd_bSaveSimpleMap = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_bSaveSimpleMap.widthHint = 150;
		bSaveSimpleMap.setLayoutData(gd_bSaveSimpleMap);
		bSaveSimpleMap.setText("Save Simple Map");
		
		Button bSaveCompleteMap = new Button(container, SWT.NONE);
		GridData gd_bSaveCompleteMap = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_bSaveCompleteMap.widthHint = 150;
		bSaveCompleteMap.setLayoutData(gd_bSaveCompleteMap);
		bSaveCompleteMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				executeCompleteMap();
				save("completeMap");
			}
		});
		bSaveCompleteMap.setText("Save Complete Map");
        
	}

	protected void executeCompleteMap() {
		// TODO Auto-generated method stub
		
	}

	protected void executeInitialMap() {
		// TODO Auto-generated method stub
		TreeItem[] selection = treeElementsMapped.getItems();
		for (TreeItem treeItem : selection) {
			System.out.println(treeItem.getText());
			Object data[] = (Object[]) treeItem.getData();
			System.out.println(data[0].getClass());
			System.out.println(data[1].getClass());
		}
	}

	protected void save(String string) {
		// TODO Auto-generated method stub
		
	}

	protected void removeMappedElement() {
		// TODO Auto-generated method stub
		
	}

	protected void mapElements() {
		// TODO Auto-generated method stub
		String text = "";
		Object data[] = new Object[2];
		
		TreeItem[] selection = treeCodeElements.getSelection();
		for (TreeItem treeItem : selection) {
			text = text.concat(treeItem.getText());
			data[0] = treeItem.getData();
		}
		text = text.concat(" was mapped to ");
		TreeItem[] selection2 = treeArchitecturalElements.getSelection();
		for (TreeItem treeItem : selection2) {
			text = text.concat(treeItem.getText());
			data[1] = treeItem.getData();
		}
		TreeItem treeItemParent = new TreeItem(treeElementsMapped, 0);
		treeItemParent.setImage(IconsType.STRUCTURAL_ELEMENT.getImage());
		treeItemParent.setText(text);
		treeItemParent.setData(data);
		
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
		((Page03SelectDrift) getWizard().getPage("page03")).fillTDrifts();
		return getWizard().getPage("page03");
	}

	/**
	 * @author Landi
	 */
	public void fillPlannedArchitecture() {
		Segment segmentPlannedArchitecture = ((ArchitecturalRefactoringWizard)this.getWizard()).getSegmentPlannedArchitecture();
		Map<String, java.util.List<StructureModel>> allStructure = KDMModelReaderJavaFactory.eINSTANCE.createKDMStructureModelReader().getAllFromSegment(segmentPlannedArchitecture);
		plannedArchitecture = null;
		if(allStructure.keySet().size() == 1){
			for (String key : allStructure.keySet()) {
				if(allStructure.get(key).size() == 1){
					plannedArchitecture = allStructure.get(key).get(0); 
				}else{
					//TODO implementar pergunta sobre qual model é a arquitetura planejada e depois fazer o processamento
				}
			}
		}else{
			//TODO implementar pergunta sobre qual model é a arquitetura planejada e depois fazer o processamento
		}
		
		TreeItem treeItemParent = null;
		for (AbstractStructureElement abstractStructureElement : plannedArchitecture.getStructureElement()) {
			
			treeItemParent = new TreeItem(treeArchitecturalElements, 0);
			treeItemParent.setImage(IconsType.STRUCTURAL_ELEMENT.getImage());
			treeItemParent.setText("[" + abstractStructureElement.eClass().getName() + "] " + abstractStructureElement.getName());
			treeItemParent.setData(abstractStructureElement);
			
			this.fillPlannedArchitecture(treeItemParent, abstractStructureElement);
			
		}
		
		
	}

	private void fillPlannedArchitecture(TreeItem treeItemParent, AbstractStructureElement parentElement) {
		TreeItem treeItemChild = null;
		for (AbstractStructureElement childElement : parentElement.getStructureElement()) {
			
			treeItemChild = new TreeItem(treeItemParent, 0);
			treeItemChild.setImage(IconsType.STRUCTURAL_ELEMENT.getImage());
			treeItemChild.setText("[" + childElement.eClass().getName() + "] " + childElement.getName());
			treeItemChild.setData(childElement);
			
			this.fillPlannedArchitecture(treeItemChild, childElement);
		}
		
	}

	/**
	 * @author Landi
	 */
	public void fillActualArchitecture() {
		Segment segmentPlannedArchitecture = ((ArchitecturalRefactoringWizard)this.getWizard()).getSegmentActualArchitecture();
		Map<String, java.util.List<CodeModel>> allCode = KDMModelReaderJavaFactory.eINSTANCE.createKDMCodeModelReader().getAllFromSegment(segmentPlannedArchitecture);
		actualArchitecture = null;
		if(allCode.keySet().size() == 1){
			for (String key : allCode.keySet()) {
				if(allCode.get(key).size() == 1){
					actualArchitecture = allCode.get(key).get(0); 
				}else{
					//TODO implementar pergunta sobre qual model é a arquitetura planejada e depois fazer o processamento
					actualArchitecture = allCode.get(key).get(0);
				}
			}
		}else{
			for (String key : allCode.keySet()) {
				actualArchitecture = allCode.get(key).get(0); 
				break;
			}//TODO implementar pergunta sobre qual model é a arquitetura planejada e depois fazer o processamento
		}
		
		for (AbstractCodeElement abstractCodeElement : actualArchitecture.getCodeElement()) {
			
			TreeItem treeItemParent = new TreeItem(treeCodeElements, 0);
			treeItemParent.setImage(IconsType.getImageByElement(abstractCodeElement));
			treeItemParent.setText("[" + abstractCodeElement.eClass().getName() + "] " + abstractCodeElement.getName());
			treeItemParent.setData(abstractCodeElement);
			
			if(abstractCodeElement instanceof Package){
				this.fillActualArchitecture(treeItemParent, (Package)abstractCodeElement);
			}
			
		}
	}

	private void fillActualArchitecture(TreeItem treeItemParent, Package parentElement) {
		
		for (AbstractCodeElement childElement : parentElement.getCodeElement()) {
			TreeItem treeItemChild = new TreeItem(treeItemParent, 0);
			treeItemChild.setImage(IconsType.getImageByElement(childElement));
			treeItemChild.setText("[" + childElement.eClass().getName() + "] " + childElement.getName());
			treeItemChild.setData(childElement);
			
			if(childElement instanceof Package){
				this.fillActualArchitecture(treeItemChild, (Package) childElement);
			}
			
		}	
	}
}
