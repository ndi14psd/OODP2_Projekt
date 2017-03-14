package drawable;

import java.awt.*;

@FunctionalInterface
public interface Drawable {
    void draw(Graphics g);

    default Drawable compose(Drawable drawable) {
        return g -> {
            this.draw(g);
            drawable.draw(g);
        };
    }
}