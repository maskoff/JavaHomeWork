/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polygon;

/**
 *
 * @author Максим
 */
public class Segment {
    
    private Point a;

    public Point getA() {
        return a;
    }

    public void setA(Point a) {
        this.a = a;
    }
    private Point b;

    public Point getB() {
        return b;
    }

    public void setB(Point b) {
        this.b = b;
    }

    public Segment(Point a, Point b) {
        this.a = a;
        this.b = b;
    }
    
    public static Point crossPoint (Segment a, Segment b){
    float ua1 =((b.getB().getX()-b.getA().getX())*(a.getA().getY()-b.getA().getY())-
                (b.getB().getY()-b.getA().getY())*(a.getA().getX()-b.getA().getX()));
    float ua2= ((b.getB().getY()-b.getA().getY())*(a.getB().getX()-a.getA().getX())-
                (b.getB().getX()-b.getA().getX())*(a.getB().getY()-a.getA().getY()));
    float ua=ua1/ua2;
    float ub1 =((a.getB().getX()-a.getA().getX())*(a.getA().getY()-b.getA().getY())-
                (a.getB().getY()-a.getA().getY())*(a.getA().getX()-b.getA().getX()));
    float ub = ub1/ua2;
    float xPoint = a.getA().getX()+ua*(a.getB().getX()-a.getA().getX());
    float yPoint = a.getA().getY()+ua*(a.getB().getY()-a.getA().getY());
    Point answer = new Point(xPoint, yPoint);
    if (ua2==0){
        return null;}
    else if ((0<=ua && ua<=1) && (0<=ub && ub<=1 )){
        return answer;}
    else
       return null; 
}
}