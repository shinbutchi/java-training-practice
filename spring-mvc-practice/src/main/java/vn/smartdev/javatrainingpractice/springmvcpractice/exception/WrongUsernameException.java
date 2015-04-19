package vn.smartdev.javatrainingpractice.springmvcpractice.exception;

public class WrongUsernameException extends RuntimeException {
    private String exceptionMsg;

    public WrongUsernameException(String exceptionMsg) {
        super(exceptionMsg);
    }

    public String getExceptionMsg(){
        return this.exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

}
