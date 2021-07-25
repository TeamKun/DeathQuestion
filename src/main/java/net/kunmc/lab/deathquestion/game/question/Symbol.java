package net.kunmc.lab.deathquestion.game.question;

public enum Symbol {
    A,
    B;

    public static Symbol getSymbol(String name) {
        if (name.equalsIgnoreCase("a")) {
            return Symbol.A;
        }
        if (name.equalsIgnoreCase("b")) {
            return Symbol.B;
        }

        return null;
    }
}
