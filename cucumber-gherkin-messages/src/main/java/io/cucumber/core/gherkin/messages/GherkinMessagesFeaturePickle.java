package io.cucumber.core.gherkin.messages;

import io.cucumber.core.gherkin.Pickle;
import io.cucumber.core.gherkin.Step;
import io.cucumber.gherkin.GherkinDialect;
import io.cucumber.messages.types.Examples;
import io.cucumber.messages.types.Feature;
import io.cucumber.messages.types.Rule;
import io.cucumber.messages.types.Scenario;
import io.cucumber.messages.types.Tag;
import io.cucumber.plugin.event.Location;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

public class GherkinMessagesFeaturePickle implements Pickle {
    private final io.cucumber.messages.types.Pickle delegatePickle;
    // private final io.cucumber.messages.types.Pickle delegate;
    private final String pickleId;
    private final URI uri;
    private final CucumberQuery cucumberQuery;
    private final boolean isFirstInFeature;
    private final List<String> tags;

    GherkinMessagesFeaturePickle(
            String pickleId, URI uri, GherkinDialect dialect, CucumberQuery cucumberQuery,
            io.cucumber.messages.types.Pickle delegatePickle, List<Tag> featureTags, boolean isFirstInFeature
    ) {
        this.pickleId = pickleId;
        this.uri = uri;
        this.cucumberQuery = cucumberQuery;
        this.isFirstInFeature = isFirstInFeature;
        this.delegatePickle = delegatePickle;
        this.tags = featureTags.stream().map(tag -> tag.getName()).collect(Collectors.toList());

        // this.pickle = new io.cucumber.messages.types.Pickle(
        // pickleId,
        // uri.toString(),
        // isFirstInFeature ? "@BeforeFeature" : "@AfterFeature",
        // delegatePickle.getLanguage(),
        // emptyList(),
        // featureTags.stream().map(tag -> new PickleTag(tag.getName(),
        // tag.getId())).collect(Collectors.toList()),
        // delegatePickle.getAstNodeIds());
    }

    @Override
    public String getKeyword() {
        return cucumberQuery.getScenarioBy(delegatePickle).getKeyword();
    }

    @Override
    public String getLanguage() {
        return delegatePickle.getLanguage();
    }

    @Override
    public String getName() {
        return isFirstInFeature ? "@BeforeFeature" : "@AfterFeature";
    }

    @Override
    public Location getLocation() {
        return GherkinMessagesLocation.from(cucumberQuery.getLocationBy(delegatePickle));
    }

    @Override
    public Location getScenarioLocation() {
        Scenario scenario = cucumberQuery.getScenarioBy(delegatePickle);
        return GherkinMessagesLocation.from(scenario.getLocation());
    }

    @Override
    public Optional<Location> getRuleLocation() {
        return cucumberQuery.findRuleBy(delegatePickle)
                .map(Rule::getLocation)
                .map(GherkinMessagesLocation::from);
    }

    @Override
    public Optional<Location> getFeatureLocation() {
        return cucumberQuery.findFeatureBy(delegatePickle)
                .map(Feature::getLocation)
                .map(GherkinMessagesLocation::from);
    }

    @Override
    public Optional<Location> getExamplesLocation() {
        return cucumberQuery.findExamplesBy(delegatePickle)
                .map(Examples::getLocation)
                .map(GherkinMessagesLocation::from);
    }

    @Override
    public List<Step> getSteps() {
        return emptyList();
    }

    @Override
    public List<String> getTags() {
        return tags;
    }

    @Override
    public URI getUri() {
        return uri;
    }

    @Override
    public String getId() {
        return pickleId;
    }

    @Override
    public boolean isFirstInFeature() {
        return isFirstInFeature;
    }

    @Override
    public boolean isLastInFeature() {
        return !isFirstInFeature;
    }
}
