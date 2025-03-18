package common.notesbloc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class NotesHandler {
    private final File file = new File("src/main/resources/file.xml");
    private Document document;

    public NotesHandler(){
        try{
            this.document = this.createDocument();
        }catch (Exception e){System.out.println("Error al crear el documento: " + e );}

    }

    private DocumentBuilderFactory createFactory(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setValidating(true);
        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", "schema.xsd");

        return factory;
    }

    private Document createDocument() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilder dBuilder = this.createFactory().newDocumentBuilder();
        Document doc = dBuilder.parse(this.file);
        doc.getDocumentElement().normalize();

        return doc;

    }

    public Note newNote(){
        Note note = new Note(this.document);
        note.save();
        return note;
    }


    public ArrayList<Note> getNotesArray(){
        ArrayList<Note> notesArray = new ArrayList<>();

        NodeList notesList = this.document.getElementsByTagName("note");
        int numberOfNotes = notesList.getLength();
        for (int index = 0; index < numberOfNotes; index++){
            String id = notesList.item(index).getAttributes().item(0).getNodeValue();
            notesArray.add(new Note(this.document,id));
        }

        return notesArray;
    }

}
