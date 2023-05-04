package loginsignup;

public class LowerCaseCharacterMissing extends PasswordException {
	private static final long serialVersionUID = 1L;

	public LowerCaseCharacterMissing() {
        super("Password must contain at least one lowercase letter.");
    }
}
