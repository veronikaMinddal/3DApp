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
		double length = 500;
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
		Geometry3D translateRaftPH = csg.translate3D(0, spaceRaft + raftWidth, height-raftWidth).transform(raft);
		Geometry3D translateRaft = csg.translate3D(0, 0, height-raftWidth).transform(raft);
		Geometry3D allRafts = csg.union3D(translateRaftPH, translateRaft);

		//sets a double that adds up the amount each raft need to move
		double k = spaceRaft + raftWidth;

		//makes the amount of rafts gotten from raftQuantityCalculator()
		// and places them with the right amount of space between
		for (double  i = 0; i <= raftNum; i++){

			translateRaft = csg.translate3D(0, k, height-raftWidth).transform(raft);
			allRafts = csg.union3D(translateRaftPH, translateRaft);
			translateRaftPH = allRafts;
			k = k + spaceRaft;
		}

		double beamHeight = 20;
		double beamWidth = 10;

		//Geometry3D longBeam = csg.box3D(  length, beamWidth, beamHeight, false);
		Geometry3D shortBeam = csg.box3D(  width - (beamWidth * 2),  beamWidth, beamHeight, false);

		//Geometry3D translatebeam1 = csg.translate3D(0, 0, height - raftWidth - beamHeight).transform(shortBeam);
		//Geometry3D translatebeam2 = csg.translate3D(0, width - beamWidth, height - raftWidth - beamHeight).transform(shortBeam);
		Geometry3D translatebeam3 = csg.translate3D(0, 0, height - raftWidth - beamHeight).transform(shortBeam);
		Geometry3D translatebeam4 = csg.translate3D(0, length - beamWidth, height - raftWidth - beamHeight).transform(shortBeam);

		Geometry3D allBeams = csg.union3D(translatebeam3, translatebeam4);

		Geometry3D fullCarport = csg.union3D(allBeams, allRafts);





		//makes the final drawing
		csg.view(fullCarport);




	}
}
