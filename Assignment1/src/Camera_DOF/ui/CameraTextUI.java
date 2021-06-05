package Camera_DOF.ui;

import Camera_DOF.model.Depth_of_Field_Calculator;
import Camera_DOF.model.Lens;
import Camera_DOF.model.LensManager;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Camera Text UI class used to interact with the users
 * the class iterates the numbered list of known lenses,
 * then asks user to select index of the lens they want to use
 * and input Aperture and distance to calculate Depth of field
 */
public class CameraTextUI {
    private final LensManager manager;
    Scanner in = new Scanner(System.in);// Read from keyboard


    public CameraTextUI(LensManager manager) {
        // Accept and store a reference to the lens manager (the model)
        // (this design is called "dependency injection")
        this.manager = manager;

        // Populate lenses (Make, max aperture (smallest supported F number), focal length [mm]):
        manager.add(new Lens("Canon", 1.8, 50));
        manager.add(new Lens("Tamron", 2.8, 90));
        manager.add(new Lens("Sigma", 2.8, 200));
        manager.add(new Lens("Nikon", 4, 200));
    }

    public void show(){
        boolean isDone = false;
        while (!isDone) {
            System.out.print("Lenses to pick from : \n");
            int count = 0;
            for (Lens lens : manager) {
                System.out.println(count + ". " + lens);
                ++count;
            }
            System.out.print("\n: ");
            int index = in.nextInt();   //inputs index of the lens
            // exits the program if user entered -1
            if (index == (-1)){
                isDone = true;
                break;
            }
            // if the entered index is invalid
            // runs the loop again and prints the list
            if(index<0|index>=count){
                System.out.print("Error: Invalid lens index.");
                continue;
            }
            System.out.print("Aperture [the F number] : ");
            double aperture = in.nextDouble();
            // checks if entered aperture is valid for the selected lens
            // if it's larger than max aperture, runs the loops again
            if (Lens.check_aperture(manager.lenses.get(index),aperture)) {
                System.out.print("ERROR: This aperture is not possible with this lens\n");
                continue;
            }
            System.out.print("Distance to subject [m] : ");
            double distance = in.nextInt();
            // creates object to Depth of calculator class, for calculations
            Depth_of_Field_Calculator obj = new Depth_of_Field_Calculator(manager.lenses.get(index),distance,aperture);
            System.out.println("\t In focus: " + formatM(obj.Calc_Near_Focalpoint()) + "m to " + formatM(obj.Calc_Far_Focalpoint()) + "m [DoF = " + formatM(obj.Calc_Depth_of_Field()) + "m]\n");
            System.out.println("\t Hyperfocal point: " + formatM(obj.Calc_Hyperfocal_Distance()) + "m\n");
        }
    }

    private String formatM(double distanceInM) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(distanceInM);
    }
}
