package me.theyinspire.projects.flow.matrix;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public interface Matrix<E> extends Iterable<MatrixRow<E>> {

    int getRows();

    int getColumns();

    E get(int row, int col);

    void set(int row, int col, E value);

}
