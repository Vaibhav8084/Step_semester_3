abstract class Instrument {
    public Instrument() {
    }

    // Concrete base message lets each subclass extend it with super.play().
    public String play() {
        return "Strumming the strings";
    }
}

class StringInstrument extends Instrument {
    public StringInstrument() {
        super();
    }

    @Override
    public String play() {
        return super.play();
    }
}

class Violin extends StringInstrument {
    public Violin() {
        super();
    }

    @Override
    public String play() {
        return super.play() + ", with a bow drawn across four strings";
    }
}

public class Problem3_OrchestraWarmUpRoutine {
    public static void main(String[] args) {
        StringInstrument strings = new StringInstrument();
        Violin violin = new Violin();
        System.out.println(strings.play());
        System.out.println(violin.play());
    }
}
