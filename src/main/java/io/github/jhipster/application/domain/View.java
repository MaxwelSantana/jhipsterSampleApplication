package io.github.jhipster.application.domain;

public class View {
	public static interface OveralView {}
	public static interface DetailView extends OveralView {}
	public static interface ClienteView extends DetailView{}
	public static interface InventarioView extends DetailView{}
}
