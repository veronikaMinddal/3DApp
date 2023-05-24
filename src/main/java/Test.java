import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

import java.util.Scanner;

public class Test
{
	public static void main(String[] args)
	{
		JavaCSG csg = JavaCSGFactory.createDefault();

		Calculator calculator = new Calculator();

		//sets the length and width gotten from the user
		double length = 400;
		double width = 200;

		//sets the height
		double height = 230;

		//sets dimensions for rafts (spaceRaft + raftWidth HAS TO BE EQUAL 50!!
		// If not the math has to be changed in Calculator.raftQuantityCalculator())
		double spaceRaft = 40;
		double raftWidth = 10;

		//calculates the number of rafts needed
		double raftNum = calculator.raftQuantityCalculator(length);

		//makes rafts with the right length based of width
		Geometry3D raft = csg.box3D(width, raftWidth, raftWidth, false);

		//sets up translate so there can be multiple rafts, transformed into one big figure
		Geometry3D translateRaftPH = csg.translate3D(0, 0, height-raftWidth).transform(raft);
		Geometry3D translateRaft = csg.translate3D(0, 0, height-raftWidth).transform(raft);
		Geometry3D allRafts = csg.union3D(translateRaftPH, translateRaft);

		//sets a double that adds up the amount each raft need to move
		double k = 0;

		//makes the amount of rafts gotten from raftQuantityCalculator()
		// and places them with the right amount of space between
		for (double  i = 0; i < raftNum; i++){

			translateRaft = csg.translate3D(0, k, height-raftWidth).transform(raft);
			allRafts = csg.union3D(translateRaftPH, translateRaft);
			translateRaftPH = allRafts;
			k = k + spaceRaft;
		}





		//makes the final drawing
		csg.view(allRafts);




	}
}
