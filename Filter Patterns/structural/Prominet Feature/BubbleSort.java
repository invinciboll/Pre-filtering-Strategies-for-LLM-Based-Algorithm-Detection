package pattern_pool.llmPrefilterPatterns.structural.v2.i0;

import pattern.description.PatternMatchingDescriptionImpl;
import pattern.description.chain.BindingExportConfiguration;
import pattern.description.chain.PatternMatchingDescriptionChain;
import pattern.description.chain.PatternMatchingDescriptionChainBuilder;
import pattern.dsl.marker.DslNode;

import static pattern.dsl.DSL.*;

public class BubbleSort {
        public static final PatternMatchingDescriptionChain CHAIN = PatternMatchingDescriptionChainBuilder
                .create("BubbleSort")
                .defaultExport(new BindingExportConfiguration(true).addAll("method"))
                .add("main", new PatternMatchingDescriptionImpl(getPattern(specification())))
                .build();

        private static DslNode specification() {
            return method().rootBind("m")
                    .body(block()
                            .has(loop() // Outer loop can be while or for loop
                                    .body(block()
                                            .has(loop() // Is forLoop in all examples
                                                    .body(block()
                                                            .has(ite()
                                                                    .condition(binOp().ops(">", "<", "<=", ">="))
                                                                    .then(block().has(varDefOrAss().rhs(arrayRead()))) // Costs 4 TP but cuts almost all FP
                                                            )
                                                    )
                                            )
                                    )
                            )
                    );
        }

}

