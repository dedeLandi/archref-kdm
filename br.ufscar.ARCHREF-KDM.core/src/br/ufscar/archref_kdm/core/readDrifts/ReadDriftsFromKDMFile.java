package br.ufscar.archref_kdm.core.readDrifts;

import java.util.List;

import br.ufscar.KDM_MANAGEMENT.load.KDMFileReader;

public class ReadDriftsFromKDMFile {

	private ReadDriftsAlgorithm algorithmType = null;
	private String KDMPath = "";

	public ReadDriftsFromKDMFile(String KDMPath) {
		this.algorithmType = ReadDriftsAlgorithm.ALGORITHM_ARCH_KDM_FROM_SEGMENT;
	}

	public ReadDriftsFromKDMFile(String KDMPath, ReadDriftsAlgorithm algorithmType) {
		this.KDMPath = KDMPath;
		this.algorithmType = algorithmType;
	}

	public List<ArchitecturalDrift> getKDMDriftsRead() {
		
		Object KDMFile = new KDMFileReader(KDMPath, KDMFileReader.READ_KDM_TO_SEGMENT).getKdmRead();
		
		return algorithmType.readDrifts(KDMFile);
		
	}
	
}
