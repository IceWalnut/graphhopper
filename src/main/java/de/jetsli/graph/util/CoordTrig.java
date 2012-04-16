/*
 *  Copyright 2012 Peter Karich info@jetsli.de
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package de.jetsli.graph.util;

/**
 * Double precision coordinates without an associated value. To add one - subclass.
 *
 * @author Peter Karich, info@jetsli.de
 */
public class CoordTrig<T> {

    public double lat;
    public double lon;

    public CoordTrig() {
    }

    public CoordTrig(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public void setValue(T t) {
    }

    public T getValue() {
        return null;
    }

    @Override
    public String toString() {
        return lat + "," + lon;
    }
}
