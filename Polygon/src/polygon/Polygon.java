/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polygon;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Максим
 */
public class Polygon {
    
    private ArrayList<Point> verticles;

    public ArrayList<Point> getVerticles() {
        return verticles;
    }

    public void setVerticles(ArrayList<Point> verticles) {
        this.verticles = verticles;
    }

    public Polygon(ArrayList<Point> verticles) {
        this.verticles = verticles;
    }
    
    public static ArrayList<Float> polygonArrayX (Polygon p){
        ArrayList<Float> coordinations = new ArrayList<Float>();
        for (int i=0; i<p.getVerticles().size(); i++){
            coordinations.add(p.getVerticles().get(i).getX());
        }
        return coordinations;
    }
    
    public static ArrayList<Float> polygonArrayY (Polygon p){
        ArrayList<Float> coordinations = new ArrayList<Float>();
         for (int i=0; i<p.getVerticles().size(); i++){
            coordinations.add(p.getVerticles().get(i).getY());
        }
        return coordinations;
    }
    
    public static float area (Polygon p ){
        ArrayList<Float> x = new ArrayList<>(polygonArrayX(p));
        ArrayList<Float> y = new ArrayList<>(polygonArrayY(p));
        float area = 0;
        for (int i=0; i<x.size()-1; i++){ 
            area+=(x.get(i)+x.get(i+1))*(y.get(i)-y.get(i+1));
        }
        area+=(x.get(x.size()-1)+x.get(0))*(y.get(x.size()-1)-y.get(0));
        return area/2;
    }
    
    public static float maxPolygonX (Polygon p){
        ArrayList<Float> x = polygonArrayX(p);
        Collections.sort(x);
        return x.get(x.size()-1);
    }
    
    public static boolean pointToPolygon (Polygon p, Point a){
        float xMax = maxPolygonX(p);
        boolean answer = false;
        int intersectionsNum = 0;
        for (int i=0; i<p.getVerticles().size()-1; i++){
            Segment m = new Segment(p.getVerticles().get(i),p.getVerticles().get(i+1));
            Segment n = new Segment(a, new Point(xMax,a.getY()));
            if (Segment.crossPoint(m, n)!=null)
                intersectionsNum++;
        }
            Segment m = new Segment(p.getVerticles().get(0),p.getVerticles().get(p.getVerticles().size()-1));
            Segment n = new Segment(a, new Point(xMax,a.getY()));
            if (Segment.crossPoint(m, n)!=null)
                intersectionsNum++;
        
        if (intersectionsNum == 1)
            return true;
        else 
            return false;
    } 
    
    public static Polygon crossPolygons (Polygon a,Polygon b){
       ArrayList<Point> points = new ArrayList<>(); 
       points = Point.sumArrayPoint(cycleCheckPoint(a, b), cycleCheckPoint(b, a));
       points = Point.fixIdenticalPoint(points);
       return new Polygon(points);
    }
    
    public static ArrayList<Point> cycleCheckPoint (Polygon a, Polygon b){
       ArrayList<Point> points = new ArrayList<>(); 
       boolean checkPoint = true;
       Point interPoint = new Point(0,0);
       Segment segmentOneFromPoint = new Segment(interPoint, interPoint);
       Segment segmentTwoFromPoint = new Segment(interPoint, interPoint);
       Segment segmentTestPolygon = new Segment(interPoint, interPoint);
       for (int i=0; i<b.getVerticles().size(); i++){
            checkPoint=pointToPolygon(a, b.getVerticles().get(i));
            if (checkPoint==true){
                points.add(b.getVerticles().get(i));
                if (i==0){
                    segmentOneFromPoint.setA(b.getVerticles().get(i));
                    segmentOneFromPoint.setB(b.getVerticles().get(i+1));}
                else if (i==b.getVerticles().size()-1){
                    segmentOneFromPoint.setA(b.getVerticles().get(i));
                    segmentOneFromPoint.setB(b.getVerticles().get(0));}
                else {
                    segmentOneFromPoint.setA(b.getVerticles().get(i));
                    segmentOneFromPoint.setB(b.getVerticles().get(i+1));}
                for (int j=0; j<a.getVerticles().size(); j++){
                    if (j==a.getVerticles().size()-1){
                        segmentTestPolygon.setA(a.getVerticles().get(j));
                        segmentTestPolygon.setB(a.getVerticles().get(0));}
                    else{
                        segmentTestPolygon.setA(a.getVerticles().get(j));
                        segmentTestPolygon.setB(a.getVerticles().get(j+1));}
                    interPoint=Segment.crossPoint(segmentTestPolygon, segmentOneFromPoint);
                        if (interPoint!=null){
                            points.add(interPoint);}
                }
                }                    
       }
    return points;      
    }
}

       
      

