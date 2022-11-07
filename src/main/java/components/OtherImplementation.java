package components;

public class OtherImplementation implements OtherInterface {

    private int int_;
    private Integer integer;
    private AnotherInterface anotherInterface1;
    private AnotherInterface anotherInterface2;

    public OtherImplementation(int int_, Integer integer, AnotherInterface anotherInterface1, AnotherInterface anotherInterface2){
        this.int_=int_;
        this.integer=integer;
        this.anotherInterface1=anotherInterface1;
        this.anotherInterface2=anotherInterface2;
    }

    @Override
    public int getInt_() {
        return int_;
    }

    @Override
    public Integer getInteger() {
        return integer;
    }

    @Override
    public AnotherInterface getAnotherInterface1() {
        return anotherInterface1;
    }

    @Override
    public AnotherInterface getAnotherInterface2() {
        return anotherInterface2;
    }
}
