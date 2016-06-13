/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polygon;

import java.util.ArrayList;

/**
 *
 * @author Максим
 */
public class Point {
    private float x;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }
        
    private float y;

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public static ArrayList<Point> sumArrayPoint (ArrayList<Point> x, ArrayList<Point> y){
        ArrayList<Point> z = new ArrayList();
        for (int i=0; i<=x.size()-1;i++){
            z.add(x.get(i));
        }
        for (int j=0;j<=y.size()-1;j++){   
            z.add(y.get(j));
            }
        return z;
    }  
    
    public static ArrayList<Point> fixIdenticalPoint (ArrayList <Point> list){
        for (int i=0; i<list.size()-1; i++){
            float x1 = list.get(i).getX();
            float y1 = list.get(i).getY();    
        for (int j=i+1; j<list.size();j++){
            float x2 = list.get(j).getX();
            float y2 = list.get(j).getY();
            if ((x1==x2) && (y1==y2)){
                list.remove(j);}
            }  
        }
        return list;
    }    
}
