package Labs.Temp.models;

public class ErrorData {
    //public String errorMessage;
    public String message;

    public ErrorData(String message){
        this.message = message;
    }

    public ErrorData(){
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
