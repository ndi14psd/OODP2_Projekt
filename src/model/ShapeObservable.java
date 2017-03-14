package model;

import java.util.LinkedList;
import java.util.List;

abstract class ShapeObservable {

    private final List<AddedShapeObserver> addedShapeObservers;
    private final List<RemovedShapeObserver> removedShapeObservers;
    private final List<UpdatedShapeObserver> updatedShapeObservers;

    protected ShapeObservable() {
        addedShapeObservers = new LinkedList<>();
        removedShapeObservers = new LinkedList<>();
        updatedShapeObservers = new LinkedList<>();
    }

    public void addAddedObserver(AddedShapeObserver observer) {
        addedShapeObservers.add(observer);
    }

    public void addRemovedObserver(RemovedShapeObserver observer) {
        removedShapeObservers.add(observer);
    }

    public void addUpdatedObserver(UpdatedShapeObserver observer) {
        updatedShapeObservers.add(observer);
    }

    public void removeAddedObserver(AddedShapeObserver observer) {
        addedShapeObservers.remove(observer);
    }

    public void removeRemovedObserver(RemovedShapeObserver observer) {
        removedShapeObservers.remove(observer);
    }

    public void removeUpdatedObserver(UpdatedShapeObserver observer) {
        updatedShapeObservers.remove(observer);
    }

    protected void notifyShapeAdded(DrawableShape shape) {
        for (AddedShapeObserver addedShapeObserver : addedShapeObservers) {
            addedShapeObserver.updateShapeAdded(shape);
        }
    }

    protected void notifyShapeRemoved(DrawableShape shape) {
        for (RemovedShapeObserver removedShapeObserver : removedShapeObservers) {
            removedShapeObserver.updateShapeRemoved(shape);
        }
    }

    protected void notifyShapeUpdated() {
        for (UpdatedShapeObserver updatedShapeObserver : updatedShapeObservers) {
            updatedShapeObserver.updateShapesUpdated();
        }
    }

}
