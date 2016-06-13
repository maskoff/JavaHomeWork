/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polygon;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.*;
import java.util.ArrayList;



/**
 *
 * @author Максим
 */
public class Start {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            List<String> lines=Files.readAllLines(
                    Paths.get("C:\\Users\\Максим\\Documents\\NetBeansProjects\\Polygon\\src\\polygon\\poligons.json")
                    ,Charset.defaultCharset());
            String fileContents="";
                for(String line:lines){
                    fileContents+=line;
                }
            JsonElement jelement = new JsonParser().parse(fileContents);
            Gson gson = new Gson();
            JsonArray jPolygons=jelement.getAsJsonArray();
            ArrayList<Polygon> polygons = new ArrayList<>();
            for (JsonElement jPolygon:jPolygons){
                Polygon polygon =gson.fromJson(jPolygon, Polygon.class);
                polygons.add(polygon);
            }
            
            int resetCount = 1;
            ArrayList<Polygon> crossPolygons = new ArrayList<>();
            Polygon crossPolygon;
            ArrayList<Point> q = new ArrayList<>();
            
            for (int i=0; i<polygons.size(); i++){
                for (int j=resetCount; j<polygons.size(); j++){
                    if (i!=j){
                        crossPolygon = Polygon.crossPolygons(polygons.get(i), polygons.get(j));
                        if (!crossPolygon.getVerticles().isEmpty()){
                        crossPolygons.add(crossPolygon);}
                        
                    }
                }
                resetCount=0;
            }
            
            ArrayList<Float> areaList = new ArrayList<>();
            for (int i=0;i<crossPolygons.size();i++){
                areaList.add(Polygon.area(crossPolygons.get(i)));}
            
            for (int i=0; i<areaList.size()-1; i++){
                float x1 = areaList.get(i);
                for (int j=i+1; j<areaList.size();j++){
                    float x2 = areaList.get(j);
                    if (Math.round(x1)==Math.round(x2))
                        areaList.remove(j);}
            }
            float sumArea = 0;
            for (float area:areaList){
                sumArea+=area;}
            System.out.println(String.format("Cуммарная площадь пересечения заданных многоугольников равна %s ", sumArea));
            
            
        } catch (IOException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}