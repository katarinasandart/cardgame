import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;

public class Window {

    private Screen screen;
    private Terminal terminal;

    public Window() {
        terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF16"));
        screen = new Screen(terminal);
        screen.startScreen();
        screen.setCursorPosition(null);
    }

    public Screen getScreen() {
        return screen;
    }

    public int getWidth() {
        return terminal.getTerminalSize().getColumns();
    }
    public int getHeight() {
        return terminal.getTerminalSize().getRows();
    }

}
