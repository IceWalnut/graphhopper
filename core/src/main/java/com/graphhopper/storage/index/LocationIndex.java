/*
 *  Licensed to GraphHopper GmbH under one or more contributor
 *  license agreements. See the NOTICE file distributed with this work for
 *  additional information regarding copyright ownership.
 *
 *  GraphHopper GmbH licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except in
 *  compliance with the License. You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.graphhopper.storage.index;

import com.carrotsearch.hppc.IntHashSet;
import com.graphhopper.routing.util.EdgeFilter;
import com.graphhopper.storage.Storable;
import com.graphhopper.util.EdgeExplorer;
import com.graphhopper.util.EdgeIterator;
import com.graphhopper.util.EdgeIteratorState;
import com.graphhopper.util.shapes.BBox;

/**
 * Provides a way to map real world data "lat,lon" to internal ids/indices of a memory efficient graph
 * - often just implemented as an array.
 * <p>
 * The implementations of findID needs to be thread safe!
 * <p>
 *
 * @author Peter Karich
 */
public interface LocationIndex extends Storable<LocationIndex> {

    /**
     * This method returns the closest Snap for the specified location (lat, lon) and only if
     * the filter accepts the edge as valid candidate (e.g. filtering away car-only results for bike
     * search)
     * <p>
     *
     * @param edgeFilter if a graph supports multiple vehicles we have to make sure that the entry
     *                   node into the graph is accessible from a selected vehicle. E.g. if you have a FOOT-query do:
     *                   <pre>DefaultEdgeFilter.allEdges(footFlagEncoder);</pre>
     * @return An object containing the closest node and edge for the specified location. The node id
     * has at least one edge which is accepted from the specified edgeFilter. If nothing is found
     * the method Snap.isValid will return false.
     */
    Snap findClosest(double lat, double lon, EdgeFilter edgeFilter);

    /**
     * This method explores the LocationIndex with the specified Visitor. It visits only the stored nodes (and only once)
     * and limited by the queryBBox. Note that for every edge only one node has to be stored and to get all nodes
     * (or edges) you should better use EdgeVisitor. Also (a few) more nodes slightly outside of queryBBox could be
     * returned that you can avoid via doing an explicit BBox check of the coordinates.
     */
    void query(BBox queryBBox, Visitor function);

    /**
     * This interface allows to visit edges stored in the LocationIndex.
     */
    abstract class Visitor {
        public boolean isTileInfo() {
            return false;
        }

        /**
         * This method is called if isTileInfo returns true.
         */
        public void onTile(BBox bbox, int depth) {
        }

        public abstract void onEdge(int edgeId);
    }

}
