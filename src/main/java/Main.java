import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

public class Main
{
    public static void main(String[] args)
    {
        JavaCSG csg = JavaCSGFactory.createDefault();
        Geometry3D cyl2 = csg.cylinder3D(30, 56, 32, false);
        csg.view(cyl2);



    }
}
