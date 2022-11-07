package dependency_injection_classes;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class DependencyInjectionContainer {

    private final Map<String, Object> PRIMITIVES_DEFAULT_VALUES = new HashMap<>() {{
        put("boolean", true);
        put("byte", 0);
        put("char", 0);
        put("double", 0);
        put("float", 0);
        put("int", 0);
        put("long", 0);
        put("short", 0);
    }};

    private final Map<String, Class<?>> PRIMITIVES_CORRESPONDING_VALUES = new HashMap<>() {{
        put("boolean", boolean.class);
        put("byte", byte.class);
        put("char", char.class);
        put("double", double.class);
        put("float", float.class);
        put("int", int.class);
        put("long", long.class);
        put("short", short.class);
    }};

    private final Map<String, ComponentConfiguration> componentConfigurations = new HashMap<>();

    public void configure() {
        try {
            String content = Files.readString(Path.of("src/main/java/config/config.json"));
            JSONObject jsonObject = new JSONObject(content);
            JSONArray componentConfigurationArray = jsonObject.getJSONArray("services");

            for (int i = 0; i < componentConfigurationArray.length(); i++) {

                JSONObject componentConfigurationObject = componentConfigurationArray.getJSONObject(i);

                //interface's class
                String interface_ = componentConfigurationObject.getString("interface");
                Class<?> interfaceClass = Class.forName(interface_);

                //implementation's class
                String implementation = componentConfigurationObject.getString("implementation");
                Class<?> implementationClass = Class.forName(implementation);

                //dependencies classes
                JSONArray dependenciesClassesArray = componentConfigurationObject.getJSONArray("dependenciesTypes");
                Class<?>[] dependenciesClasses = new Class[dependenciesClassesArray.length()];
                for (int j = 0; j < dependenciesClassesArray.length(); j++) {
                    dependenciesClasses[j] = Class.forName(dependenciesClassesArray.getString(j));
                }

                //parameters classes and values
                JSONArray parametersClassesArray = componentConfigurationObject.getJSONArray("constructorParameterTypes");
                int length = parametersClassesArray.length();

                Class<?>[] parametersClasses = new Class[length];
                Object[] parametersValues = new Object[length];
                for (int j = 0; j < length; j++) {

                    String parameterType = parametersClassesArray.getString(j);

                    if (PRIMITIVES_DEFAULT_VALUES.get(parameterType) != null) {
                        parametersClasses[j] = PRIMITIVES_CORRESPONDING_VALUES.get(parameterType);
                        parametersValues[j] = PRIMITIVES_DEFAULT_VALUES.get(parameterType);
                    } else {
                        parametersClasses[j] = Class.forName(parameterType);
                        parametersValues[j] = null;
                    }
                }

                ComponentConfiguration componentConfiguration = new ComponentConfiguration(
                        interfaceClass,
                        implementationClass,
                        dependenciesClasses,
                        parametersClasses,
                        parametersValues
                );
                componentConfigurations.put(interfaceClass.getName(), componentConfiguration);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public <T> T getComponentImplementation(Type type) {

        ComponentConfiguration componentConfiguration = componentConfigurations.get(type.getTypeName());
        try {
            Class<?> implementation = componentConfiguration.getImplementation();
            Class<?> interface_ = componentConfiguration.getInterface_();
            Class<?>[] dependenciesClasses = componentConfiguration.getDependenciesClasses();
            Class<?>[] parametersClasses = componentConfiguration.getParametersClasses();
            Object[] parametersValues = componentConfiguration.getParametersValues();

            for (int i = 0; i < parametersClasses.length; i++) {
                for (Class<?> dependencyClass : dependenciesClasses) {
                    if (parametersClasses[i].getName().equals(dependencyClass.getName())) {
                        parametersValues[i] = getComponentImplementation(dependencyClass);
                    }
                }
            }

            Constructor<?> constructor = implementation.getDeclaredConstructor(parametersClasses);
            constructor.setAccessible(true);
            T componentImplementation = (T) constructor.newInstance(parametersValues);

            return componentImplementation;
        } catch (InvocationTargetException | InstantiationException | NoSuchMethodException | IllegalAccessException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
