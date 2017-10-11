package com.example.batrakov.fragmenttask;

/**
 * Base interface for View objects.
 * Created by batrakov on 06.10.17.
 * @param <T> target presenter type.
 */
public interface BaseView<T> {
    /**
     * Set presenter.
     * @param aPresenter target presenter.
     */
    void setPresenter(T aPresenter);
}
