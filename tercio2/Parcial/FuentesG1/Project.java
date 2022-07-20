import java.time.LocalDate;
import java.util.Collection;

public class Project implements Feedbacker {

	private String id;

	private String urlFormulation;

	private String client;

	private LocalDate start;

	private LocalDate end;

	private int prize;

	private SwFactory swFactory;

	private Inscription inscription;

	private Collection<Requirement> requirements;

	private Inscription winner;

	private Project project;

	private Project project;

	private Requirement requirement;

	private Certificates certificates;


	/**
	 * @see Feedbacker#calculateExperience()
	 * 
	 *  
	 */
	public static int calculateExperience() {
		return 0;
	}

}
