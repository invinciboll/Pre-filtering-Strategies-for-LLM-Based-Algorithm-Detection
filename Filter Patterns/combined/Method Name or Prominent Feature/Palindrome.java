package pattern_pool.llmPrefilterPatterns.combined.v2.i0;

import pattern.description.PatternMatchingDescriptionImpl;
import pattern.description.chain.BindingExportConfiguration;
import pattern.description.chain.PatternMatchingDescriptionChain;
import pattern.description.chain.PatternMatchingDescriptionChainBuilder;
import pattern.dsl.marker.DslNode;

import java.util.List;
import java.util.regex.Pattern;

import static pattern.dsl.DSL.*;

public class Palindrome {
        public static final PatternMatchingDescriptionChain CHAIN = PatternMatchingDescriptionChainBuilder
                .create("Palindrome")
                .defaultExport(new BindingExportConfiguration(true).addAll("method"))
                .add("main", new PatternMatchingDescriptionImpl(getPattern(specification())))
                .build();

        private static DslNode specification() {
                return oneOf()
                        .add(method()
                                .rootBind("m")
                                .regexPredicatesOnStringRepresentation(List.of(
                                        Pattern.compile("(?i)palindrome")
                                ), 1))
                        .add(method().rootBind("m").hasParameters(parameter())
                                .body(oneOf()
                                        .add(block() //Match pali1 and pali3
                                                .has(loop()
                                                        .body(block()
                                                                .has(ite()
                                                                        .condition(binOp().ops("!="))
                                                                        .then(block().has(returns(literal().value(false)))))
                                                        ))
                                                .hasAnywhere(returns(literal().value(true))))
                                        .add(block() //Match pali2
                                                .has(forLoop()
                                                        .body(block().has(varDefOrAss().rhs(binOp().ops("+")))))
                                                .has(ite()
                                                        .then(block().has(returns(literal().value(true))))
                                                        .otherwise(block().has(returns(literal().value(false))))))
                                        .add(block() // Match pali4 try to match s.equals call
                                                .has(ite()
                                                        .condition(call())
                                                        .then(block().has(returns(literal().value(true))))
                                                        .otherwise(block().has(returns(literal().value(false))))))
                                ));
        }
}
