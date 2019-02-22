
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ABC
 */
public class Test {
    static List<PointTO> points;
    PlantModelTO plantModel=null;
    public PlantModelTO xmlToObject(){
    PlantModelTO plantModel=null;
        try{
       File file=new File("Demo-01.xml");
       JAXBContext jcontext=JAXBContext.newInstance(PlantModelTO.class);
       Unmarshaller unmarshal=jcontext.createUnmarshaller();
       plantModel=(PlantModelTO)unmarshal.unmarshal(file);
       points=plantModel.getPoints();
       for(PointTO p:points){
           System.out.println("Point name "+p.getName());
           System.out.println("X Position "+p.getxPosition());
           System.out.println("Y Position "+p.getyPosition());
           System.out.println("Z Position "+p.getzPosition());
      
       }
   }catch(Exception e){
        e.printStackTrace();
           }
    return plantModel;
    }
    public  void objectToXML(PlantModelTO plantModel)
    {
       try{
           JAXBContext jcontext =JAXBContext.newInstance(PlantModelTO.class);
           Marshaller marshaller=jcontext.createMarshaller();
           marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
           this.plantModel=plantModel;
            marshaller.setSchema(Test.createSchema());
            marshaller.marshal(plantModel, new FileOutputStream("Demo-02.xml"));
           
       }catch(Exception e ){
           e.printStackTrace();
       }
     
       
    }
    
      private static Schema createSchema()
      throws SAXException {
    URL schemaUrl = Test.class.getResource("model-0.0.2.xsd");
    SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    return schemaFactory.newSchema(schemaUrl);
  } 
    
    
    public static void main(String[] args){
            Test test=new Test();
           PlantModelTO plantmoadel1=test.xmlToObject();
           test.objectToXML(plantmoadel1);
    }
}
