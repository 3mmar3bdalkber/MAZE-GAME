public enum Sound {
    CLICK("resources/sounds/click.wav"),
    MOVE("resources/sounds/move.wav"),
    WIN("resources/sounds/win.wav"),
    LOSS("resources/sounds/loss.wav"),
    ERROR("resources/sounds/error.wav"),
    SELECT("resources/sounds/select.wav"),
    START("resources/sounds/Start.wav");

    public final String path;
    Sound(String path) {
        this.path = path;
    }
}
