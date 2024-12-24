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

public class TransposeMatrix {
        public static final PatternMatchingDescriptionChain CHAIN = PatternMatchingDescriptionChainBuilder
                .create("TransposeMatrix")
                .defaultExport(new BindingExportConfiguration(true).addAll("method"))
                .add("main", new PatternMatchingDescriptionImpl(getPattern(specification())))
                .build();

        private static DslNode specification() {
                return oneOf()
                        .add(method()
                                .rootBind("m")
                                .regexPredicatesOnStringRepresentation(List.of(
                                        Pattern.compile("(?i)trans"),
                                        Pattern.compile("(?i)temp"),
                                        Pattern.compile("(?i)row"),
                                        Pattern.compile("(?i)col"),
                                        Pattern.compile("(?i)mat"),
                                        Pattern.compile("(?i)(?<!\\S)m(?!\\S)"),
                                        Pattern.compile("(?i)(?<!\\S)n(?!\\S)"),
                                        Pattern.compile("(?i)(?<!\\S)i(?!\\S)"),
                                        Pattern.compile("(?i)(?<!\\S)j(?!\\S)"),
                                        Pattern.compile("(?i)(?<!\\S)R(?!\\S)"),
                                        Pattern.compile("(?i)(?<!\\S)C(?!\\S)")
                                        ), 3));
        }
}
