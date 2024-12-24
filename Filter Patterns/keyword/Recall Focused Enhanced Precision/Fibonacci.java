package pattern_pool.llmPrefilterPatterns.keyword.v4.i8;

import java.util.List;
import java.util.regex.Pattern;

import pattern.description.PatternMatchingDescriptionImpl;
import pattern.description.chain.BindingExportConfiguration;
import pattern.description.chain.PatternMatchingDescriptionChain;
import pattern.description.chain.PatternMatchingDescriptionChainBuilder;
import pattern.dsl.marker.DslNode;
import static pattern.dsl.DSL.method;
import static pattern.dsl.DSL.oneOf;
import static pattern.dsl.DSL.getPattern;

public class Fibonacci {
        public static final PatternMatchingDescriptionChain CHAIN = PatternMatchingDescriptionChainBuilder
                .create("Fibonacci")
                .defaultExport(new BindingExportConfiguration(true).addAll("method"))
                .add("main", new PatternMatchingDescriptionImpl(getPattern(specification())))
                .build();

        private static DslNode specification() {
                return oneOf()
                        .add(method()
                                .rootBind("m")
                                .regexPredicatesOnStringRepresentation(List.of(
                                        Pattern.compile("(?i)fib")
                                        ), 1))
                        .add(method()
                                .rootBind("m")
                                .regexPredicatesOnStringRepresentation(List.of(
                                        Pattern.compile("(?i)temp"),
                                        Pattern.compile("(?i)prev"),
                                        Pattern.compile("(?i)n.*1"),
                                        Pattern.compile("(?i)n.*2")
                                        ), 3));
        }
}
