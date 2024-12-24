package pattern_pool.llmPrefilterPatterns.structural.v2.i0;

import pattern.description.PatternMatchingDescriptionImpl;
import pattern.description.chain.BindingExportConfiguration;
import pattern.description.chain.PatternMatchingDescriptionChain;
import pattern.description.chain.PatternMatchingDescriptionChainBuilder;
import pattern.dsl.marker.DslNode;

import static pattern.dsl.DSL.*;

public class BinarySearch {
        public static final PatternMatchingDescriptionChain CHAIN = PatternMatchingDescriptionChainBuilder
                .create("BinarySearch")
                .defaultExport(new BindingExportConfiguration(true).addAll("method"))
                .add("main", new PatternMatchingDescriptionImpl(getPattern(specification())))
                .build();

        private static DslNode midPointCalculation(){
            return oneOf()
                    .add(varDefOrAss().anywhereInRhs(binOp()
                            .ops("/")
                            .rhs(literal().value(2)))
                    );
        }

        private static DslNode recursiveBody() {
            return block()
                    .has(midPointCalculation())
                    .has(ite()
                            .then(block().has(returns(call().bindTo("m"))))
                );
        }

        private static DslNode iterativeBody() {
            return block()
                    .has(loop()
                            .body(block()
                                    .has(midPointCalculation())
                            )
                    );
        }

        private static DslNode specification() {
            return method().rootBind("m").hasParameters(parameter())
                    .body(oneOf()
                            .add(recursiveBody())
                            .add(iterativeBody())
                    );
        }

}

