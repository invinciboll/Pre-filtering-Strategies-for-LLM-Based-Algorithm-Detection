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
                        .add(method()
                                .rootBind("m")
                                .regexPredicatesOnStringRepresentation(List.of(
                                        Pattern.compile("(?i)temp"),
                                        // Matches two different chars that are surronded by white space with arbitrary content between them
                                        Pattern.compile("(?i)(?<!\\S)([abxyqrc])(?:[,\\(\\)]?)(?!\\S).*?(?<!\\S)(?!\\1)([abxyqrc])(?:[,\\(\\)]?)(?!\\S)"),
                                        Pattern.compile("(?i)num")
                                        ), 2));
        }
}