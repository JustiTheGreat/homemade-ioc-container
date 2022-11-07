package components;

public class SomethingImplementation implements SomethingInterface {

    private OtherInterface otherInterface;
    private AnotherInterface anotherInterface;

    public SomethingImplementation(OtherInterface otherInterface, AnotherInterface anotherInterface) {
        this.otherInterface = otherInterface;
        this.anotherInterface = anotherInterface;
    }

    @Override
    public OtherInterface getOtherInterface() {
        return otherInterface;
    }

    @Override
    public AnotherInterface getAnotherInterface() {
        return anotherInterface;
    }
}
