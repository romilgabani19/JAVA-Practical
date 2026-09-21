public class RemoteControlDemo {

    // Functional interface for controlling whether a device may switch on
    @FunctionalInterface
    interface SwitchPermission {
        boolean maySwitchOn(Switchable device, int hour);
    }

    // Interface for switchable devices
    interface Switchable {
        void on();
        void off();

        // Default method
        default void toggle() {
            off();
            on();
        }
    }

    // Fan implementation
    static class Fan implements Switchable {
        @Override
        public void on() {
            System.out.println("Fan is ON");
        }

        @Override
        public void off() {
            System.out.println("Fan is OFF");
        }
    }

    // Light implementation
    static class Light implements Switchable {
        @Override
        public void on() {
            System.out.println("Light is ON");
        }

        @Override
        public void off() {
            System.out.println("Light is OFF");
        }
    }

    public static void main(String[] args) {

        Fan fan = new Fan();
        Light light = new Light();

        // Array of Switchable devices
        Switchable[] devices = { fan, light };

        // Toggle each device
        System.out.println("Toggling devices:");
        for (Switchable device : devices) {
            device.toggle();
        }

        // Functional interface using anonymous class
        SwitchPermission anonymousPermission = new SwitchPermission() {
            @Override
            public boolean maySwitchOn(Switchable device, int hour) {
                return hour >= 18 && hour <= 22;
            }
        };

        // Functional interface using lambda
        SwitchPermission lambdaPermission =
                (device, hour) -> hour >= 6 && hour <= 10;

        int hour = 20;

        System.out.println("\nUsing anonymous class:");
        System.out.println(
                "Can Fan switch on at " + hour + ":00? "
                + anonymousPermission.maySwitchOn(fan, hour)
        );

        System.out.println("\nUsing lambda:");
        System.out.println(
                "Can Light switch on at " + hour + ":00? "
                + lambdaPermission.maySwitchOn(light, hour)
        );
    }
}
