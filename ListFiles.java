public class ListFiles extends Command {

    public ListFiles(VCS vcs) {
        super(vcs);
    }

    @Override
    public void execute() {
        Util.listFiles(vcs.getRootDir());
    }
    
}
