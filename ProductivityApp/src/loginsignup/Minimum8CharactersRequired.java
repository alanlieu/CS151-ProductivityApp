package loginsignup;

public class Minimum8CharactersRequired extends PasswordException {
	private static final long serialVersionUID = 1L;

	public Minimum8CharactersRequired() {
        super("Password must be at least 8 characters long.");
    }
}
