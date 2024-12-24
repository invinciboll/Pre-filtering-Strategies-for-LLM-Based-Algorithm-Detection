package pattern_pool.llmPrefilterPatterns.combined.v2.i0;

import pattern.description.PatternMatchingDescriptionImpl;
import pattern.description.chain.BindingExportConfiguration;
import pattern.description.chain.PatternMatchingDescriptionChain;
import pattern.description.chain.PatternMatchingDescriptionChainBuilder;
import pattern.dsl.internal.type.Types;
import pattern.dsl.marker.DslNode;

import java.lang.reflect.Array;
import java.util.List;
import java.util.regex.Pattern;

import static pattern.dsl.DSL.*;

public class BubbleSort {
        public static final PatternMatchingDescriptionChain CHAIN = PatternMatchingDescriptionChainBuilder
                .create("BubbleSort")
                .defaultExport(new BindingExportConfiguration(true).addAll("method"))
                .add("main", new PatternMatchingDescriptionImpl(getPattern(specification())))
                .build();

        private static DslNode specification() {
                return oneOf()
                        .add(method().rootBind("m")
                                .regexPredicatesOnStringRepresentation(List.of(
                                        Pattern.compile("(?i)bubble"),
                                        Pattern.compile("(?i)sort")
                                ), 1))
                        .add(method().rootBind("m") //INT array possible??
                                .body(block()
                                        .has(loop() //Outerloop can be while or for loop
                                                .body(block()
                                                        .has(loop() // Is forLoop in all examples
                                                                .body(block()
                                                                        .has(ite()
                                                                                .condition(binOp().ops(">", "<", "<=", ">="))
                                                                                .then(block().has(varDefOrAss().rhs(arrayRead()))) // Costs 4 TP but cuts almost all FP
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                ));
        }
}
