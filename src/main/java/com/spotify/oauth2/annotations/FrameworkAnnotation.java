package com.spotify.oauth2.annotations;

import com.spotify.oauth2.enums.Author;
import com.spotify.oauth2.enums.Category;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({METHOD})
@Documented
public @interface FrameworkAnnotation {

    Author author() default Author.ABHISHEK;

    Category[] category() default Category.REGRESSION;

}
