package loginsignup;

public class UpperCaseCharacterMissing extends PasswordException {
	private static final long serialVersionUID = 1L;

	public UpperCaseCharacterMissing() {
        super("Password must contain at least one uppercase letter.");
    }
}
