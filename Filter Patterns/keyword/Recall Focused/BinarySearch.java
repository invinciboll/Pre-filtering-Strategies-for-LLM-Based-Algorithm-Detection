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

public class BinarySearch {
        public static final PatternMatchingDescriptionChain CHAIN = PatternMatchingDescriptionChainBuilder
                .create("BinarySearch")
                .defaultExport(new BindingExportConfiguration(true).addAll("method"))
                .add("main", new PatternMatchingDescriptionImpl(getPattern(specification())))
                .build();

        private static DslNode specification() {
                return oneOf()
                        .add(method()
                                .rootBind("m")
                                .regexPredicatesOnStringRepresentation(List.of(
                                        Pattern.compile("(?i)binary"),
                                        Pattern.compile("(?i)search")
                                        ), 1))
                        .add(method()
                                .rootBind("m")
                                .regexPredicatesOnStringRepresentation(List.of(
                                        Pattern.compile("(?i)arr"),
                                        Pattern.compile("(?i)lo"),
                                        Pattern.compile("(?i)hi"),
                                        Pattern.compile("(?i)key"),
                                        Pattern.compile("(?i)min"),
                                        Pattern.compile("(?i)max"),
                                        Pattern.compile("(?i)first"),
                                        Pattern.compile("(?i)last"),
                                        Pattern.compile("(?i)mid"),
                                        Pattern.compile("(?i)sorted"),
                                        Pattern.compile("(?i)start"),
                                        Pattern.compile("(?i)end")
                                        ), 2));
        }
}