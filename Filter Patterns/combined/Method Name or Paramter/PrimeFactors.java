package pattern_pool.llmPrefilterPatterns.combined.v1.i0;

import pattern.description.PatternMatchingDescriptionImpl;
import pattern.description.chain.BindingExportConfiguration;
import pattern.description.chain.PatternMatchingDescriptionChain;
import pattern.description.chain.PatternMatchingDescriptionChainBuilder;
import pattern.dsl.marker.DslNode;

import java.util.List;
import java.util.regex.Pattern;

import static pattern.dsl.DSL.*;

public class PrimeFactors {
        public static final PatternMatchingDescriptionChain CHAIN = PatternMatchingDescriptionChainBuilder
                .create("PrimeFactors")
                .defaultExport(new BindingExportConfiguration(true).addAll("method"))
                .add("main", new PatternMatchingDescriptionImpl(getPattern(specification())))
                .build();

        private static DslNode specification() {
            return oneOf()
                    .add(method()
                            .rootBind("m")
                            .regexPredicatesOnStringRepresentation(List.of(
                                    Pattern.compile("(?i)prime"),
                                    Pattern.compile("(?i)factor")
                            ), 1))
                    .add(oneOf()
                            .add(method().rootBind("m").parameters(parameter().type(type().INT)))
                            .add(method().rootBind("m").parameters(parameter().type(type().LONG)))
                    );
        }
}
