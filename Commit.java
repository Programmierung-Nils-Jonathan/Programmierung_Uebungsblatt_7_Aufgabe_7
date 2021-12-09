public class Commit extends ListFiles implements Modifying {

    public Commit(VCS vcs) {
        super(vcs);
    }

    public String getInformation() {
        return "Files: Copy and Move \n" +
            "Directory: create";
    }

    @Override
    public void execute() {
        String timeStampDir = this.createTimeStampDir();

        this.moveOldBackupDirFiles(timeStampDir);

        this.copyRootFiles();

        System.out.println("Committed the following files:");

        super.execute();
    }

    private String createTimeStampDir() {
        String timeStampDir = Util.appendFileOrDirname(vcs.getBackupDir(), Util.getTimestamp());
        Util.mkdir(timeStampDir);

        return timeStampDir;
    }

    private void moveOldBackupDirFiles(String timeStampDir) {
        String[] backupFiles = Util.listFiles(vcs.getBackupDir());

        for(String file : backupFiles) {
            String srcFilename = Util.appendFileOrDirname(vcs.getBackupDir(), file);
            String destFilename = Util.appendFileOrDirname(timeStampDir, file);

            Util.moveFile(srcFilename, destFilename);
        }
    }

    private void copyRootFiles() {
        String[] rootFiles = Util.listFiles(vcs.getRootDir());

        for(String file : rootFiles) {
            String srcFilename = Util.appendFileOrDirname(vcs.getRootDir(), file);
            String destFilename = Util.appendFileOrDirname(vcs.getBackupDir(), file);
            Util.copyFile(srcFilename, destFilename);
        }
    }
    
}
