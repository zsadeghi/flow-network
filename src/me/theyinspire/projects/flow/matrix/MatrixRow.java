package me.theyinspire.projects.flow.matrix;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public interface MatrixRow<E> extends Iterable<MatrixCell<E>> {

    int getRowNumber();

}
