package dependency_injection_classes;

public class ComponentConfiguration {

    private Class<?> interface_;
    private Class<?> implementation;
    private Class<?>[] dependenciesClasses;
    private Class<?>[] parametersClasses;
    private Object[] parametersValues;


    public ComponentConfiguration(Class<?> interface_, Class<?> implementation, Class<?>[] dependenciesClasses,
                                  Class<?>[] parametersClasses, Object[] parametersValues) {
        this.interface_ = interface_;
        this.implementation = implementation;
        this.dependenciesClasses = dependenciesClasses;
        this.parametersClasses = parametersClasses;
        this.parametersValues = parametersValues;
    }

    public Class<?> getInterface_() {
        return interface_;
    }

    public Class<?> getImplementation() {
        return implementation;
    }

    public Class<?>[] getDependenciesClasses() {
        return dependenciesClasses;
    }

    public Class<?>[] getParametersClasses() {
        return parametersClasses;
    }

    public Object[] getParametersValues() {
        return parametersValues;
    }
}
