package me.theyinspire.projects.flow.matrix.impl;

import me.theyinspire.projects.flow.matrix.Matrix;
import me.theyinspire.projects.flow.matrix.MatrixCell;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public class ImmutableMatrixCell<E> implements MatrixCell<E> {

    private final int row;
    private final int column;
    private final Matrix<E> matrix;

    public ImmutableMatrixCell(int row, int column, Matrix<E> matrix) {
        this.row = row;
        this.column = column;
        this.matrix = matrix;
    }

    @Override
    public int getRowNumber() {
        return row;
    }

    @Override
    public int getColumnNumber() {
        return column;
    }

    @Override
    public E getValue() {
        return matrix.get(row, column);
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }

}
