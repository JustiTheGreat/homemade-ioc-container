import components.AnotherInterface;
import components.OtherInterface;
import components.SomethingInterface;
import dependency_injection_classes.DependencyInjectionContainer;

public class Main {
    public static void main(String[] args) {

        DependencyInjectionContainer container = new DependencyInjectionContainer();
        container.configure();

        SomethingInterface component1 = container.getComponentImplementation(SomethingInterface.class);
        OtherInterface component2 = container.getComponentImplementation(OtherInterface.class);
        AnotherInterface component3 = container.getComponentImplementation(AnotherInterface.class);

        System.out.println("COMPONENT 1 COMPOSITION");
        System.out.println("INSTANCE: " + component1);
        System.out.println("FIELDS:");
        System.out.println(component1.getOtherInterface());
        System.out.println("|--" + component1.getOtherInterface().getInt_());
        System.out.println("|--" + component1.getOtherInterface().getInteger());
        System.out.println("|--" + component1.getOtherInterface().getAnotherInterface1());
        System.out.println("|--" + component1.getOtherInterface().getAnotherInterface2());
        System.out.println(component1.getAnotherInterface());
        System.out.println();

        System.out.println("COMPONENT 2 COMPOSITION");
        System.out.println("INSTANCE: " + component2);
        System.out.println("FIELDS:");
        System.out.println(component2.getInt_());
        System.out.println(component2.getInteger());
        System.out.println(component2.getAnotherInterface1());
        System.out.println(component2.getAnotherInterface2());
        System.out.println();

        System.out.println("COMPONENT 3 COMPOSITION");
        System.out.println("INSTANCE: " + component3);
        System.out.println("FIELDS:");
    }
}
