package br.ufscar.archref_kdm.core.mappingArchitecture;

import org.eclipse.gmt.modisco.omg.kdm.core.KDMEntity;
import org.eclipse.gmt.modisco.omg.kdm.structure.AbstractStructureElement;
import org.eclipse.gmt.modisco.omg.kdm.structure.StructureModel;

public class MapArchitecture {

	private StructureModel structureToMap;

	public MapArchitecture() {
		super();
	}
	
	public MapArchitecture(StructureModel completeMap) {
		this.structureToMap = completeMap;
	}

	public StructureModel mapCompleteArchitecture() {
		// TODO Auto-generated method stub
		return this.structureToMap;
	}

	public void mapInitialArchitecture(AbstractStructureElement abstractStructureElement, KDMEntity codeElement) {
		abstractStructureElement.getImplementation().add(codeElement);
	}

	public StructureModel cleanAggregateds() {
		for (AbstractStructureElement parentElement : this.structureToMap.getStructureElement()) {
			cleanAggregateds(parentElement);
		}
		return this.structureToMap;
	}

	private void cleanAggregateds(AbstractStructureElement parentElement) {
		parentElement.getInAggregated().clear();
		parentElement.getOutAggregated().clear();
		parentElement.getAggregated().clear();
		for (AbstractStructureElement childElement : parentElement.getStructureElement()) {
			cleanAggregateds(childElement);
		}
	}


}
