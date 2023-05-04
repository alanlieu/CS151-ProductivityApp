package loginsignup;

public class NumberCharacterMissing extends PasswordException {
	private static final long serialVersionUID = 1L;

	public NumberCharacterMissing() {
        super("Password must contain at least one number.");
    }
}
