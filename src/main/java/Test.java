import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class Test
{
	public static void main(String[] args)
	{
		JavaCSG csg = JavaCSGFactory.createDefault();

		Calculator calculator = new Calculator();
		MainMenu menu = new MainMenu();

		double length = 0;
		double width = 0;

		System.out.println("Velkommen administrator!");

		while (length == 0 || width == 0) {

			System.out.println(menu.setDimensions());
			length = menu.getLength();
			width = menu.getWidth();

		}

		length = menu.getLength();
		width = menu.getWidth();








		//sets the length and width gotten from the user

		//sets the height
		double height = 230;

		double roof = 5;

		//sets dimensions for rafts (spaceRaft + raftWidth HAS TO BE EQUAL 50!!
		// If not the math has to be changed in Calculator.raftQuantityCalculator())
		double spaceRaft = 50;
		double raftWidth = 10;


		//calculates the number of rafts needed
		double raftNum = calculator.raftQuantityCalculator(length);

		//makes rafts with the right length based of width
		Geometry3D raft = csg.box3D(width + raftWidth, raftWidth, raftWidth, false);


		Geometry3D translateRaft;


		//sets a double that adds up the amount each raft need to move
		double k = spaceRaft;

		ArrayList<Geometry3D> rafts = new ArrayList<>();


		//makes the amount of rafts gotten from raftQuantityCalculator()
		// and places them with the right amount of space between
		for (double  i = 0; i < raftNum; i++){
			translateRaft = csg.translate3D(0, k, height-raftWidth-roof).transform(raft);
			rafts.add(translateRaft);
			k = k + spaceRaft;
		}

		Geometry3D allRafts = csg.union3D(rafts);

		double beamHeight = 20;
		double beamWidth = 10;

		Geometry3D longBeam = csg.box3D( beamWidth, length + beamWidth,  beamHeight, false);
		Geometry3D shortBeam = csg.box3D(  width ,  beamWidth, beamHeight, false);

		Geometry3D translatebeam1 = csg.translate3D(-(width/2) , length/2, height - raftWidth - beamHeight-roof).transform(longBeam);
		Geometry3D translatebeam2 = csg.translate3D((width/2),length/2, height - raftWidth - beamHeight-roof).transform(longBeam);
		Geometry3D translatebeam3 = csg.translate3D(0, 0, height - raftWidth - beamHeight-roof).transform(shortBeam);
		Geometry3D translatebeam4 = csg.translate3D(0, length, height - raftWidth - beamHeight-roof).transform(shortBeam);

		Geometry3D allBeams = csg.union3D(translatebeam3, translatebeam4, translatebeam1, translatebeam2);

		double postHeight = height - raftWidth - beamHeight- roof;
		double postWidth = 10;

		Geometry3D post = csg.box3D(postWidth, postWidth, postHeight, false);

		Geometry3D translatePost1 = csg.translate3D( width/2, length, 0).transform(post);
		Geometry3D translatePost2 = csg.translate3D( -width/2, length, 0).transform(post);
		Geometry3D translatePost3 = csg.translate3D( -width/2, 0, 0).transform(post);
		Geometry3D translatePost4 = csg.translate3D( width/2, 0, 0).transform(post);

		double postNumber =calculator.postQuantityCalculator(length, width);
		Geometry3D allPosts;


		if(postNumber >= 6){
			Geometry3D translatePost5 = csg.translate3D( width/2, length/2, 0).transform(post);
			Geometry3D translatePost6 = csg.translate3D( -width/2, length/2, 0).transform(post);
			allPosts = csg.union3D(translatePost3, translatePost4, translatePost1, translatePost2, translatePost5, translatePost6);
		}

		else {
			allPosts = csg.union3D(translatePost3, translatePost4, translatePost1, translatePost2);
		}

		Geometry3D theRoof = csg.box3D(width + beamWidth, length + beamWidth, roof, true);
		Geometry3D translateRoof = csg.translate3D(0, length/2, height-roof).transform(theRoof);

		Geometry3D fullCarport = csg.union3D(allBeams, allRafts, allPosts, translateRoof);


		//makes the final drawing
		csg.view(fullCarport);




	}
}
