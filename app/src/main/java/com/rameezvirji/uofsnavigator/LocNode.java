package com.rameezvirji.uofsnavigator;

/**
 * Created by Rameez on 2015-04-05.
 */
public class LocNode {
    protected float x;
    protected float y;
    protected boolean visible;

    LocNode() {
        x = 0;
        y = 0;
        visible = false;
    }

    LocNode(float xpos, float ypos) {
        x = xpos;
        y = ypos;
        visible = false;
    }

    LocNode(float xpos, float ypos, boolean vis) {
        x = xpos;
        y = ypos;
        visible = vis;
    }

    public void setPos (float xpos, float ypos) {
        x = xpos;
        y = ypos;
    }
}
