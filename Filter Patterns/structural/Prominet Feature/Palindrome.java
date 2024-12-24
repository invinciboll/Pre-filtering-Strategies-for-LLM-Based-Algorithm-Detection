package pattern_pool.llmPrefilterPatterns.structural.v2.i0;

import pattern.description.PatternMatchingDescriptionImpl;
import pattern.description.chain.BindingExportConfiguration;
import pattern.description.chain.PatternMatchingDescriptionChain;
import pattern.description.chain.PatternMatchingDescriptionChainBuilder;
import pattern.dsl.marker.DslNode;

import static pattern.dsl.DSL.*;

public class Palindrome {
        public static final PatternMatchingDescriptionChain CHAIN = PatternMatchingDescriptionChainBuilder
                .create("Palindrome")
                .defaultExport(new BindingExportConfiguration(true).addAll("method"))
                .add("main", new PatternMatchingDescriptionImpl(getPattern(specification())))
                .build();

        private static DslNode specification() {
            return method().rootBind("m").hasParameters(parameter())
                    .body(oneOf()
                            .add(block()
                                    .has(loop()
                                            .body(block()
                                                    .has(ite()
                                                            .condition(binOp().ops("!="))
                                                            .then(block().has(returns(literal().value(false)))))
                                            ))
                                    .hasAnywhere(returns(literal().value(true))))
                            .add(block()
                                    .has(forLoop()
                                            .body(block().has(varDefOrAss().rhs(binOp().ops("+")))))
                                    .has(ite()
                                            .then(block().has(returns(literal().value(true))))
                                            .otherwise(block().has(returns(literal().value(false))))))
                            .add(block()
                                    .has(ite()
                                            .condition(call())
                                            .then(block().has(returns(literal().value(true))))
                                            .otherwise(block().has(returns(literal().value(false))))))
                    );
        }

}

