package co.develhope.dependencyinjection.components;

import org.springframework.stereotype.Component;

@Component
public class MyComponent {

    private String myComponentName;

    //nessun auto-wired, componenet viene creato a inizializzazione con costruttore vuoto!
    public MyComponent(){
        System.out.println("MyComponent constructor has been called");
        this.myComponentName = "Karl Leeds";
    }

    public String getMyComponentName(){
        System.out.println("MyComponent.getMyComponentName() has been called");
        return myComponentName;
    }

}
