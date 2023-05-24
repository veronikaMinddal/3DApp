import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

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

		double raftNum = calculator.raftQuantityCalculator(600);

		Geometry3D raft = csg.box3D(250, 10, 10, false);



		double k = 0;

		Geometry3D translateBoxPH = csg.translate3D(0, 0, 200).transform(raft);
		Geometry3D translateBox = csg.translate3D(0, 0, 200).transform(raft);
		Geometry3D allRafts = csg.union3D(translateBoxPH, translateBox);




		for (double  i = 0; i < raftNum; i++){

			translateBox = csg.translate3D(0, k, 600).transform(raft);
			allRafts = csg.union3D(translateBoxPH, translateBox);
			translateBoxPH = allRafts;
			k = k + 40;
		}
		csg.view(allRafts);




	}
}
