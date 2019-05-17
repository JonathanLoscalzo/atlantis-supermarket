package com.atlantis.supermarket.core.shared.business;

public interface UseCaseOutput<I extends InputPort, T extends OutputPort> {
    public T handle(I input);
}
