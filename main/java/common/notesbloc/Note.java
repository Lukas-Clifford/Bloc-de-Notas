package common.notesbloc;

import javafx.scene.Parent;
import org.w3c.dom.*;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;

public class Note {
    private final File file = new File("src/main/resources/file.xml");

    private String id = "note-" + System.currentTimeMillis();

    public Document doc = null;
    private Element element = null;
    private Element title = null;
    private Element content = null;


    public Note(Document doc) {

        this.doc = doc;

        this.element = doc.createElement("note");
        this.element.setAttribute("id", this.id);

        this.title = doc.createElement("title");
        this.title.setTextContent("Note Title");
        this.content = doc.createElement("content");

        this.element.appendChild(this.title);
        this.element.appendChild(this.content);


        doc.getDocumentElement().appendChild(this.element);
    }

    public Note(Document doc, String id){
        try{
            this.doc = doc;

            this.element = doc.getElementById(id);
            if (this.element != null) {
                this.title = (Element) this.element.getElementsByTagName("title").item(0);
                this.content = (Element) this.element.getElementsByTagName("content").item(0);

            } else System.err.println("Could not find element");

        }catch (Exception e) {System.out.println(e);}
    }

    public void setTitle(String text){
        this.title.setTextContent(text);
    }

    public void setContent(String text){
        this.content.setTextContent(text);
    }

    public String getTitle(){ return this.title.getTextContent(); }

    public String getContent(){ return this.content.getTextContent(); }

    public String getId(){ return this.id; }



    @Override
    public String toString(){
        return this.title.getTextContent() + "\n" + this.content.getTextContent();
    }

    public void save(){
        try{
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(this.doc);
            FileOutputStream output = new FileOutputStream(this.file);
            StreamResult result = new StreamResult(output);

            transformer.transform(source, result);

        }catch (Exception e){
            System.out.println("Error al guardar: " + e);
        }
    }


    public void delete() {
        this.doc.getDocumentElement().removeChild(this.element);
        this.save();
    }
}