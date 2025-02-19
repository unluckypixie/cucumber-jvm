package io.cucumber.core.backend;

import org.apiguardian.api.API;

@API(status = API.Status.STABLE)
public interface Glue {

    void addBeforeAllHook(StaticHookDefinition beforeAllHook);

    void addAfterAllHook(StaticHookDefinition afterAllHook);

    void addStepDefinition(StepDefinition stepDefinition);

    void addBeforeHook(HookDefinition beforeHook);

    void addAfterHook(HookDefinition afterHook);

    void addBeforeStepHook(HookDefinition beforeStepHook);

    void addAfterStepHook(HookDefinition afterStepHook);

    void addBeforeFeatureHook(HookDefinition beforeFeatureHook);

    void addAfterFeatureHook(HookDefinition afterFeatureHook);

    void addParameterType(ParameterTypeDefinition parameterType);

    void addDataTableType(DataTableTypeDefinition dataTableType);

    void addDefaultParameterTransformer(DefaultParameterTransformerDefinition defaultParameterTransformer);

    void addDefaultDataTableEntryTransformer(
            DefaultDataTableEntryTransformerDefinition defaultDataTableEntryTransformer
    );

    void addDefaultDataTableCellTransformer(DefaultDataTableCellTransformerDefinition defaultDataTableCellTransformer);

    void addDocStringType(DocStringTypeDefinition docStringType);

}
