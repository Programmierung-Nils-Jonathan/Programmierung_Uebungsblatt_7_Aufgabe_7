public class Commit extends ListFiles {

    public Commit(VCS vcs) {
        super(vcs);
    }

    @Override
    public void execute() {
        System.out.println("createTimeStampDir");

        String timeStampDir = this.createTimeStampDir();

        System.out.println("moveOldBackupDirFiles");

        this.moveOldBackupDirFiles(timeStampDir);

        System.out.println("copyRootFiles");

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
            String destFilename = Util.appendFileOrDirname(Util.appendFileOrDirname(vcs.getBackupDir(), timeStampDir), file);

            System.out.println("srcFilename" + srcFilename);
            System.out.println("destFilename" + destFilename);

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
