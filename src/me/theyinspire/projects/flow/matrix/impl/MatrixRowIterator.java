package me.theyinspire.projects.flow.matrix.impl;

import me.theyinspire.projects.flow.matrix.Matrix;
import me.theyinspire.projects.flow.matrix.MatrixRow;

import java.util.Iterator;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public class MatrixRowIterator<E> implements Iterator<MatrixRow<E>> {

    private final Matrix<E> matrix;
    private int row;

    public MatrixRowIterator(Matrix<E> matrix) {
        this.matrix = matrix;
        this.row = 0;
    }

    @Override
    public boolean hasNext() {
        return row < matrix.getRows();
    }

    @Override
    public MatrixRow<E> next() {
        return new ImmutableMatrixRow<>(matrix, row ++);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
