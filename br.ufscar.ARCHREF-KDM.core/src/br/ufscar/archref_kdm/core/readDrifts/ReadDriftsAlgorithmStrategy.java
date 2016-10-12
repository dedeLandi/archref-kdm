package br.ufscar.archref_kdm.core.readDrifts;

import java.util.List;

public interface ReadDriftsAlgorithmStrategy {

	List<ArchitecturalDrift> readDrifts (Object KDMPath);
	
}
