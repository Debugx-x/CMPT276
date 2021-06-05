package Camera_DOF.Tests;

import Camera_DOF.model.Depth_of_Field_Calculator;
import Camera_DOF.model.Lens;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class Depth_of_Field_CalculatorTest {

    Lens lens = new Lens("Canon",1.8,50);
    Lens lens1 = new Lens("Tamron",2.8,90);
    Lens lens2 = new Lens("Sigma",2.8,200);
    Lens lens3 = new Lens("Nikon",4.0,200);


    @Test
    void testHyperfocalDistance(){
        Depth_of_Field_Calculator obj = new Depth_of_Field_Calculator(lens,1.8,1.1);
        assertEquals(47.89,obj.Calc_Hyperfocal_Distance(),0.01);
    }

    @Test
    void testNearFocalpoint(){
        Depth_of_Field_Calculator obj = new Depth_of_Field_Calculator(lens1,2.8,2);
        assertEquals(1.96,obj.Calc_Near_Focalpoint(),0.01);
    }

    @Test
    void testFarFocalpoint(){
        Depth_of_Field_Calculator obj = new Depth_of_Field_Calculator(lens,11,15);
        assertEquals(Double.POSITIVE_INFINITY,obj.Calc_Far_Focalpoint(),0.01);
    }

    @Test
    void testDepthofField(){
        Depth_of_Field_Calculator obj = new Depth_of_Field_Calculator(lens2,2.8,20);
        assertEquals(1.61,obj.Calc_Depth_of_Field(),0.01);
    }

    @Test
    void testALL(){
        Depth_of_Field_Calculator obj = new Depth_of_Field_Calculator(lens3,36,40);
        assertAll("Correctly calculations",
                () -> assertEquals(38.31,obj.Calc_Hyperfocal_Distance(),0.01),
                () -> assertEquals(19.61,obj.Calc_Near_Focalpoint(),0.01),
                () -> assertEquals(Double.POSITIVE_INFINITY,obj.Calc_Far_Focalpoint(),0.01),
                () -> assertEquals(Double.POSITIVE_INFINITY,obj.Calc_Depth_of_Field(),0.01)
        );
    }
}