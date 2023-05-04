package loginsignup;

public class SpecialCharacterMissing extends PasswordException {
	private static final long serialVersionUID = 1L;

	public SpecialCharacterMissing() {
        super("Password must contain at least one special character (e.g. !@#$%).");
    }
}
