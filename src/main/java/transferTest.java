import com.github.abel533.echarts.Label;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.series.Graph;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.style.ItemStyle;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import javax.swing.text.html.HTMLDocument;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class test{
        public void readXml()throws DocumentException,NullPointerException{
            SAXReader saxReader=new SAXReader();
            Document document=saxReader.read(new File("E:\\git\\vis\\abstractExecution2.xml"));
            Element rootElement=document.getRootElement();
//            String filename = rootElement.element("instance").attributeValue("filename");
//            System.out.println("filename:"+filename);
            Element instance = rootElement.element("instance");
            List<Element> sig = instance.elements("sig");
            for(Iterator it =sig.iterator();it.hasNext();){
                Element elm=(Element) it.next();
                String label = elm.attributeValue("label");
                System.out.println(label);
            }
            List<Element> field = instance.elements("field");
            for(Iterator itField=field.iterator();itField.hasNext();){
                Element elmField=(Element) itField.next();
                String label=elmField.attributeValue("label");
                if(label.equals("op")){
                    Map<String,String> elmOp=resolveAtom(elmField);
                    System.out.println("op:"+elmOp);
                }
                else if(label.equals("rval")){
                    Map<String,String> elmRval=resolveAtom(elmField);
                    System.out.println("rval:"+elmRval);
                }
                else if(label.equals("so")){
                    Map<String,String> elmSo=resolveAtom(elmField);
                    System.out.println("so:"+elmSo);
                }
                else if(label.equals("vis")){
                    Map<String,String> elmVis=resolveAtom(elmField);
                    System.out.println("vis:"+elmVis);
                }
                else if(label.equals("ar")){
                    Map<String,String> elmAr=resolveAtom(elmField);
                    System.out.println("ar:"+elmAr);
                }
                else if(label.equals("session")){
                    Map<String,String> elmSession=resolveAtom(elmField);
                    System.out.println("session+"+elmSession);
                }
                else if(label.equals("value")){
                    Map<String,String> elmValue=resolveAtom(elmField);
                    System.out.println("value:"+elmValue);
                }

            }

        }


    public Map<String, String> resolveAtom(Element e) {
        Map<String, String> elm = new HashMap<>();
        List<Element> tuple = e.elements("tuple");
        for (Iterator itTuple = tuple.iterator(); itTuple.hasNext(); ) {
            Element elmTuple = (Element) itTuple.next();
            List<Element> atom = elmTuple.elements("atom");
            Iterator itAtom = atom.iterator();
            Element elmAtom = (Element) itAtom.next();
            String temp1 = elmAtom.attributeValue("label");
            elmAtom = (Element) itAtom.next();
            String temp2 = elmAtom.attributeValue("label");
//
            elm.put(temp1, temp2);
        }
        return elm;
    }
    public String remove$(String s){
            StringBuilder sbd= new StringBuilder(s);
            int loc = sbd.indexOf("$");
            if(loc>0) {
                sbd = sbd.delete(loc, loc + 1);
                return sbd.toString();
            }
            return sbd.toString();
    }

    public void EchartsTest(){
            Map<String,String> map=new HashMap<>();
            map.put("st1","st2");
            Option option=new Option();
            option.title().text("first graph")
                .x(X.center)
                .y(Y.center);

            Graph graph1=new Graph();
            graph1.data(map);
            option.series(graph1);
            


    }

}
public class transferTest {

    public static void main(String[] args) throws DocumentException {
        test test = new test();
        test.readXml();
    }
}
