package pattern_pool.llmPrefilterPatterns.structural.v2.i0;

import pattern.description.PatternMatchingDescriptionImpl;
import pattern.description.chain.BindingExportConfiguration;
import pattern.description.chain.PatternMatchingDescriptionChain;
import pattern.description.chain.PatternMatchingDescriptionChainBuilder;
import pattern.dsl.marker.DslNode;

import static pattern.dsl.DSL.*;

public class TransposeMatrix {
        public static final PatternMatchingDescriptionChain CHAIN = PatternMatchingDescriptionChainBuilder
                .create("TransposeMatrix")
                .defaultExport(new BindingExportConfiguration(true).addAll("method"))
                .add("main", new PatternMatchingDescriptionImpl(getPattern(specification())))
                .build();

        private static DslNode specification() {
            return method().rootBind("m")
                    .body(block()
//                            .has(forLoop().init(varDef().bindTo("i")).body(block()
//                                    .has(forLoop().init(varDef().bindTo("j"))
//                                            .body(block()
                                                    .hasAnywhere(oneOf()
                                                            .add(assignment() // Iterate Row -> Col [i][j]
                                                                .lhs(varWrite().withIndex(varRead().bindTo("j")).target(varRead().withIndex(varRead().bindTo("i"))))
                                                                .anywhereInRhs(varRead().withIndex(varRead().bindTo("i")).target(varRead().withIndex(varRead().bindTo("j")))
                                                                ))
                                                            .add(assignment() // Iterate Col -> Row [j][i]
                                                                .lhs(varWrite().withIndex(varRead().bindTo("i")).target(varRead().withIndex(varRead().bindTo("j"))))
                                                                .anywhereInRhs(varRead().withIndex(varRead().bindTo("j")).target(varRead().withIndex(varRead().bindTo("i")))
                                                                ))
//                                            ))
//                                    ))
                            )
                    );
        }
}

