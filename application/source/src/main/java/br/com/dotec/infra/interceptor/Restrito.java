package br.com.dotec.infra.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import br.com.dotec.model.TipoDeUsuario;


/**
 * The easiest way to control intercepted methods is through annotations
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Restrito {
	TipoDeUsuario[] value() default {};
}



