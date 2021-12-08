public abstract class Command {

    VCS vcs;

    public Command (VCS vcs) {
        this.vcs = vcs;
    }

    public void execute() {
        // override me!
    }

    public static Command parse(String cmdName, VCS vcs) {
        switch (cmdName) {
            case "exit":
                return new Exit(vcs);
            case "listfiles":
                return new ListFiles(vcs);
            case "commit":
                return new Commit(vcs);
            default:
                return null;
        }
    }
}
