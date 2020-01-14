package majiang.com.example.myproject.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND("\"你找的问题不在了，要不换个试试？\"");

    //覆写了ICustomizeErrorCode接口的方法
    @Override
    public String getMessage() {
        return message;
    }

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
