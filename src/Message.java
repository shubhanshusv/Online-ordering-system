import java.io.Serializable;

class Message implements Serializable {
    private static final long serialVersionUID = -5399605122490343339L;

    private Integer A;
    private Integer B;
    private Integer Result;

    Message(Integer firstNumber, Integer secondNumber){
        this.A = firstNumber;
        this.B = secondNumber;
    }

    Integer getA() {
        return A;
    }

    Integer getB() {
        return B;
    }

    void setResult(Integer X)  {
        Result = X;
    }

    Integer result(){return Result;}


}
