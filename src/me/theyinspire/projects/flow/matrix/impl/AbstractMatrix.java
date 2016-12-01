package me.theyinspire.projects.flow.matrix.impl;

import me.theyinspire.projects.flow.matrix.Matrix;
import me.theyinspire.projects.flow.matrix.MatrixRow;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public abstract class AbstractMatrix<E> implements Matrix<E> {

    private static final String EMPTY_MATRIX = "(empty matrix)";

    @Override
    public E get(int row, int col) {
        checkIndices(row, col);
        return read(row, col);
    }

    protected abstract E read(int row, int col);

    @Override
    public void set(int row, int col, E value) {
        checkIndices(row, col);
        write(row, col, value);
    }

    protected abstract void write(int row, int col, E value);

    protected void checkIndices(int row, int col) {
        if (row < 0 || row >= getRows()) {
            throw new IndexOutOfBoundsException("Row index out of bounds(0," + (getRows() - 1) + "): " + row);
        }
        if (col < 0 || col >= getColumns()) {
            throw new IndexOutOfBoundsException("Columns index out of bounds(0," + (getColumns() - 1) + "): " + col);
        }
    }

    @Override
    public Iterator<MatrixRow<E>> iterator() {
        return new MatrixRowIterator<>(this);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (MatrixRow<E> row : this) {
            builder.append(row).append("\n");
        }
        if (builder.length() == 0) {
            return EMPTY_MATRIX;
        }
        return builder.substring(0, builder.length() - 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Matrix<?>)) {
            return false;
        }
        final Matrix that = (Matrix) obj;
        if (that.getColumns() != this.getColumns() || that.getRows() != this.getRows()) {
            return false;
        }
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                if (!Objects.equals(this.get(i, j), that.get(i, j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
