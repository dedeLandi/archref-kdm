package br.ufscar.archref_kdm.core.tests;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KdmPackage;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;

public class Test {

	public static void main(String[] args) {

		Segment raizKDMInitialMap = null;

		String KDMModelFullPath = "file:///C:/Java/workspaceMestradoMars64/masterSourceCode/br.ufscar.REFARCH-KDM.core/src/br/ufscar/REFARCH_KDM/tests/initialMap.xmi";
		System.err.println("KDM Path: " + KDMModelFullPath);

		KdmPackage.eINSTANCE.eClass();//get the KDMPackage instance

		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xmi", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();
		// Get the resource
		Resource resource = resSet.getResource(URI.createURI(KDMModelFullPath),
				true);

		if(resource == null){
			System.err.println("Erro na leitura.");
		}else{
			raizKDMInitialMap = (Segment) resource.getContents().get(0); 
		}

		












	}

}
