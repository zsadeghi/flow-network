package me.theyinspire.projects.flow.matrix;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public interface MatrixCell<E> {

    int getRowNumber();

    int getColumnNumber();

    E getValue();

}
