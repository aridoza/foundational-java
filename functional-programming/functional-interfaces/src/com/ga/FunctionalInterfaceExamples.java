package com.ga;

public class FunctionalInterfaceExamples {

    @FunctionalInterface
    private static interface VoidFunctionalInterface {
        public void greet();
    }

    @FunctionalInterface
    private static interface GreetByName {
        public void greet(String name);
    }

    @FunctionalInterface
    private static interface SquareMe {
        public Integer square(Integer input);
    }

    @FunctionalInterface
    private static interface CreateFullName {
        public String fullName(String firstName, String middleName, String lastName);
    }

    public static void main(String[] args) {


        //Use the VoidFunctionalInterface interface to say Hello.
        VoidFunctionalInterface greetTheWorld = () -> System.out.println("Hello World!");
        greetTheWorld.greet();

        //Anonymous class example of the above.
        VoidFunctionalInterface voidFunctionalInterfaceImpl = new VoidFunctionalInterface() {
            @Override
            public void greet() {
                System.out.println("Hello World!");
            }
        };
        voidFunctionalInterfaceImpl.greet();


        //Use the GreetByName to greet someone by name.
        GreetByName greetSomeone =
                (name -> System.out.println("Hello " + name + "!  How are you doing today?"));
        greetSomeone.greet("Mark");

        //Anonymous class example of the above.
        GreetByName oneInputAndVoidReturnFunctionalInterfaceImpl =
                new GreetByName() {
                    @Override
                    public void greet(String name) {
                        System.out.println("Hello " + name + "!  How are you doing today?");
                    }
                };
        oneInputAndVoidReturnFunctionalInterfaceImpl.greet("Mark");


        //Use the SquareMe interface to find the square of a given number.
        SquareMe squareMe = (number) -> number * number;
        System.out.println("The square of 11 is " + squareMe.square(11));


        //Use the CreateFullName interface to greet a person by their full name.
        CreateFullName whatsMyName =
                ((firstName, middleName, lastName) -> firstName + " " + middleName + " " + lastName);

        String fullName = whatsMyName.fullName("Michael", "Jeffrey", "Jordan");
        System.out.println("Hello " + fullName + ", it's a pleasure to meet you.");

    }
}
