package de.codeshelf.consoleui.util;

/**
 * Classe que testa a validação.
 * 
 * @author jcruz
 *
 */
@FunctionalInterface
public interface Validator {

	public Object test(String lineInput);
}
