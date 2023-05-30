import java.util.Scanner;

public class MainMenu {

        private double width;
        private double length;

        public String setDimensions() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Indsæt venligts carportens længde:" +
                    " ");

            length = scanner.nextDouble();
            if (length >= 400 && length <= 600 && length%50 == 0){

                System.out.println("Indsæt venligst carportens bredde: ");
                width = scanner.nextDouble();

                if (width >= 300 && width <= 600 && width%50 == 0){
                    return "Carporten er nu klar til print!" +
                            "Gå til mappen 'OpenSCAD' og download filen View0.scad. " +
                            "Navngiv den efter ordre ID og kør filen i OpenSCAD for at se 3D modellen";

                } else {
                        return "Fejl! Målene er ugyldige";
                    }

                } else {
                    return "Fejl! Målene er ugyldige";
                }
            }

        public double getWidth() {
            return width;
        }

        public double getLength() {
            return length;
        }

    }



