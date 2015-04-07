package com.rameezvirji.uofsnavigator;

/**
 * Created by Rameez on 2015-04-06.
 */
public class LocationMap {
    public int ns; // north (1) or south (0)
    public int ew; // east (1) or west (0)
    public int f; // floor

    public LocationMap(int nsv, int ewv, int fv) {
        this.ns = nsv;
        this.ew = ewv;
        this.f = fv;
    }

    public int getNS() {
        return this.ns;
    }

    public int getEW() {
        return this.ew;
    }

    public int getF() {
        return this.f;
    }

    public String toString() {
        String result ="";
        if (ns > 0) {
            result += "North";
        } else {
            result += "South";
        }
        if (ew > 0) {
            result += "east ";
        } else {
            result += "west ";
        }
        if (f == 1) {
            result += "1st floor";
        } else {
            result += "2nd floor";
        }
        return result;
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