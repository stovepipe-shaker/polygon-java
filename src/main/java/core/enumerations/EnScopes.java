package core.enumerations;

public enum EnScopes {

    SQUARE_BRACKETS("[", "]"),
    ROUND_BRACKETS("(", ")"),
    ANGLE_BRACKETS("<", ">"),
    BRACES("{", "}");

    private String scopeStart, scopeEnd;

    EnScopes(String scopeStart, String scopeEnd) {
        this.scopeStart = scopeStart;
        this.scopeEnd = scopeEnd;
    }

    public String getScopeStart() {
        return scopeStart;
    }

    public String getScopeEnd() {
        return scopeEnd;
    }

}
