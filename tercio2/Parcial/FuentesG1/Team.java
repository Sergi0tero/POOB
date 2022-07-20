import java.util.Collection;

public class Team implements Feedbacker {

	private String nickname;

	private SwFactory swFactory;

	private Collection<Developer> members;

	private Inscription inscription;


	/**
	 * @see Feedbacker#calculateExperience()
	 * 
	 *  
	 */
	public static int calculateExperience() {
		return 0;
	}

}
