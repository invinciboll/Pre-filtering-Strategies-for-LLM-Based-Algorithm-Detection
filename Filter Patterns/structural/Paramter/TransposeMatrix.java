package pattern_pool.llmPrefilterPatterns.structural.v1.i0;

import pattern.description.PatternMatchingDescriptionImpl;
import pattern.description.chain.BindingExportConfiguration;
import pattern.description.chain.PatternMatchingDescriptionChain;
import pattern.description.chain.PatternMatchingDescriptionChainBuilder;
import pattern.dsl.internal.type.Types;
import pattern.dsl.marker.DslNode;
import spoon.Launcher;

import javax.lang.model.type.ArrayType;

import java.lang.reflect.Array;

import static pattern.dsl.DSL.*;

public class TransposeMatrix {
        public static final PatternMatchingDescriptionChain CHAIN = PatternMatchingDescriptionChainBuilder
                .create("TransposeMatrix")
                .defaultExport(new BindingExportConfiguration(true).addAll("method"))
                .add("main", new PatternMatchingDescriptionImpl(getPattern(specification())))
                .build();

        private static DslNode specification() {
            return method().rootBind("m").parameters(
                    parameter().type(Types.from(Array.class))
            );
        }

}

