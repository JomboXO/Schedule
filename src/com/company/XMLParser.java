package com.company;

import com.company.Entity.Flow;
import com.company.Entity.Group;
import com.company.Entity.Load;
import com.sun.prism.shader.AlphaOne_LinearGradient_AlphaTest_Loader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Татьяна on 13.05.2017.
 */
public class XMLParser {
    private static final String FILENAME = "src/input.xml";
    private static int flowNumber = 0;

    public static void goLoads(List<Load> groups, List<Flow> flows) {
        try {
// Строим объектную модель исходного XML файла
            final File xmlFile = new File(System.getProperty("user.dir")
                    + File.separator + FILENAME);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlFile);

            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("subject");

            int numberFlow, hoursLec, hoursLab, hoursPrac, numberFlowForTeacher, hoursLecTeacher, hoursLabTeacher, hoursPracTeacher, numFlow;
            String teacher = null;
            for (int i = 0; i < nodeList.getLength(); i++) {
                // Выводим информацию по каждому из найденных элементов
                Node node = nodeList.item(i);
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    String subject = element.getAttribute("name");
                    NodeList nodeGroup = element.getElementsByTagName("groups");
                    List<Group> n = new ArrayList<>();
                    Node node11 = nodeGroup.item(0);
                    if (Node.ELEMENT_NODE == node11.getNodeType()) {
                        Element element1 = (Element) node11;
                        NodeList gr = element1.getElementsByTagName("group");
                        for (int f = 0; f < gr.getLength(); f++) {
                            Node nnn = gr.item(f);
                            Element e = (Element) nnn;
                            String ss = e.getAttribute("people");
                            Group group = new Group(element1.getElementsByTagName("group").item(f).getTextContent(), ss);
                            n.add(group);
                        }
                    }

                    numberFlow = SetFlow(n, flows);

                    hoursLec = Integer.parseInt(element.getElementsByTagName("hoursLec").item(0).getTextContent());
                    hoursLab = Integer.parseInt(element.getElementsByTagName("hoursLab").item(0).getTextContent());
                    hoursPrac = Integer.parseInt(element.getElementsByTagName("hoursPrac").item(0).getTextContent());

                    NodeList nodeTeachers = element.getElementsByTagName("teachers");
                    Node nod = nodeTeachers.item(0);
                    if (Node.ELEMENT_NODE == nod.getNodeType()){
                        Element elemTeach = (Element) nod;
                        NodeList nodeTeacher = elemTeach.getElementsByTagName("teacher");
                        for (int k = 0; k < nodeTeacher.getLength();k++){
                            Node nodeTeach = nodeTeacher.item(k);
                            if (Node.ELEMENT_NODE == nodeTeach.getNodeType()){
                                Element elementTeach = (Element) nodeTeach;
                                teacher = elementTeach.getAttribute("name");
                                nodeGroup = elementTeach.getElementsByTagName("groups");
                                node11 = nodeGroup.item(0);
                                if (Node.ELEMENT_NODE == node11.getNodeType()) {
                                    Element element1 = (Element) node11;
                                    NodeList gr = element1.getElementsByTagName("group");
                                    n = new ArrayList<>();
                                    for (int f = 0; f < gr.getLength(); f++) {
                                        Node nnn = gr.item(f);
                                        Element e = (Element) nnn;
                                        String ss = e.getAttribute("people");
                                        Group group = new Group(element1.getElementsByTagName("group").item(f).getTextContent(), ss);
                                        n.add(group);
                                    }
                                }
                            }
                            hoursLecTeacher = Integer.parseInt(element.getElementsByTagName("lecture").item(k).getTextContent());
                            hoursLabTeacher = Integer.parseInt(element.getElementsByTagName("lab").item(k).getTextContent());
                            hoursPracTeacher = Integer.parseInt(element.getElementsByTagName("practice").item(k).getTextContent());
                            numberFlowForTeacher = SetFlow(n, flows);
                            if (hoursLecTeacher != 0) {
                                groups.add(new Load(subject, numberFlowForTeacher, getHours(hoursLec), 0, teacher));
                            }
                            if (hoursLabTeacher != 0) {
                                boolean bol = false;
                                for (Flow flow : flows) {
                                    if (flow.getNumberFlow() == numberFlowForTeacher && flow.getGroupList().size() * hoursLab == hoursLabTeacher) {
                                        for (Group group : flow.getGroupList()) {
                                            List<Group> gr = new ArrayList<>();
                                            gr.add(group);
                                            numFlow = SetFlow(gr, flows);
                                            groups.add(new Load(subject, numFlow, getHours(hoursLab), 2, teacher));
                                        }
                                        break;
                                    }
                                    else if(hoursLab == hoursLabTeacher){
                                        bol = true;
                                    }
                                }
                                if (bol){
                                    groups.add(new Load(subject, numberFlowForTeacher, getHours(hoursLab), 2, teacher));
                                }
                            }
                            if (hoursPracTeacher != 0) {
                                boolean bol = false;
                                for (Flow flow : flows) {
                                    if (flow.getNumberFlow() == numberFlowForTeacher && flow.getGroupList().size() * hoursPrac == hoursPracTeacher) {
                                        for (Group group : flow.getGroupList()) {
                                            List<Group> gr = new ArrayList<>();
                                            gr.add(group);
                                            numFlow = SetFlow(gr, flows);
                                            groups.add(new Load(subject, numFlow, getHours(hoursPrac), 1, teacher));
                                        }
                                        break;
                                    }
                                    else if(hoursPrac == hoursPracTeacher){
                                        bol = true;
                                    }
                                }
                                if (bol){
                                    groups.add(new Load(subject, numberFlowForTeacher, getHours(hoursLab), 1, teacher));
                                }
                            }
                        }
                    }
//                    for (int h = 0; h < nodeTeachers.getLength(); h++) {
//                        Node node1 = nodeGroup.item(h);
//                        if (Node.ELEMENT_NODE == node1.getNodeType()) {
//                            Element element1 = (Element) node1;
////                            teacher = element1.getAttribute("name");//.getElementsByTagName("teacher").item(0).getTextContent();
//
//                            NodeList nodeGroup1 = element1.getElementsByTagName("groups");
//                            n.clear();
//                            for (int g = 0; g < nodeGroup1.getLength(); g++) {
//                                Node node2 = nodeGroup1.item(g);
//                                if (Node.ELEMENT_NODE == node2.getNodeType()) {
//                                    Element element2 = (Element) node2;
//                                    NodeList gr = element2.getElementsByTagName("group");
//                                    for (int f = 0; f < gr.getLength(); f++) {
//                                        Group group = new Group(element2.getElementsByTagName("group").item(f).getTextContent());
//                                        n.add(group);
//                                    }
//                                }
//                            }
//
//                        }
//                    }
                }

            }

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLParser.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLParser.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLParser.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    private static double getHours(int hours) {
        double h = 0;
        if (hours > 0 && hours <= 9) {
            h = 0.5;
        } else if (hours > 10 && hours <= 18) {
            h = 1;
        } else if (hours > 30 && hours < 36) {
            h = 2;
        } else if (hours > 50) h = 3;
        return h;
    }

    private static int SetFlow(List<Group> nn, List<Flow> flows) {

        if (!flows.isEmpty()) {
            flowNumber = flows.get(flows.size()-1).getNumberFlow();
            boolean bool = false;
            for (int i = 0; i < flows.size(); i++) {
                if (flows.get(i).getGroupList().equals(nn)) {
                    flowNumber = flows.get(i).getNumberFlow();
                    bool = true;
                    break;
                }
            }
            if (!bool) {
                flowNumber++;
                flows.add(new Flow(flowNumber, nn, countPeopleInFlow(nn)));
            }
        } else {
            flowNumber++;
            flows.add(new Flow(flowNumber, nn, countPeopleInFlow(nn)));
        }
        return flowNumber;
    }

    private static int countPeopleInFlow(List<Group> nn) {
        int ret = 0;
        for (Group group : nn){
            ret = ret + Integer.parseInt(group.getCountPeople());
        }
        return ret;
    }

}
