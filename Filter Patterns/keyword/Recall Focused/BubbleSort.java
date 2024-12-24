package pattern_pool.llmPrefilterPatterns.keyword.v3.i6;

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

public class BubbleSort {
        public static final PatternMatchingDescriptionChain CHAIN = PatternMatchingDescriptionChainBuilder
                .create("BubbleSort")
                .defaultExport(new BindingExportConfiguration(true).addAll("method"))
                .add("main", new PatternMatchingDescriptionImpl(getPattern(specification())))
                .build();

        private static DslNode specification() {
                return oneOf()
                        .add(method()
                                .rootBind("m")
                                .regexPredicatesOnStringRepresentation(List.of(
                                        Pattern.compile("(?i)bubble"),
                                        Pattern.compile("(?i)sort")
                                        ), 1))
                        .add(method()
                                .rootBind("m")
                                .regexPredicatesOnStringRepresentation(List.of(
                                        Pattern.compile("(?i)arr"),
                                        Pattern.compile("(?i)temp"),
                                        Pattern.compile("(?i)(?<!\\S)i(?!\\S)"),
                                        Pattern.compile("(?i)(?<!\\S)j(?!\\S)"),
                                        Pattern.compile("(?i)size"),
                                        Pattern.compile("(?i)input"),
                                        Pattern.compile("(?i)list"),
                                        Pattern.compile("(?i)length"),
                                        Pattern.compile("(?i)flag")
                                ), 3));
        }
}
