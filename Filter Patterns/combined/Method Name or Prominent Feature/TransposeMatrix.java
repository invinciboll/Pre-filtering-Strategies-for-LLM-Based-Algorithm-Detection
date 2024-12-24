package pattern_pool.llmPrefilterPatterns.combined.v2.i0;

import pattern.description.PatternMatchingDescriptionImpl;
import pattern.description.chain.BindingExportConfiguration;
import pattern.description.chain.PatternMatchingDescriptionChain;
import pattern.description.chain.PatternMatchingDescriptionChainBuilder;
import pattern.dsl.internal.type.Types;
import pattern.dsl.marker.DslNode;

import java.lang.reflect.Array;
import java.util.List;
import java.util.regex.Pattern;

import static pattern.dsl.DSL.*;

public class TransposeMatrix {
        public static final PatternMatchingDescriptionChain CHAIN = PatternMatchingDescriptionChainBuilder
                .create("TransposeMatrix")
                .defaultExport(new BindingExportConfiguration(true).addAll("method"))
                .add("main", new PatternMatchingDescriptionImpl(getPattern(specification())))
                .build();

        private static DslNode specification() {
            return oneOf()
                    .add(method()
                            .rootBind("m")
                            .regexPredicatesOnStringRepresentation(List.of(
                                    Pattern.compile("(?i)transpose")
                            ), 1))
                    .add(method().rootBind("m")
                            .body(block()
                                    .hasAnywhere(oneOf()
                                            .add(assignment() // Iterate Row -> Col [i][j]
                                                    .lhs(varWrite().withIndex(varRead().bindTo("j")).target(varRead().withIndex(varRead().bindTo("i"))))
                                                    .anywhereInRhs(varRead().withIndex(varRead().bindTo("i")).target(varRead().withIndex(varRead().bindTo("j")))
                                                ))
                                            .add(assignment() // Iterate Col -> Row [j][i]
                                                    .lhs(varWrite().withIndex(varRead().bindTo("i")).target(varRead().withIndex(varRead().bindTo("j"))))
                                                    .anywhereInRhs(varRead().withIndex(varRead().bindTo("j")).target(varRead().withIndex(varRead().bindTo("i")))
                                                ))
                                    )
                            ));
        }
}
