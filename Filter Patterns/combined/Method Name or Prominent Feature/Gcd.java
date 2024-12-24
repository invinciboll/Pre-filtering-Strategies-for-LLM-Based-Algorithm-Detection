package pattern_pool.llmPrefilterPatterns.combined.v2.i0;

import pattern.description.PatternMatchingDescriptionImpl;
import pattern.description.chain.BindingExportConfiguration;
import pattern.description.chain.PatternMatchingDescriptionChain;
import pattern.description.chain.PatternMatchingDescriptionChainBuilder;
import pattern.dsl.marker.DslNode;

import java.util.List;
import java.util.regex.Pattern;

import static pattern.dsl.DSL.*;

public class Gcd {
        public static final PatternMatchingDescriptionChain CHAIN = PatternMatchingDescriptionChainBuilder
                .create("Gcd")
                .defaultExport(new BindingExportConfiguration(true).addAll("method"))
                .add("main", new PatternMatchingDescriptionImpl(getPattern(specification())))
                .build();

        private static DslNode specification() {
            return oneOf()
                    .add(method()
                            .rootBind("m")
                            .regexPredicatesOnStringRepresentation(List.of(
                                    Pattern.compile("(?i)gcd"),
                                    Pattern.compile("(?i)greatest")
                            ), 1))
                    .add(method().rootBind("m")//.hasParameters(parameter().bindTo("param1"),parameter().bindTo("param2"));
                            .body(block().has(oneOf()
                                    // Recursive impl Matches GCD1
                                    .add(returns(call().bindTo("m").hasArguments(varRead(), binOp().ops("%"))))
                                    // Bruteforce impl Matches GCD2
                                    .add(forLoop()
                                            .init(varDef().bindTo("i"))
                                            .body(block()
                                                    .has(ite().condition(
                                                            binOp().ops("&&")
                                                                    .lhs(binOp().ops("==").anywhereInLhs(varRead().bindTo("i")))
                                                                    .rhs(binOp().ops("==").anywhereInLhs(varRead().bindTo("i")))
                                                    ))))
                                    // matches GCD3
                                    .add(loop()
                                            .body(block()
                                                    .has(varDefOrAss().rhs(binOp().ops("/")))
                                                    .has(varDefOrAss().rhs(binOp().ops("-")))))
                                    // Matches GCD4
                                    .add(loop()
                                            .body(block()
                                                    .has(ite()
                                                            .condition(binOp().ops(">", "<"))
                                                            .then(block().has(varDefOrAss().rhs(binOp().ops("-"))))
                                                            .otherwise(block().has(varDefOrAss().rhs(binOp().ops("-"))))
                    ))))));
        }
}
