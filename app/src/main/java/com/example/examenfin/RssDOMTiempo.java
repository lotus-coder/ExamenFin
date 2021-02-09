package com.example.examenfin;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class RssDOMTiempo {

    private URL rssURL;

    public RssDOMTiempo(String url){
        try{
            this.rssURL = new URL (url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public Tiempo parse() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Tiempo temporal = new Tiempo();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(this.getInputStream());
            Element root = dom.getDocumentElement();

            NodeList items = root.getElementsByTagName("day1");

            for (int i=0; i<items.getLength(); i++){


                Node dia = items.item(i);
                NodeList datosTiempo = items.item(i).getChildNodes();

                for (int j=0; j<datosTiempo.getLength(); j++){
                    Node dato = datosTiempo.item(j);
                    String etiqueta = dato.getNodeName();

                    NamedNodeMap atributos = dato.getAttributes();

                        if (etiqueta.equals("text")) {
                            temporal.setEstadocielo( dato.getFirstChild().getNodeValue());
                        } else if (etiqueta.equals("temperature_max")) {
                            temporal.setTempmax(Integer.parseInt(dato.getFirstChild().getNodeValue()));
                        } else if (etiqueta.equals("temperature_min")) {
                            temporal.setTempmin(Integer.parseInt(dato.getFirstChild().getNodeValue()));
                        } else if (etiqueta.equals("date")) {
                            temporal.setFecha(dato.getFirstChild().getNodeValue());
                        }
                    }
                }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return temporal;
    }

    public String dameTexto (Node dato) {
        StringBuilder texto = new StringBuilder();
        NodeList fragmentos = dato.getChildNodes();
        for (int k=0; k<fragmentos.getLength(); k++) {
            texto.append(fragmentos.item(k).getNodeValue());
        }
        return texto.toString();
    }

    private InputStream getInputStream() {
        try {
            return rssURL.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
