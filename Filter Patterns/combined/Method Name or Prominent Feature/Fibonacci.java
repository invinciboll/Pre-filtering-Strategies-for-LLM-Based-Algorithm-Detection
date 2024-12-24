package pattern_pool.llmPrefilterPatterns.combined.v2.i0;

import pattern.description.PatternMatchingDescriptionImpl;
import pattern.description.chain.BindingExportConfiguration;
import pattern.description.chain.PatternMatchingDescriptionChain;
import pattern.description.chain.PatternMatchingDescriptionChainBuilder;
import pattern.dsl.marker.DslNode;

import java.util.List;
import java.util.regex.Pattern;

import static pattern.dsl.DSL.*;

public class Fibonacci {
        public static final PatternMatchingDescriptionChain CHAIN = PatternMatchingDescriptionChainBuilder
                .create("Fibonacci")
                .defaultExport(new BindingExportConfiguration(true).addAll("method"))
                .add("main", new PatternMatchingDescriptionImpl(getPattern(specification())))
                .build();

        private static DslNode earlyReturn() {
            return  block().has(returns(oneOf()
                    .add(literal()) // Usually int 1
                    .add(varRead().bindTo("n")))
            );
        }

        private static DslNode recursiveCall() {
            // return call(-op) + call(-op)
            // waive literals, so order of calls doesnt matter
            return returns(binOp()
                    .ops("+")
                    .lhs(call().bindTo("m").hasArguments(binOp().ops("-").lhs(varRead().bindTo("n"))))
                    .rhs(call().bindTo("m").hasArguments(binOp().ops("-").lhs(varRead().bindTo("n"))))
            );
        }

        private static DslNode specification() {
            return oneOf()
                    .add(method()
                            .rootBind("m")
                            .regexPredicatesOnStringRepresentation(List.of(
                                    Pattern.compile("(?i)fib")
                            ), 1))
                    .add(method().rootBind("m")
                            .hasParameters(parameter().bindTo("n"))
                            .body(oneOf() // Separate by if condition types
                                    .add(block()
                                            .has(oneOf().add(ite()
                                                            .condition(binOp().ops("<", "<=", "==", "||").anywhereInLhs(varRead().bindTo("n")))
                                                            .anywhereInThen(earlyReturn())
                                                            .otherwise(block().hasAnywhere(recursiveCall())) // Help
                                                    )
                                            )
                                    )
                                    .add(block()
                                            .has(oneOf().add(ite()
                                                    .condition(binOp().ops("<", "<=", "==", "||").anywhereInLhs(varRead().bindTo("n")))
                                                    .anywhereInThen(earlyReturn())))
                                            .hasAnywhere(recursiveCall()))
                            )
                    );
        }
}
