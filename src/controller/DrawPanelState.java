package controller;

interface DrawPanelState {

    void leftMouseButtonPressed(int x, int y);

    void rightMouseButtonPressed(int x, int y);
    
    void leftMouseButtonReleased(int x, int y);

    void mouseDragged(int x, int y);

    void leftMouseButtonClicked(int x, int y);

    void mouseWheelScrolled(int x, int y);

    void mouseMoved(int x, int y);
    
    void stateModifierKeyPressed();

    void stateModifierKeyReleased();


}
