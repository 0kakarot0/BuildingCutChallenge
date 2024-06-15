package model;

public class CommandDetails {

    private String fields;
    private String delimiter;
    private String fileName;
    private String printLimiter;

    public CommandDetails(String fields, String delimiter, String fileName, String printLimiter) {
        this.fields = fields;
        this.delimiter = delimiter;
        this.fileName = fileName;
        this.printLimiter = printLimiter;
    }

    public String getFields() {
        return fields;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPrintLimiter() {
        return printLimiter;
    }

}
