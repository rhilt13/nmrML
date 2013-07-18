
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import javax.xml.bind.*;

// import java content classes generated by binding compiler
import org.nmrml.schema.*;

/*
 * $Id: dx2nmrML.java,v 1.1 2013-07-15 09:49:33 DJ $
 */
 
public class dx2nmrML {
    
    // This sample application demonstrates how to modify a java content
    // tree and marshal it back to a xml data
    
    public static void main( String[] args ) {

        try {

            ObjectFactory objFactory = new ObjectFactory();
            NmrMLType nmrMLtype = (NmrMLType) objFactory.createNmrMLType();
 
            nmrMLtype.setVersion("1.0");
            nmrMLtype.setAccession("S1");

       /* CV List : used as references for all CV in the document */
            CVListType cvList = (CVListType) objFactory.createCVListType();
            CVType cvNMR = (CVType) objFactory.createCVType();
            cvNMR.setId("NMR");
            cvNMR.setFullName("Nuclear Magnetic Resonance CV");
            cvNMR.setVersion("0.1.0");
            cvNMR.setURI("http://msi-ontology.sourceforge.net/ontology/NMR.owl");
            cvList.getCv().add(cvNMR);
            CVType cvOBI = (CVType) objFactory.createCVType();
            cvOBI.setId("OBI");
            cvOBI.setFullName("Ontology for Biomedical Investigations");
            cvOBI.setVersion("2012.07.01");
            cvOBI.setURI("http://purl.obolibrary.org/obo/obi");
            cvList.getCv().add(cvOBI);
            cvList.setCount(new BigInteger("2"));
            nmrMLtype.setCvList(cvList);


       /* FileDescription */
            FileDescriptionType filedesc = (FileDescriptionType) objFactory.createFileDescriptionType();
            ParamGroupType paramgrp = (ParamGroupType) objFactory.createParamGroupType();
            CVParamType cvp1 = (CVParamType) objFactory.createCVParamType();
            String cvref="NMR";
            cvp1.setCvRef(cvNMR);
            cvp1.setAccession("#NMR_400128");
            cvp1.setName("NMR Sample");
            cvp1.setValue("");
            paramgrp.getCvParam().add(cvp1);
            filedesc.setFileContent(paramgrp);
            nmrMLtype.setFileDescription(filedesc);

       /* Contact List */
       /* SourceFile List */
       /* Software List */
       /* InstrumentConfiguration List */
       /* DataProcessing List */
       /* Sample List */
       /* ReferenceableParamGroup List */
       /* Spectrum List */

       /* Acquition */
            AcquisitionDimensionParameterSetType acqdimparam = 
                     (AcquisitionDimensionParameterSetType) objFactory.createAcquisitionDimensionParameterSetType();
            acqdimparam.setNumberOfDataPoints(new BigInteger("32768"));
            acqdimparam.setAcquisitionNucleus("1H");
            
            Acquisition1DType.AcquisitionParameterSet acqparam = 
                      (Acquisition1DType.AcquisitionParameterSet) objFactory.createAcquisition1DTypeAcquisitionParameterSet();
            acqparam.setDirectDimensionParameterSet(acqdimparam);

            Acquisition1DType acq1Dtype = (Acquisition1DType) objFactory.createAcquisition1DType();
            acq1Dtype.setAcquisitionParameterSet(acqparam);

            AcquisitionType acqtype = (AcquisitionType) objFactory.createAcquisitionType();
            acqtype.setAcquisition1D(acq1Dtype);

            nmrMLtype.setAcquisition(acqtype);

       /* Generate XML */
            JAXBElement<NmrMLType> nmrML = (JAXBElement<NmrMLType>) objFactory.createNmrML(nmrMLtype);

            // create a JAXBContext capable of handling classes generated into the org.po package
            JAXBContext jc = JAXBContext.newInstance( "org.nmrml.schema" );

            // create a Marshaller and marshal to a file
            Marshaller m = jc.createMarshaller();
            m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true) );
            m.marshal( nmrML, System.out );
System.out.println("OK");
            
        } catch( JAXBException je ) {
            je.printStackTrace();
        }
    }
}
