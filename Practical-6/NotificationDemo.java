public class NotificationDemo {

    // Functional interface
    @FunctionalInterface
    interface Notifier {
        void send(String message);
    }

    // Marker interface
    interface Urgent {
    }

    // Wrapper class to associate a notifier with urgency
    static class NotificationSender {
        Notifier notifier;
        boolean urgent;

        NotificationSender(Notifier notifier, boolean urgent) {
            this.notifier = notifier;
            this.urgent = urgent;
        }
    }

    public static void main(String[] args) {

        // Email sender as a lambda
        Notifier email = message ->
                System.out.println("Email: " + message);

        // SMS sender as a lambda
        Notifier sms = message ->
                System.out.println("SMS: " + message);

        /*
         -> Hold the senders in an array.
         -> Email is marked urgent, SMS is not.
         */
        NotificationSender[] senders = {
            new NotificationSender(email, true),
            new NotificationSender(sms, false)
        };

        String message = "Server maintenance at 10 PM.";

        // Broadcast the message  
        for (NotificationSender sender : senders) {
            sender.notifier.send(message);

            // Urgent senders send twice
            if (sender.urgent) {
                sender.notifier.send(message);
            }
        }
    }
}
