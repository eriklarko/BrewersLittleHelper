package se.angstroms.blh.anders.view.util;

import javafx.fxml.JavaFXBuilderFactory;
import javafx.util.Builder;
import javafx.util.BuilderFactory;
import se.angstroms.blh.anders.view.recipe.details.data.value.ValuePresenter;

/**
 *
 * @author eriklark
 */
public class AndersBuilderFactory implements BuilderFactory {

	private final BuilderFactory fallBack;

	public AndersBuilderFactory() {
		this(new JavaFXBuilderFactory());
	}

	public AndersBuilderFactory(BuilderFactory fallBack) {
		this.fallBack = fallBack;
	}

	@Override
	public Builder<?> getBuilder(Class<?> type) {
		if (type == ValuePresenter.class) {
			return new ValuePresenter.ValuePresenterBuilder();
		}

		return fallBack.getBuilder(type);
	}

}
