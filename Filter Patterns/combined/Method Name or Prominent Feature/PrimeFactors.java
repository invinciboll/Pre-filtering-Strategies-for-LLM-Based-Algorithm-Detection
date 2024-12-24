package pattern_pool.llmPrefilterPatterns.combined.v2.i0;

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
                    .add(method().rootBind("m")
                            .body(block()
                                    .has(loop()
                                            .body(block().has(oneOf()
                                                    .add(loop().condition(binOp().ops("==").lhs(binOp().ops("%"))))
                                                    .add(ite().condition(binOp().ops("==").lhs(binOp().ops("%"))))
                                            ))
                                    )
                            ));
        }
}
