import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

import java.util.Scanner;

public class Test
{
	public static void main(String[] args)
	{
		JavaCSG csg = JavaCSGFactory.createDefault();
		/*  Geometry3D cyl = csg.cylinder3D(10, 20, 32, true);
		csg.view(cyl);
		Geometry3D cyl3 = csg.cylinder3D(10, 20, 32, false);
		csg.view(cyl3);
		Geometry3D cylle =csg.union3D(cyl3, cyl);
		csg.view(cylle); */

		Calculator calculator = new Calculator();
		double length = 400;
		double width = 200;
		double height = 230;
		double spaceRaft = 40;
		double raftWidth = 10;



		double raftNum = calculator.raftQuantityCalculator(length);

		Geometry3D raft = csg.box3D(width, raftWidth, raftWidth, false);

		Geometry3D translateRaftPH = csg.translate3D(0, 0, height-raftWidth).transform(raft);
		Geometry3D translateRaft = csg.translate3D(0, 0, height-raftWidth).transform(raft);
		Geometry3D allRafts = csg.union3D(translateRaftPH, translateRaft);

		double k = 0;

		for (double  i = 0; i < raftNum; i++){

			translateRaft = csg.translate3D(0, k, height-raftWidth).transform(raft);
			allRafts = csg.union3D(translateRaftPH, translateRaft);
			translateRaftPH = allRafts;
			k = k + spaceRaft;
		}
		csg.view(allRafts);




	}
}
