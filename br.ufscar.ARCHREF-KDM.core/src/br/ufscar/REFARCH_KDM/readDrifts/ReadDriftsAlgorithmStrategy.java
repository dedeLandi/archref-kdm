package br.ufscar.REFARCH_KDM.readDrifts;

import java.util.List;

public interface ReadDriftsAlgorithmStrategy {

	List<ArchitecturalDrift> readDrifts (Object KDMPath);
	
}
