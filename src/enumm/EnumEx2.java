package enumm;

// an enum example
enum Signal {
    // will call enum constructor with one String argument
    RED("STOP"), GREEN("GO"), ORANGE("SLOW DOWN"); // members with their own different custom values

    // for getting values
    private String action;
    public String getAction() {
        return this.action;
    }

    // enum class's object can't be create explicitly,
    // enum constructor cannot be public or protected
    private Signal(String action) {
        this.action = action;
    }
}

public class EnumEx2 {
    public static void main(String[] args) {
        // print name of each enum and there action
        Signal[] signals = Signal.values(); // get all values inside enum

        for (Signal s : signals) {
            System.out.println("name : " + s.name() + " action: " + s.getAction());
        }
    }
}
