public abstract class Command {

    VCS vcs;

    public void execute() {
        // override me!
    }

    public static Command parse(String cmdName, VCS vcs) {
        // implement me!
        return null;
    }
}
