package br.ufscar.archref_kdm.core.readDrifts;

import java.util.List;

import br.ufscar.kdm_manager.core.exceptions.KDMFileException;
import br.ufscar.kdm_manager.core.loads.factory.KDMFileReaderFactory;

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

		Object KDMFile;
		try {
			KDMFile = KDMFileReaderFactory.eINSTANCE.createKDMFileReaderToSegment().readFromPath(KDMPath);
			return algorithmType.readDrifts(KDMFile);
		} catch (KDMFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

		return null;		


	}

}
