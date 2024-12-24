package pattern_pool.llmPrefilterPatterns.combined.v1.i0;

import pattern.description.PatternMatchingDescriptionImpl;
import pattern.description.chain.BindingExportConfiguration;
import pattern.description.chain.PatternMatchingDescriptionChain;
import pattern.description.chain.PatternMatchingDescriptionChainBuilder;
import pattern.dsl.internal.type.Types;
import pattern.dsl.marker.DslNode;
import spoon.Launcher;

import java.lang.reflect.Array;
import java.util.List;
import java.util.regex.Pattern;

import static pattern.dsl.DSL.*;
import static pattern.dsl.DSL.any;

public class BubbleSort {
        public static final PatternMatchingDescriptionChain CHAIN = PatternMatchingDescriptionChainBuilder
                .create("BubbleSort")
                .defaultExport(new BindingExportConfiguration(true).addAll("method"))
                .add("main", new PatternMatchingDescriptionImpl(getPattern(specification())))
                .build();

        private static DslNode specification() {
            var typeFactory = new Launcher().getFactory().Type();
            var typeIntArray = Types.from(typeFactory.createArrayReference(typeFactory.INTEGER_PRIMITIVE)); // Matching INT array loses many TP

            return oneOf()
                        .add(method().rootBind("m")
                                .regexPredicatesOnStringRepresentation(List.of(
                                        Pattern.compile("(?i)bubble"),
                                        Pattern.compile("(?i)sort")
                                ), 1))
                        .add(method().rootBind("m").parameters(parameter().type(typeIntArray)));
        }
}