package me.theyinspire.projects.flow.matrix.impl;

import me.theyinspire.projects.flow.matrix.Matrix;
import me.theyinspire.projects.flow.matrix.MatrixCell;

import java.util.Iterator;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public class MatrixCellIterator<E> implements Iterator<MatrixCell<E>> {

    private final Matrix<E> matrix;
    private final int row;
    private int column;

    public MatrixCellIterator(Matrix<E> matrix, int row) {
        this.matrix = matrix;
        this.row = row;
        column = 0;
    }

    @Override
    public boolean hasNext() {
        return column < matrix.getColumns();
    }

    @Override
    public MatrixCell<E> next() {
        return new ImmutableMatrixCell<>(row, column ++, matrix);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
