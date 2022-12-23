package rest.validator;

import java.util.List;
import rest.model.Error;

public class ValidateFieldsException extends Throwable {
    private List<Error> errorMsg;

    public ValidateFieldsException(List<Error> errorMessage) {
        this.errorMsg = errorMessage;
    }

    public List<Error> getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(List<Error> errorMsg) {
        this.errorMsg = errorMsg;
    }
}