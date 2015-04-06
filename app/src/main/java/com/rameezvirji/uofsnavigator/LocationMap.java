package com.rameezvirji.uofsnavigator;

/**
 * Created by Rameez on 2015-04-06.
 */
public class LocationMap {
    public float x;
    public float y;
    public String name;

    public LocationMap(String namev, float xv, float yv) {
        this.x = xv;
        this.y = yv;
    }
}

/*example of use
      Hashtable table = new Hashtable();
        locations location = new locations();
        location.x = 3f;
        location.y = 3f;
        table.put("Tim Hortons", location);
        location.x = 4f;
        location.y = 1f;
        table.put("dean's office", location);
 */